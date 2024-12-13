package L4Epsilon.sqc.logic.elements;

import L4Epsilon.sqc.logic.visitors.Visitor;
import java.util.List;
import java.util.ArrayList;

public class Step extends Instruction{
    private List<Instruction> nextInstructions;
    private String text;

    public Step(List<Instruction> nextInstructions, String text){
        this.nextInstructions = (nextInstructions != null) ? nextInstructions : new ArrayList<>();
        this.text = text;
    }

    public List<Instruction> getNextInstructions(){
        return nextInstructions;
    }

    public String getText(){
        return text;
    }

    @Override
    public void accept(Visitor visitor){
        visitor.visitStep(this);
    }
}
