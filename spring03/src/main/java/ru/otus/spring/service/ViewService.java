package ru.otus.spring.service;

import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.User;

import java.util.List;

public interface ViewService {
    User askUser();
    int askQuestions(List<Question> questions);
    void showResult(int rightAnswersAmount);
}
