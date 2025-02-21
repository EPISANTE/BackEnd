package Episante.back;

import Episante.back.Models.Question;
import Episante.back.Service.QuizService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class QuizControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private QuizService quizService; // You might mock this in some scenarios

    @Test
    void testGetNextQuestion() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/quiz/next-question"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
        // You might want to further assert the content of the JSON response
    }

    @Test
    void testSubmitAnswer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/quiz/submit-answer")
                        .param("questionId", "1")
                        .param("answer", "some answer"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetResult() throws Exception {
        // First, submit some answers to have a result
        quizService.resetQuiz(); // Ensure a clean state
        Question question1 = quizService.getNextQuestion();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/quiz/submit-answer")
                        .param("questionId", String.valueOf(question1.getId()))
                        .param("answer", "test answer"))
                .andExpect(status().isOk());

        while (quizService.hasMoreQuestions()) {
            Question nextQuestion = quizService.getNextQuestion();
            mockMvc.perform(MockMvcRequestBuilders.post("/api/quiz/submit-answer")
                            .param("questionId", String.valueOf(nextQuestion.getId()))
                            .param("answer", "another test answer"))
                    .andExpect(status().isOk());
        }

        mockMvc.perform(MockMvcRequestBuilders.get("/api/quiz/result"))
                .andExpect(status().isOk());
        // You could assert the content of the result string if needed
    }

    @Test
    void testResetQuiz() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/quiz/reset"))
                .andExpect(status().isOk());
        // Optionally, assert that getting the next question after reset returns the first question again
    }
}
