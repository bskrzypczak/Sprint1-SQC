package L4Epsilon.sqc.logic.visitors;

import L4Epsilon.sqc.logic.elements.*;


public class KeyWordAnalysisVisitor implements Visitor{
    private int occurrenceCount = 0;

    public int getOccurrenceCount(){
        return this.occurrenceCount;
    }

    @Override
    public void visitScenario(Scenario scenario){
        for (Instruction instruction : scenario.getInstructions()){
            instruction.accept(this);
        }
    }

    @Override
    public void visitStep(Step step) {
        String[] keyWords = {"IF", "ELSE", "FOR EACH"};

        for (String word : keyWords) {
            if (step.getText().startsWith(word)) {
                occurrenceCount++;
                for (Instruction instruction : step.getNextInstructions()){
                    if (instruction instanceof Step) visitStep((Step) instruction);
                }
            }
        }
    }



    @Override
    public void visitAction(Action action){}
}