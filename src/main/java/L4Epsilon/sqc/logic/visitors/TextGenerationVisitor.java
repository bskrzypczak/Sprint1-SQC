package L4Epsilon.sqc.logic.visitors;

import L4Epsilon.sqc.logic.elements.*;

/**
 * Klasa implementująca wzorzec wizytatora do generowania tekstowegj
 * reprezentacji scenariusza i jego kroków.
 */
public class TextGenerationVisitor implements Visitor {
    private String generatedText = "";
    private int stepCounter = 1;  // Główny licznik kroków
    private String prefix = "";  // Prefiks dla numeracji zagnieżdżonej

    public String getGeneratedText() {
        return generatedText;
    }

    /**
     * Odwiedza scenariusz i generuje tekst zawierający jego instrukcje oraz
     * Numeruje każdy krok lub akcję w hierarchiczny sposób
     *
     * @param scenario Scenariusz do przetworzenie
     */
    @Override
    public void visitScenario(Scenario scenario) {
        for (Instruction instruction : scenario.getInstructions()){
            if (instruction instanceof Step){
                generatedText += stepCounter + ". " + ((Step) instruction).getText() + "\n";
                prefix = stepCounter + ".";
                ((Step) instruction).accept(this);
            }
            else {
                generatedText += stepCounter + ". " + ((Action) instruction).getText() + "\n";
            }
            stepCounter++;
        }
    }

    /**
     * Odwiedza krok scenariusza i generuje tekst jego instrukcji
     * Numeruje każdą zagnieżdżoną instrukcję zgodnie z hierarchią
     *
     * @param step Krok scenariusza do przetworzenia
     */
    @Override
    public void visitStep(Step step) {
        int thisStep = 1;
        for (Instruction instruction : step.getNextInstructions()){
            if (instruction instanceof Action){
                generatedText += prefix + thisStep + ". " + ((Action) instruction).getText() + "\n";
            }
            else {
                generatedText += prefix + thisStep + ". " + ((Step) instruction).getText() + "\n";
                prefix += thisStep + ".";
                ((Step) instruction).accept(this);
            }
            thisStep++;
        }

    }

    @Override
    public void visitAction(Action action) {
    }
}

