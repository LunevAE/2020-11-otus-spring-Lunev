package ru.otus.spring.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class QuestionTest {

    @Test
    public void shouldHaveCorrectConstructor() {
        Question q = new Question("1+1?", "2");

        assertThat(q.getText().equals("1+1?"));
        assertThat(q.getAnswer().equals("2"));
    }

}
