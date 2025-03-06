package Episante.back.Service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class DiagnosticServiceImpl implements DiagnosticService {

    private final Map<String, SessionState> sessions = new ConcurrentHashMap<>();

    private Map<String, String> decisionTree;

    @PostConstruct
    public void initializeDecisionTree() {
        decisionTree = new HashMap<>();
        decisionTree.put("START", "Do you have a fever? (yes/no)");

        decisionTree.put("Do you have a fever? (yes/no):yes", "Do you have fatigue or muscle pain? (yes/no)");
        decisionTree.put("Do you have a fever? (yes/no):no", "Insufficient symptoms for a diagnosis. Please consult a doctor if you feel unwell.");

        decisionTree.put("Do you have a fever? (yes/no):yes,Do you have fatigue or muscle pain? (yes/no):yes", "Do you have a cough? (yes/no)");
        decisionTree.put("Do you have a fever? (yes/no):yes,Do you have fatigue or muscle pain? (yes/no):no", "Diagnosis: Possible Common Cold (Rhume)");

        decisionTree.put("Do you have a fever? (yes/no):yes,Do you have fatigue or muscle pain? (yes/no):yes,Do you have a cough? (yes/no):yes", "Diagnosis: Possible Flu (Grippe)");
        decisionTree.put("Do you have a fever? (yes/no):yes,Do you have fatigue or muscle pain? (yes/no):yes,Do you have a cough? (yes/no):no", "Do you have a runny nose? (yes/no)");

        decisionTree.put("Do you have a fever? (yes/no):yes,Do you have fatigue or muscle pain? (yes/no):yes,Do you have a cough? (yes/no):no,Do you have a runny nose? (yes/no):yes", "Diagnosis: Possible Common Cold (Rhume)");
        decisionTree.put("Do you have a fever? (yes/no):yes,Do you have fatigue or muscle pain? (yes/no):yes,Do you have a cough? (yes/no):no,Do you have a runny nose? (yes/no):no", "Diagnosis: Possible COVID-19");

    }

    private static class SessionState {
        String currentQuestion = "START";
        final Map<String, String> answers = new HashMap<>();
    }

    @Override
    public String startSession() {
        String sessionId = UUID.randomUUID().toString();
        sessions.put(sessionId, new SessionState());
        return sessionId;
    }

    @Override
    public String getNextQuestion(String sessionId) {
        SessionState session = sessions.get(sessionId);
        if (session == null) {
            System.out.println("Session not found");
        }

        String nextStepKey = session.currentQuestion;
        if ("START".equals(nextStepKey)) {
            return decisionTree.get(nextStepKey);
        }
        return null;
    }

    @Override
    public String processAnswer(String sessionId, String answer) {
        SessionState session = sessions.get(sessionId);
        if (session == null) {
            System.out.println("Session not found");
        }

        // Validate answer, again this is not AI :), not every comment explain things is an AI
        if (!"yes".equalsIgnoreCase(answer) && !"no".equalsIgnoreCase(answer)) {
            return "Invalid answer. Please answer 'yes' or 'no'.";
        }

        StringBuilder pathBuilder = new StringBuilder();
        pathBuilder.append(session.currentQuestion);
        if (!"START".equals(session.currentQuestion)) {
            session.answers.put(session.currentQuestion, answer);
        }
        String currentPath = session.answers.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> entry.getKey() + ":" + entry.getValue())
                .collect(Collectors.joining(","));

        if (!currentPath.isEmpty()) {
            currentPath = currentPath + "," + session.currentQuestion + ":" + answer;
        } else {

            currentPath = session.currentQuestion + ":" + answer;
        }

        String nextStep = decisionTree.get(currentPath);
        if (nextStep == null) {
            return "An error occurred or the diagnosis path is incomplete.";
        }


        if (nextStep.startsWith("Diagnosis:")) {
            sessions.remove(sessionId);
            return nextStep;
        } else {

            session.currentQuestion = currentPath;
            return nextStep;

        }
    }
}