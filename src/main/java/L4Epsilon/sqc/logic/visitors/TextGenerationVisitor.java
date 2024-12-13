package L4Epsilon.sqc.logic.visitors;

import L4Epsilon.sqc.logic.elements.*;

public class TextGenerationVisitor implements Visitor{
    private String generatedText = "";

    public String getGeneratedText(){
        return generatedText;
    }

    @Override
    public void visitScenario(Scenario scenario){
        int i = 1;
        for (Instruction instruction : scenario.getInstructions()){
            if (instruction instanceof Action){
                System.out.println(i + ". " + ((Action) instruction).getText());
                i++;
            }
            else {
                System.out.println(((Step) instruction).getText());
            }
        }
    }

    @Override
    public void visitStep(Step step){
    }

    @Override
    public void visitAction(Action action){
    }
}
