package Episante.back.Service;


import Episante.back.Repository.QuestionRepository;
import Episante.back.Models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizService {

    @Autowired
    private QuestionRepository questionRepository;

    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private Map<Long, String> userAnswers = new HashMap<>();


    public Question getNextQuestion() {
        if (questions == null || questions.isEmpty()) {
            questions = questionRepository.findAll();
        }
        if (currentQuestionIndex < questions.size()) {
            return questions.get(currentQuestionIndex++);
        }
        return null; // No more questions
    }

    public void submitAnswer(Long questionId, String answer) {
        userAnswers.put(questionId, answer);
    }

    public String calculateResult() {
        if (questions == null || questions.isEmpty()) {
            questions = questionRepository.findAll();
        }
        int score = 0;
        for (Map.Entry<Long, String> entry : userAnswers.entrySet()) {
            Long questionId = entry.getKey();
            String userAnswer = entry.getValue().toLowerCase().trim();
            Optional<Question> question = questionRepository.findById(questionId);

            if(question.isPresent())
            {
                Question currentQuestion = question.get();
                if(currentQuestion.getQuestionText().equals("What is your age?"))
                {
                    try {
                        int age = Integer.parseInt(userAnswer);
                        if(age > 18 && age < 65) {
                            score+=20;
                        } else {
                            score -=20;
                        }
                    } catch (NumberFormatException e) {
                        score-=10;
                    }
                }
                if(currentQuestion.getQuestionText().equals("What is your weight?"))
                {
                    try {
                        int weight = Integer.parseInt(userAnswer);
                        if(weight > 60 && weight < 100) {
                            score+=20;
                        } else {
                            score-=20;
                        }
                    }catch (NumberFormatException e) {
                        score-=10;
                    }

                }
                if(currentQuestion.getQuestionText().equals("What is your height?"))
                {
                    try {
                        int height = Integer.parseInt(userAnswer);
                        if(height > 150 && height < 200) {
                            score+=20;
                        } else {
                            score-=20;
                        }
                    }catch (NumberFormatException e) {
                        score-=10;
                    }

                }
                if(currentQuestion.getQuestionText().equals("What is your sex?"))
                {
                    if(userAnswer.equals("male") || userAnswer.equals("female")) {
                        score += 10;
                    } else {
                        score -= 10;
                    }
                }
                if(currentQuestion.getQuestionText().equals("How many miles do you walk/run per day?"))
                {
                    try {
                        int miles = Integer.parseInt(userAnswer);
                        if(miles > 2) {
                            score += 20;
                        } else {
                            score-=20;
                        }
                    } catch (NumberFormatException e) {
                        score-=10;
                    }
                }
                if(currentQuestion.getQuestionText().equals("Do you smoke?"))
                {
                    if(userAnswer.equals("no")) {
                        score += 30;
                    } else {
                        score -= 30;
                    }
                }
                if(currentQuestion.getQuestionText().equals("How many hours do you sleep per day?"))
                {
                    try {
                        int sleepHours = Integer.parseInt(userAnswer);
                        if(sleepHours > 7 && sleepHours < 9) {
                            score += 20;
                        }else {
                            score-=20;
                        }
                    } catch (NumberFormatException e) {
                        score-=10;
                    }
                }
                if(currentQuestion.getQuestionText().equals("Do you have a history of heart disease in your family?"))
                {
                    if(userAnswer.equals("no")) {
                        score += 20;
                    }else {
                        score -= 20;
                    }
                }
                if(currentQuestion.getQuestionText().equals("How many liters of water do you drink per day?"))
                {
                    try {
                        int waterLiters = Integer.parseInt(userAnswer);
                        if(waterLiters >= 2) {
                            score += 20;
                        } else {
                            score -= 20;
                        }
                    } catch (NumberFormatException e) {
                        score -= 10;
                    }
                }
                if(currentQuestion.getQuestionText().equals("Do you have any known allergies?"))
                {
                    if(userAnswer.equals("no")) {
                        score += 10;
                    }else {
                        score -= 10;
                    }
                }
            }


        }


        if(score >= 150)
            return "Super good health";
        else if(score >= 100)
            return "Good health";
        else if(score >= 50)
            return "Okay health";
        else if(score >= 0)
            return "Poor health";
        else return "Very poor health";
    }


    public boolean hasMoreQuestions() {
        if (questions == null || questions.isEmpty()) {
            questions = questionRepository.findAll(); // Load from DB if not initialized
        }
        return currentQuestionIndex < questions.size();
    }

    public void resetQuiz() {
        currentQuestionIndex = 0;
        userAnswers.clear();
    }
}