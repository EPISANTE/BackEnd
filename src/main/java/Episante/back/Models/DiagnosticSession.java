package Episante.back.Models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@Entity
@Table(name = "diagnostic_sessions")
public class DiagnosticSession {
    @Id
    private String sessionId;

    @ElementCollection
    @CollectionTable(name = "symptom_responses",
            joinColumns = @JoinColumn(name = "session_id"))
    @MapKeyColumn(name = "symptom_id")
    @Column(name = "response")
    private Map<String, Boolean> symptomResponses = new HashMap<>();

    @ElementCollection
    @CollectionTable(name = "disease_probabilities",
            joinColumns = @JoinColumn(name = "session_id"))
    @MapKeyColumn(name = "disease_id")
    @Column(name = "probability")
    private Map<String, Double> diseaseProbabilities = new HashMap<>();

    @CreationTimestamp
    private Date createdAt;


    public DiagnosticSession() {
    }

    public DiagnosticSession(String sessionId, Map<String, Boolean> symptomResponses, Map<String, Double> diseaseProbabilities, Date createdAt) {
        this.sessionId = sessionId;
        this.symptomResponses = symptomResponses;
        this.diseaseProbabilities = diseaseProbabilities;
        this.createdAt = createdAt;
    }
}
