package L4Epsilon.sqc.logic;

import L4Epsilon.sqc.logic.elements.Action;
import L4Epsilon.sqc.logic.elements.Instruction;
import L4Epsilon.sqc.logic.elements.Scenario;
import L4Epsilon.sqc.logic.elements.Step;
import L4Epsilon.sqc.logic.visitors.CountingVisitor;
import L4Epsilon.sqc.logic.visitors.KeyWordAnalysisVisitor;
import L4Epsilon.sqc.logic.visitors.TextGenerationVisitor;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ScenarioQualityChecker {

    private String path;

    public ScenarioQualityChecker(String path){
        this.path = path;
    }

    /**
     * Parsuje plik JSON i zwraca JSONObject
     *
     * @param path ścieżka do JSONa
     * @return JSONObject z zawartością pliku
     */
    public JSONObject parseJSON(String path) {
        try {
            String fileContent = new String(Files.readAllBytes(Paths.get(path)));
            return new JSONObject(fileContent);
        } catch (IOException e) {
            throw new RuntimeException("Błąd podczas odczytu pliku JSON: " + e.getMessage(), e);
        }
    }


    /**
     * Transformuje JSONObject na model obiektowy Scenario
     *
     * @param scenarioJSON JSONObject reprezentujący scenariusz
     * @return Scenario jako model obiektowy
     */
    public Scenario transformJSONToModel(JSONObject scenarioJSON) {
        String title = scenarioJSON.getString("title");
        String systemActor = scenarioJSON.getString("system_actor");

        List<String> actors = new ArrayList<>();
        JSONArray actorsJSON = scenarioJSON.getJSONArray("actors");
        for (int i = 0; i < actorsJSON.length(); i++) {
            actors.add(actorsJSON.getString(i));
        }

        List<Instruction> instructions = parseInstructions(scenarioJSON.getJSONArray("steps"));

        return new Scenario(title, systemActor, actors, instructions);
    }


    /**
     * Transformuje JSONObject na model obiektowy Scenario
     *
     * @param stepsJSON JSONArray z krokami
     * @return Scenario jako obiekt Scenario
     */
    private List<Instruction> parseInstructions(JSONArray stepsJSON) {
        List<Instruction> instructions = new ArrayList<>();

        for (int i = 0; i < stepsJSON.length(); i++) {
            JSONObject stepJSON = stepsJSON.getJSONObject(i);

            if (stepJSON.has("action")) {
                instructions.add(new Action(stepJSON.getString("action")));
            } else if (stepJSON.has("condition")) {
                String condition = stepJSON.getString("condition");
                Action conditionAction = new Action(condition);
                instructions.add(new Step(parseInstructions(stepJSON.getJSONArray("steps")), conditionAction.getText()));
            } else if (stepJSON.has("loop")) {
                String loopInfo = stepJSON.getString("loop");
                Action loopAction = new Action(loopInfo);
                instructions.add(new Step(parseInstructions(stepJSON.getJSONArray("steps")), loopAction.getText()));
            }
        }

        return instructions;
    }

    public Scenario getReady(){
        JSONObject scenarioJSON = parseJSON(this.path);
        return transformJSONToModel(scenarioJSON);
    }


    /**
     * Wypisuje kazdy tekst w scenariuszu
     *
     * @param instructions lista Instrukcji scenariusza
     */
    public void printInstructions(List<Instruction> instructions) {
        for (Instruction instruction : instructions) {
            if (instruction instanceof Step) {
                Step step = (Step) instruction;
                System.out.println("Step: " + step.getText());
                if (step.getNextInstructions() != null) {
                    printInstructions(step.getNextInstructions());
                }
            } else if (instruction instanceof Action) {
                Action action = (Action) instruction;
                System.out.println("Action: " + action.getText());
            }
        }
    }

    public void generateJsonOutput(Scenario scenario, CountingVisitor countingVisitor,
                                   KeyWordAnalysisVisitor keyWordVisitor, TextGenerationVisitor textVisitor,
                                   String outputFilePath) {

        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{\n");


        if (scenario != null) {
            jsonBuilder.append("    \"title\": \"")
                    .append(scenario.getTitle().replace("\"", "\\\""))
                    .append("\",\n");
        }


        if (textVisitor != null) {
            String[] generatedTextLines = textVisitor.getGeneratedText().split("\n");
            StringBuilder generatedTextArray = new StringBuilder("[\n");

            for (int i = 0; i < generatedTextLines.length; i++) {
                generatedTextArray.append("        \"")
                        .append(generatedTextLines[i].trim().replace("\"", "\\\""))
                        .append("\"");

                if (i < generatedTextLines.length - 1) {
                    generatedTextArray.append(",");
                }
                generatedTextArray.append("\n");
            }
            generatedTextArray.append("    ]");

            jsonBuilder.append("    \"generatedText\": ")
                    .append(generatedTextArray)
                    .append(",\n");
        }


        if (countingVisitor != null) {
            jsonBuilder.append("    \"stepsCount\": ")
                    .append(countingVisitor.getStepsCount())
                    .append(",\n");
        }


        if (keyWordVisitor != null) {
            jsonBuilder.append("    \"keyWordOccurrences\": ")
                    .append(keyWordVisitor.getOccurrenceCount())
                    .append(",\n");
        }

        if (jsonBuilder.charAt(jsonBuilder.length() - 2) == ',') {
            jsonBuilder.deleteCharAt(jsonBuilder.length() - 2);
        }

        jsonBuilder.append("}\n");

        try {
            Files.write(Paths.get(outputFilePath), jsonBuilder.toString().getBytes());
            Files.write(Paths.get(outputFilePath), jsonBuilder.toString().getBytes());
            System.out.println("Wynik zapisano do pliku: " + outputFilePath);
        } catch (IOException e) {
            System.err.println("Błąd podczas zapisywania pliku JSON: " + e.getMessage());
        }

    }

    public static String generateOutputPath(String inputPath) {
        Path inputFilePath = Paths.get(inputPath);
        String fileNameWithoutExtension = inputFilePath.getFileName().toString().replaceFirst("[.][^.]+$", "");
        return "output/" + fileNameWithoutExtension + "_output.json";
    }


}
