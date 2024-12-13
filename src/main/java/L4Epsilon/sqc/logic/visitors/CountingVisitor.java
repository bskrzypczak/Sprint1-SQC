package L4Epsilon.sqc.logic.visitors;

import L4Epsilon.sqc.logic.elements.*;

public class CountingVisitor implements Visitor{
    private int stepsCount = 0;

    public int getStepsCount(){
        return stepsCount;
    }

    @Override
    public void visitScenario(Scenario scenario){
        for (Instruction instruction : scenario.getInstructions()){
            instruction.accept(this);
        }
    }

    @Override
    public void visitStep(Step step){
        stepsCount++;
        for (Instruction instruction : step.getNextInstructions()){
            instruction.accept(this);
        }
    }

    @Override
    public void visitAction(Action action){
        stepsCount++;
    }
}