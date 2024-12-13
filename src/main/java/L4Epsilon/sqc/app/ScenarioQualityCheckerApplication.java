package L4Epsilon.sqc.app;

import L4Epsilon.sqc.logic.ScenarioQualityChecker;
import L4Epsilon.sqc.logic.elements.*;
import L4Epsilon.sqc.logic.visitors.CountingVisitor;

import L4Epsilon.sqc.logic.visitors.KeyWordAnalysisVisitor;
import L4Epsilon.sqc.logic.visitors.TextGenerationVisitor;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"L4Epsilon.sqc.rest"})
public class ScenarioQualityCheckerApplication {

    public static void main(String[] args) {
        CountingVisitor countingVisitor = new CountingVisitor();
        KeyWordAnalysisVisitor keyWordVisitor = new KeyWordAnalysisVisitor();
        TextGenerationVisitor textVisitor = new TextGenerationVisitor();

        String path = "testy/test4.json";
        ScenarioQualityChecker checker = new ScenarioQualityChecker(path);
        Scenario scenario = checker.getReady();


        scenario.accept(countingVisitor);
        scenario.accept(keyWordVisitor);
        scenario.accept(textVisitor);

        System.out.println("Tytuł: " + scenario.getTitle());
        checker.printInstructions(scenario.getInstructions());
        System.out.println("Liczba krokow: " + countingVisitor.getStepsCount());
        System.out.println("Liczba krokow z kluczowymi slowami: " + keyWordVisitor.getOccurrenceCount());

        //SpringApplication.run(ScenarioQualityCheckerApplication.class, args);
    }


}
