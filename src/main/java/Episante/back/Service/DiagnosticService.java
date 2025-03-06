package Episante.back.Service;

public interface DiagnosticService {
    public String  startSession();
    public String getNextQuestion(String sessionId);
    public String processAnswer(String sessionId, String answer);
}
