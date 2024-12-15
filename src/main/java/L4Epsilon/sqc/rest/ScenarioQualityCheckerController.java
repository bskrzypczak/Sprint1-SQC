package L4Epsilon.sqc.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import L4Epsilon.sqc.logic.ScenarioQualityChecker;

import  L4Epsilon.sqc.logic.elements.Scenario;

import java.util.List;

@RestController
@RequestMapping("/scenario")
public class ScenarioQualityCheckerController {

    private static final Logger logger = LoggerFactory.getLogger(ScenarioQualityCheckerController.class);
    private ScenarioService service;

    @Autowired
    public ScenarioQualityCheckerController(ScenarioService service){
        this.service = service;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/index.html";
    }

    @GetMapping("/all-info")
    public String getEverything(@RequestParam String fileName){
        logger.info("Pobieranie wszystkich informacji dla scenariusza: " + fileName);
        String rawText = service.getTitle(fileName);
        rawText += "<br><br>";
        rawText += service.getPlan(fileName);
        rawText += "<br><br>";
        rawText += "Liczba kroków w scenariuszu: ";
        rawText += service.getStepsCount(fileName);
        rawText += "<br><br>";
        rawText += "Liczba kroków w scenariuszu zaczynających się na słowo kluczowe: ";
        rawText += service.getKeyWordSteps(fileName);
        return rawText.replace("\n", "<br>");
    }

    @GetMapping("/text")
    public String getPlan(@RequestParam String fileName){
        logger.info("Pobieranie ponumerowanego tekstu dla scenariusza: " + fileName);
        String rawText = service.getTitle(fileName);
        rawText += "<br><br>";
        rawText += service.getPlan(fileName);
        return rawText.replace("\n", "<br>");
    }

    @GetMapping("/number-of-steps")
    public String getStepsCount(@RequestParam String fileName){
        logger.info("Pobieranie liczby krokow dla scenariusza: " + fileName);
        //String rawText = service.getTitle(fileName);
       // rawText += "<br><br>";
        String rawText = "Liczba kroków w scenariuszu: ";
        rawText += service.getStepsCount(fileName);
        return rawText;
    }

    @GetMapping("/key-words")
    public String getKeyWords(@RequestParam String fileName){
        logger.info("Pobieranie liczby krokow ze slowami kluczowymi dla scenariusza: " + fileName);
       // String rawText = service.getTitle(fileName);
       // rawText += "<br><br>";
        String rawText = "Liczba kroków w scenariuszu zaczynających się na słowo kluczowe: ";
        rawText += service.getKeyWordSteps(fileName);
        return rawText;
    }
}


