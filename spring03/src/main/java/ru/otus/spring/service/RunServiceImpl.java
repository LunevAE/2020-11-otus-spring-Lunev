package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;

import java.util.List;

@Service
public class RunServiceImpl implements RunService{
    private final ViewService viewService;
    private final QuestionDao questionDao;

    public RunServiceImpl(ViewService viewService, QuestionDao questionDao) {
        this.viewService = viewService;
        this.questionDao = questionDao;
    }

    public void run() {
        List<Question> questions = questionDao.getListQuestions();
        viewService.askUser();
        viewService.showResult(viewService.askQuestions(questions));
    }
}
