package L4Epsilon.sqc.app;

import L4Epsilon.sqc.logic.elements.*;
import org.json.JSONObject;

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

        JSONObject scenarioJSON = parseJSON(path);

        List<Action> actions = new ArrayList<>();
        List<String> actors = new ArrayList<>();
        List<Step> steps = new ArrayList<>();

        Scenario scenario = new Scenario(scenarioJSON.getString("title"), scenarioJSON.getString("system_actor"), actors, steps);

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
     * Parsuje plik JSON i zwraca JSONObject.
     *
     * @param path ścieżka do JSONa.
     * @return JSONObject reprezentujący zawartość pliku.
     */

    public static JSONObject parseJSON(String path) {
        try {
            String fileContent = new String(Files.readAllBytes(Paths.get(path)));
            return new JSONObject(fileContent);
        } catch (IOException e) {
            throw new RuntimeException("Błąd podczas odczytu pliku JSON: " + e.getMessage(), e);
        }
    }
}
