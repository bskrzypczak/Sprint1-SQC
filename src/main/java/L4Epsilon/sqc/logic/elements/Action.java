package L4Epsilon.sqc.logic.elements;

import L4Epsilon.sqc.logic.visitors.Visitor;

public class Action implements Element{
    private String text;

    public Action(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public void accept(Visitor visitor){
        visitor.visitAction(this);
    }
}
