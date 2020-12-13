package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.User;
import ru.otus.spring.service.ViewService;
import ru.otus.spring.service.ViewServiceImpl;

import java.util.List;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ViewServiceImplTest {
    @Mock
    private QuestionDao questionDao;

    private ViewService viewService;

    Question q1 = new Question("1+0?", "1");
    Question q2;
    User user = new User("Ivan");

    @BeforeEach
    public void setUp() {
        viewService = new ViewServiceImpl(questionDao);
        q2 = new Question("2+0?", "2");
    }

    @Test
    public void listQuestionsTest() {
        when(viewService.listQuestions()).thenReturn(List.of(q1, q2));
    }
}
