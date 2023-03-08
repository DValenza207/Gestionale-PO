package demo_backend.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"demo_backend.authentication", "demo_backend.configuration", "demo_backend.entities", "demo_backend.model", "demo_backend.repositories", "demo_backend.restcontroller"})
public class AppConfig {
}
