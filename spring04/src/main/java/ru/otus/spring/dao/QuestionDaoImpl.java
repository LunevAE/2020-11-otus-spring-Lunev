package ru.otus.spring.dao;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Question;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionDaoImpl implements QuestionDao{
    private final DataReader dataReader;

    public QuestionDaoImpl(DataReader dataReader) {
        this.dataReader = dataReader;
    }

    @Override
    public List<Question> getListQuestions(){
        List<Question> questionList = new ArrayList<>();
        List<String> lines = dataReader.readData();
        for (String line : lines){
            String[] splittedQuestion = line.split(",");
            Question question = new Question(splittedQuestion[0], splittedQuestion[1]);
            questionList.add(question);
        }

        return questionList;
    }
}
