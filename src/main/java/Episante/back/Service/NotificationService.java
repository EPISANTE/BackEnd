package Episante.back.Service;

import Episante.back.DTO.NotificationDTO;
import Episante.back.Models.Notification;
import Episante.back.Models.RendezVous;
import Episante.back.Repository.INotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class NotificationService {

    @Autowired
    private INotificationRepository notificationRepository;
   @Autowired
   private INotificationRepository rendezVousRepository;

    public void creerNotification(RendezVous rendezVous) {
        Notification notification = new Notification();
        notification.setMessage("Vous avez un rendez-vous avec le médecin " +
                rendezVous.getMedecin().getNom() + " à " + rendezVous.getDateHeure());
        notification.setPatient(rendezVous.getPatient());
        notification.setRendezVous(rendezVous);
        notification.setDateCreation(LocalDateTime.now());

        notificationRepository.save(notification);
    }

    public List<Notification> getNotificationsByPatient(Long patientId) {
        return notificationRepository.findByPatientId(patientId);
    }

    public List<NotificationDTO> getNotificationsByPatientEmail(String email) {
        return notificationRepository.findByPatientEmail(email)
                .stream()
                .map(NotificationDTO::new)
                .collect(Collectors.toList());
    }
}