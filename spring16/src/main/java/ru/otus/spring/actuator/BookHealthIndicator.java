package ru.otus.spring.actuator;

import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import ru.otus.spring.dao.BookDao;

@Component
@AllArgsConstructor
public class BookHealthIndicator implements HealthIndicator {
  private BookDao bookDao;

  @Override
  public Health health() {
    if (isBookExists()) {
      return Health.up()
              .withDetail("message", "There is book The Sign of the Four" )
              .build();
    }
    else {
      return Health.down()
              .status(Status.DOWN)
              .withDetail("message", "There is not book The Sign of the Four")
              .build();
    }
  }

  private boolean isBookExists(){
    return bookDao.findByTitle("The Sign of the Four").isPresent();
  }
}
