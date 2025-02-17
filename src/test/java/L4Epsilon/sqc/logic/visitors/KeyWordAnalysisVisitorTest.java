package L4Epsilon.sqc.logic.visitors;

import L4Epsilon.sqc.logic.elements.Action;
import L4Epsilon.sqc.logic.elements.Instruction;
import L4Epsilon.sqc.logic.elements.Scenario;
import L4Epsilon.sqc.logic.elements.Step;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KeyWordAnalysisVisitorTest {

    @Test
    void test_IF() {
        String text = "IF";

        List<Instruction> instructions = new ArrayList<>();

        Step step = new Step(instructions, text);

        KeyWordAnalysisVisitor visitor = new KeyWordAnalysisVisitor();
        visitor.visitStep(step);

        assertEquals(1, visitor.getOccurrenceCount());
    }


    @Test
    void test_ELSE() {
        String text = "ELSE";

        List<Instruction> instructions = new ArrayList<>();

        Step step = new Step(instructions, text);

        KeyWordAnalysisVisitor visitor = new KeyWordAnalysisVisitor();
        visitor.visitStep(step);

        assertEquals(1, visitor.getOccurrenceCount());
    }

    @Test
    void test_FOR_EACH() {
        String text = "FOR EACH";

        List<Instruction> instructions = new ArrayList<>();

        Step step = new Step(instructions, text);

        KeyWordAnalysisVisitor visitor = new KeyWordAnalysisVisitor();
        visitor.visitStep(step);

        assertEquals(1, visitor.getOccurrenceCount());
    }

    @Test
    void test_ELSE_IF() {
        String text = "ELSE IF";

        List<Instruction> instructions = new ArrayList<>();

        Step step = new Step(instructions, text);

        KeyWordAnalysisVisitor visitor = new KeyWordAnalysisVisitor();
        visitor.visitStep(step);

        assertEquals(1, visitor.getOccurrenceCount());
    }

    @Test
    void test__IF() {
        String text = " IF";

        List<Instruction> instructions = new ArrayList<>();

        Step step = new Step(instructions, text);

        KeyWordAnalysisVisitor visitor = new KeyWordAnalysisVisitor();
        visitor.visitStep(step);

        assertEquals(0, visitor.getOccurrenceCount());
    }
}