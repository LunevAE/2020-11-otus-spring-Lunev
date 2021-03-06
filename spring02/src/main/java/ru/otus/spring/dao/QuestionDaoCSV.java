package ru.otus.spring.dao;

import org.springframework.stereotype.Service;
import ru.otus.spring.config.DaoConfig;
import ru.otus.spring.domain.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class QuestionDaoCSV implements QuestionDao{
    private final List<String> lines = new ArrayList<>();

    private final DaoConfig daoConfig;
    public QuestionDaoCSV(DaoConfig daoConfig) {
        this.daoConfig = daoConfig;
        String fileName = this.daoConfig.getFileName() + ".csv";
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        try {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Question> getListQuestions(){
        List<Question> questionList = new ArrayList<>();
        for (String line : lines){
            String[] splittedQuestion = line.split(",");
            Question question = new Question(splittedQuestion[0], splittedQuestion[1]);
            questionList.add(question);
        }
            
        return questionList;
    }
}
