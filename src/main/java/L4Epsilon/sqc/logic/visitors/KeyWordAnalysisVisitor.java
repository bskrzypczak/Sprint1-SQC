package L4Epsilon.sqc.logic.visitors;

import L4Epsilon.sqc.logic.elements.*;

import java.util.List;

public class KeyWordAnalysisVisitor implements Visitor{
    private int occurrenceCount;

    public int getOccurrenceCount(Scenario scenario){
        int occurrenceCount = 0;
        for (Step step : scenario.getSteps()) {
            for (Action actionx : step.getActions()){
                if (actionx.getText().contains("IF") || actionx.getText().contains("FOR EACH") || actionx.getText().contains("ELSE")){
                    occurrenceCount ++;
                }
            }
        }
        return occurrenceCount;
    }

    @Override
    public void visitScenario(Scenario scenario){}

    @Override
    public void visitStep(Step step){}

    @Override
    public void visitAction(Action action){}
}