package Episante.back.Repository;

import Episante.back.Models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    boolean existsByQuestionText(String questionText);
}
