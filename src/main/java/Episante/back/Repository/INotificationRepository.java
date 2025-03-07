package Episante.back.Repository;

import Episante.back.Models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface INotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByPatientId(Long patientId);
}