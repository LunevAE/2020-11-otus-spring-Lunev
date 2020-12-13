package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.User;

import java.util.List;
import java.util.Scanner;

@Service
public class ViewServiceImpl implements ViewService{
    private final QuestionDao dao;
    private final Scanner sc;
    public ViewServiceImpl(QuestionDao dao) {
        this.dao = dao;
        sc = new Scanner(System.in);
    }

    public List<Question> listQuestions() {
        return dao.listQuestions();
    }

    public User askUser(){
        System.out.println("Enter your name");
        String name = sc.nextLine();

        return new User(name);
    }

    public int askQuestions(){
        List<Question> questions = listQuestions();
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
        System.out.print("Your result is: " + cnt + "/5");
    }
}
