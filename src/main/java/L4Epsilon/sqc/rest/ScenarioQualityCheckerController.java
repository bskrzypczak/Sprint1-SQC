package L4Epsilon.sqc.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


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

    @GetMapping("/generate-output")
    public ResponseEntity<String> generateOutput(
            @RequestParam String fileName,
            @RequestParam(required = false, defaultValue = "false") boolean includePlan,
            @RequestParam(required = false, defaultValue = "false") boolean includeStepsCount,
            @RequestParam(required = false, defaultValue = "false") boolean includeKeyWordOccurrences
    ) {
        try {
            service.generateCustomOutput(fileName, includePlan, includeStepsCount, includeKeyWordOccurrences);
            return ResponseEntity.ok("Output JSON został wygenerowany dla pliku: " + fileName);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Błąd: " + e.getMessage());
        }
    }
    @GetMapping("/download")
    public ResponseEntity<Resource> downloadOutput(@RequestParam String fileName) {
        try {
            String outputPath = "output/" + fileName + "_output.json";
            Path filePath = Paths.get(outputPath);

            if (!Files.exists(filePath)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            Resource resource = new UrlResource(filePath.toUri());
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "_output.json\"")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}





