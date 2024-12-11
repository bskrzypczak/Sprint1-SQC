package L4Epsilon.sqc.logic.visitors;

import L4Epsilon.sqc.logic.elements.*;

public interface Visitor {
    void visitScenario(Scenario scenario);
    void visitStep(Step step);
    void visitAction(Action action);
}
