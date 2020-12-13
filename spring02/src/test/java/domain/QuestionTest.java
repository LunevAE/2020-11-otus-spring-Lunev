package domain;

import org.junit.jupiter.api.Test;
import ru.otus.spring.domain.Question;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {

    @Test
    public void shouldHaveCorrectConstructor() {
        Question q = new Question("1+1?", "2");

        assertThat(q.getText().equals("1+1?"));
        assertThat(q.getAnswer().equals("2"));
    }

}
