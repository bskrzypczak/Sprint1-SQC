package L4Epsilon.sqc.logic.visitors;

import L4Epsilon.sqc.logic.elements.*;

import java.util.List;
import java.util.Objects;

/**
 * Klasa implementująca wzorzec wizytatora do identyfikowania niepoprawnych kroków
 * w scenariuszu. Krok uznaje się za niepoprawny, jeśli nie zaczyna się od jednego
 * z dozwolonych słów kluczowych, aktorów lub aktora systemowego
 */
public class ActorStepsVisitor implements Visitor {

    private String generatedIncorrectSteps = "";
    private int stepCounter = 1;  // Główny licznik kroków
    private String prefix = "";  // Prefiks dla numeracji zagnieżdżonej
    private int counter = 0;
    private List<String> allowedStarts;

    /**
     * Zwraca wygenerowany tekst niepoprawnych kroków
     *
     * @return Tekst niepoprawnych kroków jako String
     */
    public String getGeneratedIncorrectSteps() {
        if (Objects.equals(generatedIncorrectSteps, "")) {
            generatedIncorrectSteps = "Brak niepoprawnych kroków";
        }
        return generatedIncorrectSteps;
    }

    /**
     * Odwiedza scenariusz i sprawdza poprawność każdego kroku i akcji
     * Krok uznaje się za poprawny, jeśli jego treść zaczyna się od jednego z dozwolonych
     * słów kluczowych, aktorów lub aktora systemowego.
     *
     * @param scenario Scenariusz, który ma być przetworzony.
     */
    @Override
    public void visitScenario(Scenario scenario) {
        allowedStarts = scenario.getActors();
        allowedStarts.add("IF");
        allowedStarts.add("FOR EACH");
        allowedStarts.add("ELSE");
        allowedStarts.add(scenario.getSystemActor());

        for (Instruction instruction : scenario.getInstructions()) {
            if (instruction instanceof Step) {
                for (String start : allowedStarts) {
                    if (((Step) instruction).getText().startsWith(start)) {
                        counter++;
                    }
                }
                if (counter == 0) {
                    generatedIncorrectSteps += stepCounter + ". " + ((Step) instruction).getText() + "\n";
                }
                counter = 0;
                prefix = stepCounter + ".";
                ((Step) instruction).accept(this);
            } else {
                for (String start : allowedStarts) {
                    if (((Action) instruction).getText().startsWith(start)) {
                        counter++;
                    }
                }
                if (counter == 0) {
                    generatedIncorrectSteps += stepCounter + ". " + ((Action) instruction).getText() + "\n";
                }
                counter = 0;
            }
            stepCounter++;
        }
    }

    /**
     * Odwiedza krok scenariusza i sprawdza poprawność jego instrukcji podrzędnych.
     *
     * @param step Krok scenariusza, który ma być przetworzony.
     */
    @Override
    public void visitStep(Step step) {
        int thisStep = 1;
        for (Instruction instruction : step.getNextInstructions()) {
            if (instruction instanceof Action) {
                for (String start : allowedStarts) {
                    if (((Action) instruction).getText().startsWith(start)) {
                        counter++;
                    }
                }
                if (counter == 0) {
                    generatedIncorrectSteps += prefix + thisStep + ". " + ((Action) instruction).getText() + "\n";
                }
                counter = 0;
            } else {
                for (String start : allowedStarts) {
                    if (((Step) instruction).getText().startsWith(start)) {
                        counter++;
                    }
                }
                if (counter == 0) {
                    generatedIncorrectSteps += prefix + thisStep + ". " + ((Step) instruction).getText() + "\n";
                }
                counter = 0;
                prefix += thisStep + ".";
                ((Step) instruction).accept(this);
            }
            thisStep++;
        }
    }

    @Override
    public void visitAction(Action action) {
        //pali jeździ ale zapomnialem pull requesta
    }


}
