package Episante.back;

import Episante.back.Models.*;
import Episante.back.Repository.IPatientrepository;
import Episante.back.Repository.MedecinRepository;
import Episante.back.Repository.IPatientrepository;
import Episante.back.Repository.QuestionRepository;
import Episante.back.Service.RendezVousService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void run(String... args) throws Exception {
        // Demographic Questions
        createSexQuestion();
        createNumericQuestion("What is your age?", "years");
        createNumericQuestion("What is your weight?", "kg");
        createNumericQuestion("What is your height?", "cm");

        // Lifestyle Questions
        createNumericQuestion("How many miles do you walk/run per day?", "miles");
        createBooleanQuestion("Do you smoke?");
        createNumericQuestion("How many hours do you sleep per day?", "hours");

        // Medical History Questions
        createBooleanQuestion("Do you have a history of heart disease in your family?");
        createNumericQuestion("How many liters of water do you drink per day?", "liters");
        createBooleanQuestion("Do you have any known allergies?");
    }

    private void createSexQuestion() {
        createQuestionIfNotExists(
                "What is your sex?",
                "MULTIPLE_CHOICE",
                null,
                "male,female" // Only these two options
        );
    }

    private void createBooleanQuestion(String questionText) {
        createQuestionIfNotExists(
                questionText,
                "BOOLEAN",
                null,
                "yes,no" // Standard boolean options
        );
    }

    private void createNumericQuestion(String questionText, String unit) {
        createQuestionIfNotExists(
                questionText,
                "NUMERIC",
                unit,
                null
        );
    }

    private void createQuestionIfNotExists(String questionText, String questionType, String unit, String options) {
        if (!questionRepository.existsByQuestionText(questionText)) {
            Question question = new Question();
            question.setQuestionText(questionText);
            question.setQuestionType(questionType);
            question.setUnit(unit);
            question.setOptions(options);
            questionRepository.save(question);
        }
    }
}