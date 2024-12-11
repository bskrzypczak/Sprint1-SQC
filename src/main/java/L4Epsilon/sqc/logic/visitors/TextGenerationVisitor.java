package L4Epsilon.sqc.logic.visitors;

import L4Epsilon.sqc.logic.elements.*;

public class TextGenerationVisitor implements Visitor{
    private String generatedText;

    public String getGeneratedText(){
        return generatedText;
    }

    @Override
    public void visitScenario(Scenario scenario){}

    @Override
    public void visitStep(Step step){}

    @Override
    public void visitAction(Action action){}
}
