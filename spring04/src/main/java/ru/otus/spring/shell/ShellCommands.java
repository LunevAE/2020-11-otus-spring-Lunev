package ru.otus.spring.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;
import ru.otus.spring.service.ViewService;
import java.util.List;

@ShellComponent
public class ShellCommands {
    private final ViewService viewService;
    private final QuestionDao questionDao;

    public ShellCommands(ViewService viewService, QuestionDao questionDao) {
        this.viewService = viewService;
        this.questionDao = questionDao;
    }

    @ShellMethod(value = "Ask username", key = {"n", "name"})
    public void askName() {
        viewService.askUser();
    }

    @ShellMethod(value = "Start the poll", key = {"s", "start"})
    public void startPoll() {
        List<Question> questions = questionDao.getListQuestions();
        viewService.showResult(viewService.askQuestions(questions));
    }
}
