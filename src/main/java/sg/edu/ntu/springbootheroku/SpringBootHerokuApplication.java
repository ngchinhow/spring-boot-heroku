package sg.edu.ntu.springbootheroku;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootHerokuApplication {

	@Value("${spring.datasource.url}")
	private String datasourceUrl;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootHerokuApplication.class, args);
	}

}
