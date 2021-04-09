package ru.otus.spring.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import ru.otus.spring.batch.BookDocumentRepositoryItemWriter;
import ru.otus.spring.dao.nosql.BookDocumentDao;
import ru.otus.spring.dao.sql.BookDao;
import ru.otus.spring.domain.nosql.BookDocument;
import ru.otus.spring.domain.sql.Book;
import ru.otus.spring.service.BookConvertService;

import java.util.HashMap;
import java.util.List;

@Slf4j
@SuppressWarnings("all")
@EnableBatchProcessing
@Configuration
public class JobConfig {
    private static final int CHUNK_SIZE = 5;

    @Autowired
    private BookDao bookRepository;
    @Autowired
    private BookDocumentDao bookDocumentRepository;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @StepScope
    @Bean
    public RepositoryItemReader<Book> reader() {
        return new RepositoryItemReaderBuilder<Book>()
                .name("bookItemReader")
                .repository(bookRepository)
                .methodName("findAll")
                .sorts(new HashMap<>())
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<Book, BookDocument> processor(BookConvertService bookConvertService) {
        return bookConvertService::convert;
    }

    @StepScope
    @Bean
    public BookDocumentRepositoryItemWriter writer(MongoOperations template) {
        return new BookDocumentRepositoryItemWriter(template);
    }

    @Bean
    public Job importBookJob(Step step1) {
        return jobBuilderFactory.get("importBookJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1)
                .end()
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(JobExecution jobExecution) {
                        log.info("Начало job");
                    }

                    @Override
                    public void afterJob(JobExecution jobExecution) {
                        log.info("Конец job");
                    }
                })
                .build();
    }

    @Bean
    public Step importBooksStep(BookDocumentRepositoryItemWriter<BookDocument> writer, RepositoryItemReader<Book> reader, ItemProcessor<Book, BookDocument> itemProcessor) {
        return stepBuilderFactory.get("step1")
                .<Book, BookDocument>chunk(CHUNK_SIZE)
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .listener(new ItemReadListener<>() {
                    public void beforeRead() {
                        log.info("Начало чтения");
                    }

                    public void afterRead(Book book) {
                        log.info("Конец чтения");
                    }

                    public void onReadError(Exception e) {
                        log.info("Ошибка чтения");
                    }
                })
                .listener(new ItemWriteListener<>() {
                    public void beforeWrite(List list) {
                        log.info("Начало записи");
                    }

                    public void afterWrite(List list) {
                        log.info("Конец записи");
                    }

                    public void onWriteError(Exception e, List list) {
                        log.info("Ошибка записи");
                    }
                })
                .listener(new ItemProcessListener<Book, BookDocument>() {
                    public void beforeProcess(Book book) {
                        log.info("Начало обработки");
                    }

                    public void afterProcess(Book book, BookDocument bookDocument) {
                        log.info("Конец обработки");
                    }

                    public void onProcessError(Book book, Exception e) {
                        log.info("Ошбка обработки");
                    }
                })
                .listener(new ChunkListener() {
                    public void beforeChunk(ChunkContext chunkContext) {
                        log.info("Начало пачки");
                    }

                    public void afterChunk(ChunkContext chunkContext) {
                        log.info("Конец пачки");
                    }

                    public void afterChunkError(ChunkContext chunkContext) {
                        log.info("Ошибка пачки");
                    }
                })
                .build();
    }
}
