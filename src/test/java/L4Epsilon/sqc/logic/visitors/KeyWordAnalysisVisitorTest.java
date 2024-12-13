package L4Epsilon.sqc.logic.visitors;

import L4Epsilon.sqc.logic.elements.Action;
import L4Epsilon.sqc.logic.elements.Scenario;
import L4Epsilon.sqc.logic.elements.Step;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class KeyWordAnalysisVisitorTest {

    int occurence = 0;

    @Test
    void test_IF() {
        List<String> aktorzy = new ArrayList<>();
        aktorzy.add("a");
        aktorzy.add("b");

        List<Action> akcje1 = new ArrayList<>();
        akcje1.add(new Action("IF"));

        Step krok1 = new Step(akcje1);

        List<Step> kroki = new ArrayList<>();
        kroki.add(krok1);
        Scenario scenario = new Scenario("abc", "abc", aktorzy, kroki);

        KeyWordAnalysisVisitor visitor = new KeyWordAnalysisVisitor();
        occurence = visitor.getOccurrenceCount(scenario);

        assertEquals(1, occurence);
    }

    @Test
    void test_ELSE() {
        List<String> aktorzy = new ArrayList<>();
        aktorzy.add("a");
        aktorzy.add("b");

        List<Action> akcje1 = new ArrayList<>();
        akcje1.add(new Action("ELSE"));

        Step krok1 = new Step(akcje1);

        List<Step> kroki = new ArrayList<>();
        kroki.add(krok1);
        Scenario scenario = new Scenario("abc", "abc", aktorzy, kroki);

        KeyWordAnalysisVisitor visitor = new KeyWordAnalysisVisitor();
        occurence = visitor.getOccurrenceCount(scenario);

        assertEquals(1, occurence);
    }

    @Test
    void test_FOR_EACH() {
        List<String> aktorzy = new ArrayList<>();
        aktorzy.add("a");
        aktorzy.add("b");

        List<Action> akcje1 = new ArrayList<>();
        akcje1.add(new Action("FOR EACH"));

        Step krok1 = new Step(akcje1);

        List<Step> kroki = new ArrayList<>();
        kroki.add(krok1);
        Scenario scenario = new Scenario("abc", "abc", aktorzy, kroki);

        KeyWordAnalysisVisitor visitor = new KeyWordAnalysisVisitor();
        occurence = visitor.getOccurrenceCount(scenario);

        assertEquals(1, occurence);
    }
}