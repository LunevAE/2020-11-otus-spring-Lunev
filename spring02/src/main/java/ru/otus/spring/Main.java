package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.service.ViewService;

@Configuration
@ComponentScan
public class Main {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);
        ViewService service = context.getBean(ViewService.class);
        service.askUser();
        service.showResult(service.askQuestions());
    }
}
