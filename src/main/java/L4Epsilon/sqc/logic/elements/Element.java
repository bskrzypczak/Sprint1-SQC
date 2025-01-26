package L4Epsilon.sqc.logic.elements;

import L4Epsilon.sqc.logic.visitors.Visitor;

/**
 * Interfejs reprezentujący element w scenariuszu
 * Każdy element musi implementować metodę `accept`, która pozwala na odwiedzenie
 */
public interface Element {
    void accept(Visitor visitor);
}
