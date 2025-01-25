package L4Epsilon.sqc.logic.visitors;

import L4Epsilon.sqc.logic.elements.Action;
import L4Epsilon.sqc.logic.elements.Instruction;
import L4Epsilon.sqc.logic.elements.Scenario;
import L4Epsilon.sqc.logic.elements.Step;

public class SubscenarioVisitor implements Visitor{
    private String subscenarioText = "";
    private int stepCounter = 1;    // Główny licznik kroków
    private String prefix = "";     // Prefiks dla numeracji zagnieżdżonej
    private int depth = 0;

    public String getSubscenarioText() {
        return subscenarioText;
    }

    public void setDepth(int givenDepth){
        this.depth = givenDepth;
    }

    @Override
    public void visitScenario(Scenario scenario) {
        for (Instruction instruction : scenario.getInstructions()){
            if (instruction instanceof Step){
                subscenarioText += stepCounter + ". " + ((Step) instruction).getText() + "\n";
                prefix = stepCounter + ".";
                if(depth > 1){
                    ((Step) instruction).accept(this);
                }
            }
            else {
                subscenarioText += stepCounter + ". " + ((Action) instruction).getText() + "\n";
            }
            stepCounter++;
        }
    }

    @Override
    public void visitStep(Step step) {
        if(depth > prefix.length() / 2) {
            int thisStep = 1;
            for (Instruction instruction : step.getNextInstructions()) {
                if (instruction instanceof Action) {
                    subscenarioText += prefix + thisStep + ". " + ((Action) instruction).getText() + "\n";
                } else {
                    subscenarioText += prefix + thisStep + ". " + ((Step) instruction).getText() + "\n";
                    prefix += thisStep + ".";
                    ((Step) instruction).accept(this);
                }
                thisStep++;
            }
        }

    }

    @Override
    public void visitAction(Action action) {
    }
}
