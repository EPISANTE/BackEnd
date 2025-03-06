package Episante.back.Models;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
@Entity
@Table(name = "diseases")
public class Disease {
    @Id
    private String id;
    private String name;
    private String description;

    @ElementCollection
    @CollectionTable(name = "disease_symptom_weights",
            joinColumns = @JoinColumn(name = "disease_id"))
    @MapKeyColumn(name = "symptom_id")
    @Column(name = "weight")
    private Map<String, Double> symptomWeights = new HashMap<>();

    public Disease() {
    }

    public Disease(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public Disease(String id, String name, String description, Map<String, Double> symptomWeights) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.symptomWeights = symptomWeights;
    }



}
