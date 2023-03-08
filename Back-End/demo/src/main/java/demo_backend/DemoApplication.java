package demo_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

// Inserita nel package Main per evitare il rischio di non trovare repositories o classi
@SpringBootApplication
@ComponentScan(basePackages= {"demo_backend.authentication", "demo_backend.configuration", "demo_backend.entities", "demo_backend.model", "demo_backend.repositories", "demo_backend.restcontroller"  })
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
