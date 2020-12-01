package ru.otus.spring.service;

import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao dao;

    public QuestionServiceImpl(QuestionDao dao) {
        this.dao = dao;
    }

    public List<Question> listQuestions() {
        return dao.listQuestions();
    }

    public void showQuestions(){
        List<Question> questions = listQuestions();

        for (Question question : questions) {
            System.out.println(question.getText());
        }
    }
}
