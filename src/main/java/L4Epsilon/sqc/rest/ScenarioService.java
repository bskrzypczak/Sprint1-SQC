package L4Epsilon.sqc.rest;

import L4Epsilon.sqc.logic.ScenarioQualityChecker;
import L4Epsilon.sqc.logic.elements.*;
import L4Epsilon.sqc.logic.visitors.CountingVisitor;
import L4Epsilon.sqc.logic.visitors.KeyWordAnalysisVisitor;
import L4Epsilon.sqc.logic.visitors.TextGenerationVisitor;
import org.springframework.stereotype.Service;

@Service
public class ScenarioService {

    public void generateCustomOutput(String fileName, boolean includePlan, boolean includeStepsCount, boolean includeKeyWordOccurrences) {
        String inputPath = "testy/" + fileName + ".json";
        String outputPath = "output/" + fileName + "_output.json";

        ScenarioQualityChecker checker = new ScenarioQualityChecker(inputPath);
        Scenario scenario = checker.getReady();

        CountingVisitor countingVisitor = null;
        KeyWordAnalysisVisitor keyWordVisitor = null;
        TextGenerationVisitor textVisitor = null;

        if (includeStepsCount) {
            countingVisitor = new CountingVisitor();
            scenario.accept(countingVisitor);
        }
        if (includeKeyWordOccurrences) {
            keyWordVisitor = new KeyWordAnalysisVisitor();
            scenario.accept(keyWordVisitor);
        }
        if (includePlan) {
            textVisitor = new TextGenerationVisitor();
            scenario.accept(textVisitor);
        }


        checker.generateJsonOutput(
                scenario,
                countingVisitor,
                keyWordVisitor,
                textVisitor,
                outputPath
        );
    }

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
