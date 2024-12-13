package L4Epsilon.sqc.logic.visitors;

import L4Epsilon.sqc.logic.elements.*;

public class CountingVisitor implements Visitor{
    private int stepsCount = 0;

    public int getStepsCount(){
        return stepsCount;
    }


    /**
     * Akceptuje każdą instrukcję po kolei
     *
     * @param scenario wygenerowany scenariusz
     */
    @Override
    public void visitScenario(Scenario scenario){
        for (Instruction instruction : scenario.getInstructions()){
            instruction.accept(this);
        }
    }

    /**
     * Inkrementuje stepsCount oraz akceptuje każdą kolejną instrukcję
     *
     * @param step instancja klasy step
     */
    @Override
    public void visitStep(Step step){
        stepsCount++;
        for (Instruction instruction : step.getNextInstructions()){
            instruction.accept(this);
        }
    }

    /**
     * Inkrementuje stepsCount
     *
     * @param action instancja klasy akcja
     */
    @Override
    public void visitAction(Action action){
        stepsCount++;
    }
}