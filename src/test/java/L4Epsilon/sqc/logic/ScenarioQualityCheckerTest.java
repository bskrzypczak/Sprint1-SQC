package L4Epsilon.sqc.logic;

import L4Epsilon.sqc.logic.elements.Instruction;
import L4Epsilon.sqc.logic.elements.Scenario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.json.JSONArray;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScenarioQualityCheckerTest {

    @Test
    void tytul() {
        JSONObject ScenarioJSON = new JSONObject();
        ScenarioJSON.put("title", "Dodanie ksiazki");
        ScenarioJSON.put("system_actor", "System");

        JSONArray actorsJSON = new JSONArray();
        actorsJSON.put("Bibliotekarz");
        ScenarioJSON.put("actors", actorsJSON);

        JSONArray stepsJSON = new JSONArray();
        stepsJSON.put(new JSONObject().put("action", "Bibliotekarz wybiera opcje dodania nowej pozycji ksiazkowej"));
        ScenarioJSON.put("steps", stepsJSON);

        ScenarioQualityChecker checker = new ScenarioQualityChecker(null);

        Scenario result = checker.transformJSONToModel(ScenarioJSON);

        assertEquals("Dodanie ksiazki", result.getTitle());
    }

    @Test
    void aktor_systemowy() {
        JSONObject ScenarioJSON = new JSONObject();
        ScenarioJSON.put("title", "Dodanie ksiazki");
        ScenarioJSON.put("system_actor", "System");

        JSONArray actorsJSON = new JSONArray();
        actorsJSON.put("Bibliotekarz");
        ScenarioJSON.put("actors", actorsJSON);

        JSONArray stepsJSON = new JSONArray();
        stepsJSON.put(new JSONObject().put("action", "Bibliotekarz wybiera opcje dodania nowej pozycji ksiazkowej"));
        ScenarioJSON.put("steps", stepsJSON);

        ScenarioQualityChecker checker = new ScenarioQualityChecker(null);

        Scenario result = checker.transformJSONToModel(ScenarioJSON);

        assertEquals("System", result.getSystemActor());

    }

    @Test
    void aktorzy() {
        JSONObject ScenarioJSON = new JSONObject();
        ScenarioJSON.put("title", "Dodanie ksiazki");
        ScenarioJSON.put("system_actor", "System");

        JSONArray actorsJSON = new JSONArray();
        actorsJSON.put("Bibliotekarz");
        ScenarioJSON.put("actors", actorsJSON);

        JSONArray stepsJSON = new JSONArray();
        stepsJSON.put(new JSONObject().put("action", "Bibliotekarz wybiera opcje dodania nowej pozycji ksiazkowej"));
        ScenarioJSON.put("steps", stepsJSON);

        ScenarioQualityChecker checker = new ScenarioQualityChecker(null);

        Scenario result = checker.transformJSONToModel(ScenarioJSON);

        assertEquals(List.of("Bibliotekarz"), result.getActors());

    }

    @Test
    void liczba_aktorow() {
        JSONObject ScenarioJSON = new JSONObject();
        ScenarioJSON.put("title", "Dodanie ksiazki");
        ScenarioJSON.put("system_actor", "System");

        JSONArray actorsJSON = new JSONArray();
        actorsJSON.put("Bibliotekarz");
        ScenarioJSON.put("actors", actorsJSON);

        JSONArray stepsJSON = new JSONArray();
        stepsJSON.put(new JSONObject().put("action", "Bibliotekarz wybiera opcje dodania nowej pozycji ksiazkowej"));
        ScenarioJSON.put("steps", stepsJSON);

        ScenarioQualityChecker checker = new ScenarioQualityChecker(null);

        Scenario result = checker.transformJSONToModel(ScenarioJSON);

        assertEquals(1, result.getActors().size());
    }

    @Test
    void liczba_instrukcji() {
        JSONObject ScenarioJSON = new JSONObject();
        ScenarioJSON.put("title", "Dodanie ksiazki");
        ScenarioJSON.put("system_actor", "System");

        JSONArray actorsJSON = new JSONArray();
        actorsJSON.put("Bibliotekarz");
        ScenarioJSON.put("actors", actorsJSON);

        JSONArray stepsJSON = new JSONArray();
        stepsJSON.put(new JSONObject().put("action", "Bibliotekarz wybiera opcje dodania nowej pozycji ksiazkowej"));
        ScenarioJSON.put("steps", stepsJSON);

        ScenarioQualityChecker checker = new ScenarioQualityChecker(null);

        Scenario result = checker.transformJSONToModel(ScenarioJSON);

        assertEquals(1, result.getInstructions().size());
    }

    @Test
    void mock_tytul() {
        JSONObject mockScenarioJSON = mock(JSONObject.class);
        when(mockScenarioJSON.getString("title")).thenReturn("Dodanie ksiazki");
        when(mockScenarioJSON.getString("system_actor")).thenReturn("System");

        JSONArray mockActorsJSON = mock(JSONArray.class);
        when(mockScenarioJSON.getJSONArray("actors")).thenReturn(mockActorsJSON);
        when(mockActorsJSON.length()).thenReturn(1);
        when(mockActorsJSON.getString(0)).thenReturn("Bibliotekarz");

        JSONArray mockStepsJSON = mock(JSONArray.class);
        when(mockScenarioJSON.getJSONArray("steps")).thenReturn(mockStepsJSON);

        ScenarioQualityChecker checker = new ScenarioQualityChecker(null);

        Scenario result = checker.transformJSONToModel(mockScenarioJSON);

        assertEquals("Dodanie ksiazki", result.getTitle());
    }

    @Test
    void mock_aktor_systemowy() {
        JSONObject mockScenarioJSON = mock(JSONObject.class);
        when(mockScenarioJSON.getString("title")).thenReturn("Dodanie ksiazki");
        when(mockScenarioJSON.getString("system_actor")).thenReturn("System");

        JSONArray mockActorsJSON = mock(JSONArray.class);
        when(mockScenarioJSON.getJSONArray("actors")).thenReturn(mockActorsJSON);
        when(mockActorsJSON.length()).thenReturn(1);
        when(mockActorsJSON.getString(0)).thenReturn("Bibliotekarz");

        JSONArray mockStepsJSON = mock(JSONArray.class);
        when(mockScenarioJSON.getJSONArray("steps")).thenReturn(mockStepsJSON);

        ScenarioQualityChecker checker = new ScenarioQualityChecker(null);

        Scenario result = checker.transformJSONToModel(mockScenarioJSON);

        assertEquals("System", result.getSystemActor());
    }

    @Test
    void mock_1liczba_aktorow() {
        JSONObject mockScenarioJSON = mock(JSONObject.class);
        when(mockScenarioJSON.getString("title")).thenReturn("Dodanie ksiazki");
        when(mockScenarioJSON.getString("system_actor")).thenReturn("System");

        JSONArray mockActorsJSON = mock(JSONArray.class);
        when(mockScenarioJSON.getJSONArray("actors")).thenReturn(mockActorsJSON);
        when(mockActorsJSON.length()).thenReturn(1);
        when(mockActorsJSON.getString(0)).thenReturn("Bibliotekarz");

        JSONArray mockStepsJSON = mock(JSONArray.class);
        when(mockScenarioJSON.getJSONArray("steps")).thenReturn(mockStepsJSON);

        ScenarioQualityChecker checker = new ScenarioQualityChecker(null);

        Scenario result = checker.transformJSONToModel(mockScenarioJSON);

        assertEquals(1, result.getActors().size());
    }

    @Test
    void mock_2liczba_aktorow() {
        JSONObject mockScenarioJSON = mock(JSONObject.class);
        when(mockScenarioJSON.getString("title")).thenReturn("Dodanie ksiazki");
        when(mockScenarioJSON.getString("system_actor")).thenReturn("System");

        JSONArray mockActorsJSON = mock(JSONArray.class);
        when(mockScenarioJSON.getJSONArray("actors")).thenReturn(mockActorsJSON);
        when(mockActorsJSON.length()).thenReturn(2);
        when(mockActorsJSON.getString(0)).thenReturn("Bibliotekarz");
        when(mockActorsJSON.getString(1)).thenReturn("Nauczyciel");

        JSONArray mockStepsJSON = mock(JSONArray.class);
        when(mockScenarioJSON.getJSONArray("steps")).thenReturn(mockStepsJSON);

        ScenarioQualityChecker checker = new ScenarioQualityChecker(null);

        Scenario result = checker.transformJSONToModel(mockScenarioJSON);

        assertEquals(2, result.getActors().size());
    }

    @Test
    void mock_0liczba_instrukcji() {
        JSONObject mockScenarioJSON = mock(JSONObject.class);
        when(mockScenarioJSON.getString("title")).thenReturn("Dodanie ksiazki");
        when(mockScenarioJSON.getString("system_actor")).thenReturn("System");

        JSONArray mockActorsJSON = mock(JSONArray.class);
        when(mockScenarioJSON.getJSONArray("actors")).thenReturn(mockActorsJSON);
        when(mockActorsJSON.length()).thenReturn(1);
        when(mockActorsJSON.getString(0)).thenReturn("Bibliotekarz");

        JSONArray mockStepsJSON = mock(JSONArray.class);
        when(mockScenarioJSON.getJSONArray("steps")).thenReturn(mockStepsJSON);

        ScenarioQualityChecker checker = new ScenarioQualityChecker(null);

        Scenario result = checker.transformJSONToModel(mockScenarioJSON);

        assertEquals(0, result.getInstructions().size());
    }

    @Test
    void mock_1liczba_instrukcji() {
        JSONObject mockScenarioJSON = mock(JSONObject.class);
        when(mockScenarioJSON.getString("title")).thenReturn("Dodanie ksiazki");
        when(mockScenarioJSON.getString("system_actor")).thenReturn("System");

        JSONArray mockActorsJSON = mock(JSONArray.class);
        when(mockScenarioJSON.getJSONArray("actors")).thenReturn(mockActorsJSON);
        when(mockActorsJSON.length()).thenReturn(1);
        when(mockActorsJSON.getString(0)).thenReturn("Bibliotekarz");

        JSONArray mockStepsJSON = mock(JSONArray.class);
        when(mockScenarioJSON.getJSONArray("steps")).thenReturn(mockStepsJSON);
        when(mockStepsJSON.length()).thenReturn(1);

        JSONObject mockStep = mock(JSONObject.class);
        when(mockStepsJSON.getJSONObject(0)).thenReturn(mockStep);
        when(mockStep.has("action")).thenReturn(true);
        when(mockStep.getString("action")).thenReturn("Bibliotekarz wybiera opcje dodania nowej pozycji ksiazkowej");

        ScenarioQualityChecker checker = new ScenarioQualityChecker(null);

        Scenario result = checker.transformJSONToModel(mockScenarioJSON);

        assertEquals(1, result.getInstructions().size());
    }

    @Test
    void mock_aktorzy() {
        JSONObject mockScenarioJSON = mock(JSONObject.class);
        when(mockScenarioJSON.getString("title")).thenReturn("Dodanie ksiazki");
        when(mockScenarioJSON.getString("system_actor")).thenReturn("System");

        JSONArray mockActorsJSON = mock(JSONArray.class);
        when(mockScenarioJSON.getJSONArray("actors")).thenReturn(mockActorsJSON);
        when(mockActorsJSON.length()).thenReturn(1);
        when(mockActorsJSON.getString(0)).thenReturn("Bibliotekarz");

        JSONArray mockStepsJSON = mock(JSONArray.class);
        when(mockScenarioJSON.getJSONArray("steps")).thenReturn(mockStepsJSON);

        ScenarioQualityChecker checker = new ScenarioQualityChecker(null);

        Scenario result = checker.transformJSONToModel(mockScenarioJSON);

        assertEquals("Bibliotekarz", result.getActors().get(0));
    }

    @Test
    void mockOutputTest(){
        ScenarioQualityChecker checker = new ScenarioQualityChecker("dummyPath");
        String outputPath = ScenarioQualityChecker.generateOutputPath("input/scenario.json");

        assertEquals("output/scenario_output.json", outputPath);
    }
}