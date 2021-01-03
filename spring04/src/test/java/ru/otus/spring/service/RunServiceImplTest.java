package ru.otus.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.User;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
public class RunServiceImplTest {
    @MockBean
    private QuestionDao questionDao;

    @MockBean
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
