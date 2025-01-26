package L4Epsilon.sqc.logic.visitors;

import L4Epsilon.sqc.logic.elements.Action;
import L4Epsilon.sqc.logic.elements.Instruction;
import L4Epsilon.sqc.logic.elements.Scenario;
import L4Epsilon.sqc.logic.elements.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CountingVisitorTest {
    private CountingVisitor countingVisitor;

    @BeforeEach
    void setUp() {
        countingVisitor = new CountingVisitor();
    }

    @Test
    void Step0() {
        List<Instruction> instructions = new ArrayList<>();

        Scenario scenario = new Scenario("abc", "system", null, instructions);
        countingVisitor.visitScenario(scenario);
        assertEquals(0, countingVisitor.getStepsCount());
    }

    @Test
    void Step1() {
        Step step1 = new Step(null, "a");

        List<Instruction> instructions = new ArrayList<>();
        instructions.add(step1);

        Scenario scenario = new Scenario("abc", "system", null, instructions);
        countingVisitor.visitScenario(scenario);
        assertEquals(1, countingVisitor.getStepsCount());
    }

    @Test
    void Step2() {
        Step step1 = new Step(null, "a");

        List<Instruction> instructions = new ArrayList<>();
        instructions.add(step1);
        instructions.add(step1);

        Scenario scenario = new Scenario("abc", "system", null, instructions);
        countingVisitor.visitScenario(scenario);
        assertEquals(2, countingVisitor.getStepsCount());
    }

    @Test
    void Step3() {
        Step step1 = new Step(null, "a");

        List<Instruction> instructions = new ArrayList<>();
        instructions.add(step1);
        instructions.add(step1);
        instructions.add(step1);

        Scenario scenario = new Scenario("abc", "system", null, instructions);
        countingVisitor.visitScenario(scenario);
        assertEquals(3, countingVisitor.getStepsCount());
    }

    @Test
    void mock() {
        Scenario mockScenario = org.mockito.Mockito.mock(Scenario.class);
        when(mockScenario.getInstructions()).thenReturn(List.of());

        countingVisitor.visitScenario(mockScenario);

        assertEquals(0, countingVisitor.getStepsCount());
    }


}