package L4Epsilon.sqc.logic.elements;

import L4Epsilon.sqc.logic.visitors.Visitor;
import java.util.List;

public class Scenario implements Element{
    private String title;
    private String systemActor;
    private List<String> actors;
    private List<Instruction> instructions;

    public Scenario(String title, String systemActor, List<String> actors, List<Instruction> instructions){
        this.title = title;
        this.systemActor = systemActor;
        this.actors = actors;
        this.instructions = instructions;
    }

    public String getTitle(){
        return title;
    }

    public String getSystemActor(){
        return systemActor;
    }

    public List<String> getActors(){
        return actors;
    }

    public List<Instruction> getInstructions(){
        return instructions;
    }

    @Override
    public void accept(Visitor visitor){
        visitor.visitScenario(this);
    }
}
