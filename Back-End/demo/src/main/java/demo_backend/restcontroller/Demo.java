
package demo_backend.restcontroller;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Demo {

    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = {"Authorization", "Origin"}) //Per consentire il collegamento tra la porta 4200 e 8080
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("{\"response\": \"Hello %s !\"}", name);
    }

}
            