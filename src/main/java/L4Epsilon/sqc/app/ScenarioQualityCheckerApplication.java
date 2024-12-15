package L4Epsilon.sqc.app;

import L4Epsilon.sqc.logic.ScenarioQualityChecker;
import L4Epsilon.sqc.logic.elements.*;
import L4Epsilon.sqc.logic.visitors.CountingVisitor;

import L4Epsilon.sqc.logic.visitors.KeyWordAnalysisVisitor;
import L4Epsilon.sqc.logic.visitors.TextGenerationVisitor;
import L4Epsilon.sqc.rest.ScenarioQualityCheckerController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication(scanBasePackages = {"L4Epsilon.sqc.rest"})
public class ScenarioQualityCheckerApplication {

    public static void main(String[] args) {
        CountingVisitor countingVisitor = new CountingVisitor();
        KeyWordAnalysisVisitor keyWordVisitor = new KeyWordAnalysisVisitor();
        TextGenerationVisitor textVisitor = new TextGenerationVisitor();

        String inputPath = "testy/test1.json";
        String outputPath = "ScenarioQualityChecker_output.json";
        ScenarioQualityChecker checker = new ScenarioQualityChecker(inputPath);
        Scenario scenario = checker.getReady();

        scenario.accept(countingVisitor);
        scenario.accept(keyWordVisitor);
        scenario.accept(textVisitor);

        System.out.println("Tytuł: " + scenario.getTitle());
        System.out.println(textVisitor.getGeneratedText());
        System.out.println("Liczba krokow: " + countingVisitor.getStepsCount());
        System.out.println("Liczba krokow z kluczowymi slowami: " + keyWordVisitor.getOccurrenceCount());

        checker.generateJsonOutput(scenario, countingVisitor, keyWordVisitor, textVisitor, outputPath);

        SpringApplication.run(ScenarioQualityCheckerApplication.class, args);
    }


}
