package L4Epsilon.sqc.app;

import L4Epsilon.sqc.logic.elements.*;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"L4Epsilon.sqc.rest"})
public class ScenarioQualityCheckerApplication {

    public static void main(String[] args) {

        Action action = new Action("Bibliotekarz wybiera opcje dodania nowej pozycji ksiazkowej");
        List<Action> actions = new ArrayList<>();
        List<String> actors = new ArrayList<>();
        List<Step> steps = new ArrayList<>();
        actions.add(action);
        actors.add("Bibliotekarz");
        Step step = new Step(actions);
        steps.add(step);

        Scenario scenario = new Scenario("Dodanie ksiazki", "System", actors, steps);

        System.out.println("SCENARIUSZ TESTOWY\n ");
        System.out.println("Tytuł: " + scenario.getTitle());
        for (Step stepx : scenario.getSteps()){
            for (Action actionx : stepx.getActions()){
                System.out.println(actionx.getText());
            }
        }


        //SpringApplication.run(ScenarioQualityCheckerApplication.class, args);
    }
}
