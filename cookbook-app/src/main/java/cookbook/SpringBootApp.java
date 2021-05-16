package cookbook;

import cookbook.app.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "cookbook.*")
public class SpringBootApp {

	@Autowired
	private App app;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApp.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			app.start();
		};
	}
}
