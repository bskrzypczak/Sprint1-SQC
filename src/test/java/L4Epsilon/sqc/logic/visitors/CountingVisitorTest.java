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

        Scenario scenario = new Scenario("", "", null, instructions);
        countingVisitor.visitScenario(scenario);
        assertEquals(0, countingVisitor.getStepsCount());
    }

    @Test
    void Step1() {
        Step step1 = new Step(null, "");

        List<Instruction> instructions = new ArrayList<>();
        instructions.add(step1);

        Scenario scenario = new Scenario("", "", null, instructions);
        countingVisitor.visitScenario(scenario);
        assertEquals(1, countingVisitor.getStepsCount());
    }

    @Test
    void mock0() {
        Scenario mockScenario = org.mockito.Mockito.mock(Scenario.class);
        when(mockScenario.getInstructions()).thenReturn(List.of());

        countingVisitor.visitScenario(mockScenario);

        assertEquals(0, countingVisitor.getStepsCount());
    }

    @Test
    void mock1() {
        Scenario mockScenario = org.mockito.Mockito.mock(Scenario.class);
        Step mockStep = org.mockito.Mockito.mock(Step.class);
        when(mockScenario.getInstructions()).thenReturn(List.of(mockStep));

        doAnswer(invocation -> {
            CountingVisitor visitor = invocation.getArgument(0);
            visitor.visitStep(mockStep);
            return null;
        }).when(mockStep).accept(any(CountingVisitor.class));

        countingVisitor.visitScenario(mockScenario);

        assertEquals(1, countingVisitor.getStepsCount());
    }
}