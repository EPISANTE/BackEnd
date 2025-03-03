package Episante.back.Repository;

import Episante.back.Models.DiagnosticSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosticSessionRepository extends JpaRepository<DiagnosticSession, String> {
}
