package L4Epsilon.sqc.logic.visitors;

import L4Epsilon.sqc.logic.elements.*;

public class KeyWordAnalysisVisitor implements Visitor{
    private int occurrenceCount;

    public int getOccurrenceCount(){
        return occurrenceCount;
    }

    @Override
    public void visitScenario(Scenario scenario){}

    @Override
    public void visitStep(Step step){}

    @Override
    public void visitAction(Action action){}
}