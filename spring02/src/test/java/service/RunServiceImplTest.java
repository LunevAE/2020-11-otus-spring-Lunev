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

import java.util.List;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RunServiceImplTest {
    @Mock
    private QuestionDao questionDao;

    @Mock
    private ViewService viewService;

    Question q1 = new Question("1+0?", "1");
    Question q2;
    User user = new User("Ivan");

    @BeforeEach
    public void setUp() {
        q2 = new Question("2+0?", "2");
    }

    @Test
    public void RunServiceTest() {
        when(questionDao.getListQuestions()).thenReturn(List.of(q1, q2));
        when(viewService.askUser()).thenReturn(user);
        when(viewService.askQuestions(List.of(q1, q2))).thenReturn(3);

        assertThat(questionDao.getListQuestions()).isEqualTo(List.of(q1, q2));
        assertThat(viewService.askUser()).isSameAs(user);
        assertThat(viewService.askQuestions(List.of(q1, q2))).isEqualTo(3);
    }
}
