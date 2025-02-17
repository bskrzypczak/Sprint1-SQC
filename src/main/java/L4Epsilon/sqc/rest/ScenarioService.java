package L4Epsilon.sqc.rest;

import L4Epsilon.sqc.logic.ScenarioQualityChecker;
import L4Epsilon.sqc.logic.elements.*;
import L4Epsilon.sqc.logic.visitors.*;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class ScenarioService {

    private String getFilePath(String fileName) {
        String uploadedPath = "uploaded/" + fileName + ".json";
        String testPath = "testy/" + fileName + ".json";

        if (Files.exists(Paths.get(uploadedPath))) {
            return uploadedPath;
        } else if (Files.exists(Paths.get(testPath))) {
            return testPath;
        } else {
            throw new IllegalArgumentException("Plik o nazwie '" + fileName + "' nie istnieje.");
        }
    }

    public void generateCustomOutput(String fileName, boolean includePlan, boolean includeStepsCount, boolean includeKeyWordOccurrences,boolean includeIncorrectSteps,boolean includeSubSteps,int depth) {
        String inputPath = getFilePath(fileName);
        String outputPath = "output/" + fileName + "_output.json";

        ScenarioQualityChecker checker = new ScenarioQualityChecker(inputPath);
        Scenario scenario = checker.getReady();

        CountingVisitor countingVisitor = null;
        KeyWordAnalysisVisitor keyWordVisitor = null;
        TextGenerationVisitor textVisitor = null;
        ActorStepsVisitor actorVisitor = null;
        SubscenarioVisitor subVisitor = null;

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
        if (includeIncorrectSteps) {
            actorVisitor = new ActorStepsVisitor();
            scenario.accept(actorVisitor);
        }

        if (includeSubSteps) {
            subVisitor = new SubscenarioVisitor();
            subVisitor.setDepth(depth);
            scenario.accept(subVisitor);
        }


        checker.generateJsonOutput(
                scenario,
                countingVisitor,
                keyWordVisitor,
                textVisitor,
                actorVisitor,
                subVisitor,
                outputPath
        );
    }

    public String getTitle(String fileName){
        String inputPath = getFilePath(fileName);
        ScenarioQualityChecker checker = new ScenarioQualityChecker(inputPath);
        Scenario scenario = checker.getReady();
        return scenario.getTitle();
    }

    public String getPlan(String fileName){
        TextGenerationVisitor textVisitor = new TextGenerationVisitor();
        String inputPath = getFilePath(fileName);
        ScenarioQualityChecker checker = new ScenarioQualityChecker(inputPath);
        Scenario scenario = checker.getReady();
        scenario.accept(textVisitor);
        return textVisitor.getGeneratedText();
    }

    public String getWrongSteps(String fileName){
        ActorStepsVisitor actorVisitor = new ActorStepsVisitor();
        String inputPath = getFilePath(fileName);
        ScenarioQualityChecker checker = new ScenarioQualityChecker(inputPath);
        Scenario scenario = checker.getReady();
        scenario.accept(actorVisitor);
        return actorVisitor.getGeneratedIncorrectSteps();
    }
    public String getSubSteps(String fileName, int depth) {
        SubscenarioVisitor subVisitor = new SubscenarioVisitor();
        subVisitor.setDepth(depth);

        String inputPath = getFilePath(fileName);
        ScenarioQualityChecker checker = new ScenarioQualityChecker(inputPath);
        Scenario scenario = checker.getReady();

        scenario.accept(subVisitor);

        return subVisitor.getSubscenarioText();
    }

    public int getStepsCount(String fileName){
        CountingVisitor countingVisitor = new CountingVisitor();
        String inputPath = getFilePath(fileName);
        ScenarioQualityChecker checker = new ScenarioQualityChecker(inputPath);
        Scenario scenario = checker.getReady();
        scenario.accept(countingVisitor);

        return countingVisitor.getStepsCount();
    }

    public int getKeyWordSteps(String fileName){
        KeyWordAnalysisVisitor keyVisitor = new KeyWordAnalysisVisitor();
        String inputPath = getFilePath(fileName);
        ScenarioQualityChecker checker = new ScenarioQualityChecker(inputPath);
        Scenario scenario = checker.getReady();
        scenario.accept(keyVisitor);

        return keyVisitor.getOccurrenceCount();
    }
}
