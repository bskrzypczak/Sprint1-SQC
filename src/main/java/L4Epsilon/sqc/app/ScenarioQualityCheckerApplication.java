package L4Epsilon.sqc.app;

import L4Epsilon.sqc.logic.ScenarioQualityChecker;
import L4Epsilon.sqc.logic.elements.*;
import L4Epsilon.sqc.logic.visitors.*;

import L4Epsilon.sqc.rest.ScenarioQualityCheckerController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import static L4Epsilon.sqc.logic.ScenarioQualityChecker.generateOutputPath;


@SpringBootApplication(scanBasePackages = {"L4Epsilon.sqc.rest"})
public class ScenarioQualityCheckerApplication {

    public static void main(String[] args) {
        CountingVisitor countingVisitor = new CountingVisitor();
        KeyWordAnalysisVisitor keyWordVisitor = new KeyWordAnalysisVisitor();
        TextGenerationVisitor textVisitor = new TextGenerationVisitor();
        ActorStepsVisitor actorStepsVisitor = new ActorStepsVisitor();
        SubscenarioVisitor subscenarioVisitor = new SubscenarioVisitor();

        String inputPath = "testy/test1.json";
        String outputPath = generateOutputPath(inputPath);
        ScenarioQualityChecker checker = new ScenarioQualityChecker(inputPath);
        Scenario scenario = checker.getReady();
        subscenarioVisitor.setDepth(4);

        scenario.accept(countingVisitor);
        scenario.accept(keyWordVisitor);
        scenario.accept(textVisitor);
        scenario.accept(actorStepsVisitor);
        scenario.accept(subscenarioVisitor);

        System.out.println("Tytuł: " + scenario.getTitle());
        System.out.println(textVisitor.getGeneratedText());
        System.out.println("Liczba krokow: " + countingVisitor.getStepsCount());
        System.out.println("Liczba krokow z kluczowymi slowami: " + keyWordVisitor.getOccurrenceCount());
        System.out.println("Niepoprawne kroki: \n" + actorStepsVisitor.getGeneratedIncorrectSteps());
        System.out.println("Scenariusz o glebokosci 1: \n" + subscenarioVisitor.getSubscenarioText());

        checker.generateJsonOutput(scenario, countingVisitor, keyWordVisitor, textVisitor, outputPath);

        SpringApplication.run(ScenarioQualityCheckerApplication.class, args);
    }


}
