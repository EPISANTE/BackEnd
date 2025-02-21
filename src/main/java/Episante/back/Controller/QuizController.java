package Episante.back.Controller;

import Episante.back.Models.Question;
import Episante.back.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/next-question")
    public ResponseEntity<Question> getNextQuestion() {
        Question question = quizService.getNextQuestion();
        if (question != null) {
            return ResponseEntity.ok(question);
        } else {
            return ResponseEntity.noContent().build(); // No more questions
        }
    }

    @PostMapping("/submit-answer")
    public ResponseEntity<Void> submitAnswer(@RequestParam Long questionId, @RequestParam String answer) {
        quizService.submitAnswer(questionId, answer);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/result")
    public ResponseEntity<String> getResult() {
        String result = quizService.calculateResult();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/has-more-questions")
    public ResponseEntity<Boolean> hasMoreQuestions() {
        return ResponseEntity.ok(quizService.hasMoreQuestions());
    }

    @PostMapping("/reset")
    public ResponseEntity<Void> resetQuiz() {
        quizService.resetQuiz();
        return ResponseEntity.ok().build();
    }
}
