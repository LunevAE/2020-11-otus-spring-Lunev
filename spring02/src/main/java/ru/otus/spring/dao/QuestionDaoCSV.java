package ru.otus.spring.dao;

import org.springframework.stereotype.Service;
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
    public List<Question> listQuestions() {
        List<Question> questionList = new ArrayList<>();
        String fileName = "questions.csv";
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        try {
            String line;
            Scanner sc;
            int index = 0;

            while ((line = reader.readLine()) != null) {
                Question question = new Question();
                sc = new Scanner(line);
                sc.useDelimiter(",");

                while (sc.hasNext()) {
                    String data = sc.next();
                    if (index == 0) {
                        question.setText(data);
                    } else if (index == 1) {
                        question.setAnswer(data);
                    }
                    index++;
                }
                index = 0;
                questionList.add(question);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return questionList;
    }
}
