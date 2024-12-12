package L4Epsilon.sqc.app;

import L4Epsilon.sqc.logic.elements.*;
import L4Epsilon.sqc.logic.visitors.KeyWordAnalysisVisitor;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"L4Epsilon.sqc.rest"})
public class ScenarioQualityCheckerApplication {

    public static void main(String[] args) {
        String path = "test1.json";

        // plik JSON -> JSONObject
        JSONObject scenarioJSON = parseJSON(path);

        // JSONObject -> model obiektowy
        Scenario scenario = transformJSONToModel(scenarioJSON);

        // testowy print
        System.out.println("SCENARIUSZ TESTOWY\n ");
        System.out.println("Tytuł: " + scenario.getTitle());
        for (Step stepx : scenario.getSteps()){
            for (Action actionx : stepx.getActions()){
                System.out.println(actionx.getText());
            }
        }

        //SpringApplication.run(ScenarioQualityCheckerApplication.class, args);
    }

    /**
     * Parsuje plik JSON i zwraca JSONObject
     *
     * @param path ścieżka do JSONa
     * @return JSONObject z zawartością pliku
     */

    public static JSONObject parseJSON(String path) {
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
    public static Scenario transformJSONToModel(JSONObject scenarioJSON) {
        String title = scenarioJSON.getString("title");
        String systemActor = scenarioJSON.getString("system_actor");

        List<String> actors = new ArrayList<>();
        JSONArray actorsJSON = scenarioJSON.getJSONArray("actors");
        for (int i = 0; i < actorsJSON.length(); i++) {
            actors.add(actorsJSON.getString(i));
        }

        List<Step> steps = parseSteps(scenarioJSON.getJSONArray("steps"));

        return new Scenario(title, systemActor, actors, steps);
    }

    /**
     * Transformuje JSONObject na model obiektowy Scenario
     *
     * @param stepsJSON JSONArray z krokami
     * @return Scenario jako obiekt Scenario
     */
    private static List<Step> parseSteps(JSONArray stepsJSON) {
        List<Step> steps = new ArrayList<>();

        for (int i = 0; i < stepsJSON.length(); i++) {
            JSONObject stepJSON = stepsJSON.getJSONObject(i);

            if (stepJSON.has("action")) {
                steps.add(new Step(List.of(new Action(stepJSON.getString("action")))));
            } else if (stepJSON.has("condition")) {
                String condition = stepJSON.getString("condition");
                Action conditionAction = new Action("Condition: " + condition);
                steps.add(new Step(List.of(conditionAction)));
            } else if (stepJSON.has("loop")) {
                String loopInfo = stepJSON.getString("loop");
                Action loopAction = new Action("Loop: " + loopInfo);
                steps.add(new Step(List.of(loopAction)));
            }
        }

        return steps;
    }

}
