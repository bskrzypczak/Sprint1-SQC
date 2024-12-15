package L4Epsilon.sqc.rest;

import L4Epsilon.sqc.logic.ScenarioQualityChecker;
import L4Epsilon.sqc.logic.elements.*;
import L4Epsilon.sqc.logic.visitors.CountingVisitor;
import L4Epsilon.sqc.logic.visitors.KeyWordAnalysisVisitor;
import L4Epsilon.sqc.logic.visitors.TextGenerationVisitor;
import org.springframework.stereotype.Service;

@Service
public class ScenarioService {

    public String getTitle(String fileName){
        String path = "testy/" + fileName + ".json";
        ScenarioQualityChecker checker = new ScenarioQualityChecker(path);
        Scenario scenario = checker.getReady();
        return scenario.getTitle();
    }

    public String getPlan(String fileName){
        TextGenerationVisitor textVisitor = new TextGenerationVisitor();
        String path = "testy/" + fileName + ".json";
        ScenarioQualityChecker checker = new ScenarioQualityChecker(path);
        Scenario scenario = checker.getReady();
        scenario.accept(textVisitor);
        return textVisitor.getGeneratedText();
    }

    public int getStepsCount(String fileName){
        CountingVisitor countingVisitor = new CountingVisitor();
        String path = "testy/" + fileName + ".json";
        ScenarioQualityChecker checker = new ScenarioQualityChecker(path);
        Scenario scenario = checker.getReady();
        scenario.accept(countingVisitor);

        return countingVisitor.getStepsCount();
    }

    public int getKeyWordSteps(String fileName){
        KeyWordAnalysisVisitor keyVisitor = new KeyWordAnalysisVisitor();
        String path = "testy/" + fileName + ".json";
        ScenarioQualityChecker checker = new ScenarioQualityChecker(path);
        Scenario scenario = checker.getReady();
        scenario.accept(keyVisitor);

        return keyVisitor.getOccurrenceCount();
    }
}
