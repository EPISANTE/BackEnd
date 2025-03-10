package Episante.back.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String questionText;
    private String questionType;
    private String options;
    private String unit;

    public Question() {
    }

    public Question(String questionText, String questionType, String options,String unit) {
        this.questionText = questionText;
        this.questionType = questionType;
        this.options = options;
        this.unit = unit;
    }

}
