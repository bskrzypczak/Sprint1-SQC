package L4Epsilon.sqc.logic.visitors;

import L4Epsilon.sqc.logic.elements.*;

public class CountingVisitor implements Visitor{
    private int stepsCount;

    public int getStepsCount(){
        return stepsCount;
    }

    @Override
    public void visitScenario(Scenario scenario){}

    @Override
    public void visitStep(Step step){}

    @Override
    public void visitAction(Action action){}
}