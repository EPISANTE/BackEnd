package Episante.back.Repository;

import Episante.back.Models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface INotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByPatientId(Long patientId);
    @Query("SELECT n FROM Notification n JOIN n.patient p WHERE p.email = :email")
    List<Notification> findByPatientEmail(@Param("email") String email);
}