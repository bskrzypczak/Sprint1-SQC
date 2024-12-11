package L4Epsilon.sqc.logic.elements;

import L4Epsilon.sqc.logic.visitors.Visitor;

public interface Element {
    void accept(Visitor visitor);
}
