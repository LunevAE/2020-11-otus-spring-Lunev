package ru.otus.spring.service;

import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.User;

import java.util.List;

public interface ViewService {
    List<Question> listQuestions();
    User askUser();
    int askQuestions();
    void showResult(int rightAnswersAmount);
}
