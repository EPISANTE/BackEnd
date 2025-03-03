package Episante.back.Models;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

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



}
