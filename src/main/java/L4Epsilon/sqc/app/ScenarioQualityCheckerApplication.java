package L4Epsilon.sqc.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"L4Epsilon.sqc.rest"})
public class ScenarioQualityCheckerApplication {

    public static void main(String[] args) {
        System.out.println("...");
        SpringApplication.run(ScenarioQualityCheckerApplication.class, args);
    }
}
