package Episante.back.Repository;

import Episante.back.Models.Notification;
import Episante.back.Models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface INotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByPatientId(Long patientId);
//    @Query("SELECT n FROM Notification n JOIN n.patient p WHERE p.email = :email")
//    List<Notification> findByPatientEmail(@Param("email") String email);

    @Modifying
    @Query("DELETE FROM Notification n WHERE n.rendezVous.id = :rendezVousId")
    void deleteByRendezVousId(@Param("rendezVousId") Long rendezVousId);


    Collection<Object> findByPatientEmail(String email);

    List<Notification> getNotificationByPatient(Patient patient);
}
