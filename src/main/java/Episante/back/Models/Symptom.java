package Episante.back.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "symptoms")
public class Symptom {
    @Id
    private String id;
    private String question;
    private String description;

    public Symptom() {
    }

    public Symptom(String id, String question, String description) {
        this.id = id;
        this.question = question;
        this.description = description;
    }
}
