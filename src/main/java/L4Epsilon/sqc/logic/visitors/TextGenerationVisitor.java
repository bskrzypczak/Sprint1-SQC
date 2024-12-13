package L4Epsilon.sqc.logic.visitors;

import L4Epsilon.sqc.logic.elements.*;

public class TextGenerationVisitor implements Visitor {
    private String generatedText = "";
    private int stepCounter = 1;  // Główny licznik kroków
    private String prefix = "";  // Prefiks dla numeracji zagnieżdżonej

    public String getGeneratedText() {
        return generatedText;
    }

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

