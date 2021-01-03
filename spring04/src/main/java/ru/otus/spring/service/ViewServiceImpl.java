package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.User;
import java.util.List;
import java.util.Scanner;

@Service
public class ViewServiceImpl implements ViewService{
    private final LocalizedMessage localizedMessage;
    private final Scanner sc = new Scanner(System.in);
    public ViewServiceImpl(QuestionDao dao, LocalizedMessage localizedMessage) {
        this.localizedMessage = localizedMessage;
    }

    public User askUser(){
        System.out.println(localizedMessage.getMessage("message.name"));
        String name = sc.nextLine();

        return new User(name);
    }

    public int askQuestions(List<Question> questions){
        int rightAnswersAmount = 0;
        for (Question question : questions) {
            System.out.println(question.getText());
            String answer = sc.nextLine();
            if (answer.equals(question.getAnswer())){
                rightAnswersAmount++;
            }
        }
        return rightAnswersAmount;
    }

    public void showResult(int cnt){
        System.out.println(localizedMessage.getMessage("message.result", cnt));
    }
}
