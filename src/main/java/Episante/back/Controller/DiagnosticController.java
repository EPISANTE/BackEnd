package Episante.back.Controller;
import Episante.back.Service.DiagnosticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/diagnostic")
public class DiagnosticController {

    @Autowired
    private DiagnosticService diagnosticService;

    @PostMapping("/start")
    public ResponseEntity<String> startDiagnosis() {
        String sessionId = diagnosticService.startSession();

        String firstQuestion = diagnosticService.getNextQuestion(sessionId);

        return ResponseEntity.ok(sessionId +","+ firstQuestion);
    }

        @PostMapping("/answer/{sessionId}")
    public ResponseEntity<String> processAnswer(@PathVariable String sessionId, @RequestBody String answer) {
        String response = diagnosticService.processAnswer(sessionId, answer);
        return ResponseEntity.ok(response);
    }
}
