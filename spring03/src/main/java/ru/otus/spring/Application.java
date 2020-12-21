package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.otus.spring.config.AppProps;
import ru.otus.spring.service.RunService;

@SpringBootApplication
@EnableConfigurationProperties(AppProps.class)
public class Application {

	public static void main(String[] args) {
		var context = SpringApplication.run(Application.class, args);
		RunService service = context.getBean(RunService.class);
		service.run();
	}

}
