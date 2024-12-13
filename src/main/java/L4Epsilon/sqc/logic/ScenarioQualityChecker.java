package L4Epsilon.sqc.logic;

import L4Epsilon.sqc.logic.elements.Action;
import L4Epsilon.sqc.logic.elements.Instruction;
import L4Epsilon.sqc.logic.elements.Scenario;
import L4Epsilon.sqc.logic.elements.Step;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
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
                    printInstructions(step.getNextInstructions()); // Rekurencja dla zagnieżdżonych instrukcji
                }
            } else if (instruction instanceof Action) {
                Action action = (Action) instruction;
                System.out.println("Action: " + action.getText());
            }
        }
    }

}
