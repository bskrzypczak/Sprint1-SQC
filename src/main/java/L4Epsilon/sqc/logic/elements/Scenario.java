package L4Epsilon.sqc.logic.elements;

import L4Epsilon.sqc.logic.visitors.Visitor;
import java.util.List;

public class Scenario implements Element{
    private String title;
    private String systemActor;
    private List<String> actors;
    private List<Step> steps;

    public Scenario(String title, String systemActor, List<String> actors, List<Step> steps){
        this.title = title;
        this.systemActor = systemActor;
        this.actors = actors;
        this.steps = steps;
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

    public List<Step> getSteps(){
        return steps;
    }

    @Override
    public void accept(Visitor visitor){
        visitor.visitScenario(this);
    }
}
