package L4Epsilon.sqc.logic.elements;

import L4Epsilon.sqc.logic.visitors.Visitor;
import java.util.List;

public class Step implements Element{
    private List<Action> actions;

    public Step(List<Action> actions){
        this.actions = actions;
    }

    public List<Action> getActions(){
        return actions;
    }

    @Override
    public void accept(Visitor visitor){
        visitor.visitStep(this);
    }
}
