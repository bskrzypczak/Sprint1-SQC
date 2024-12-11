package L4Epsilon.sqc.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer.rest"})
public class ScenarioQualityCheckerApplication {

    public static void main(String[] args) {
        System.out.println("Błagam");
        SpringApplication.run(ScenarioQualityCheckerApplication.class, args);
    }
}
