package Episante.back.Service;

import Episante.back.DTO.NotificationDTO;
import Episante.back.Models.Notification;
import Episante.back.Models.RendezVous;
import Episante.back.Repository.INotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


@Service
public class NotificationService {

    @Autowired
    private INotificationRepository notificationRepository;

    public void creerNotification(RendezVous rendezVous) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE d MMMM yyyy 'à' HH'h'mm", Locale.FRENCH);
        String dateFormatee = rendezVous.getDateHeure().format(formatter);

        Notification notification = new Notification();
        notification.setMessage("Rendez-vous avec Dr " + rendezVous.getMedecin().getNom()
                + " (" + rendezVous.getMedecin().getSpecialite().getLibelle()
                + ") le " + dateFormatee);
        notification.setPatient(rendezVous.getPatient());
        notification.setRendezVous(rendezVous);
        notification.setDateCreation(LocalDateTime.now());
        notification.setDateRdv(rendezVous.getDateHeure());

        notificationRepository.save(notification);
    }

    public List<NotificationDTO> getNotificationsByPatientEmail(String email) {
        return notificationRepository.findByPatientEmail(email)
                .stream()
                .map(notification -> new NotificationDTO(notification))
                .sorted(Comparator.comparing(NotificationDTO::getDateRdv))
                .collect(Collectors.toList());
    }
    public List<Notification> getNotificationsByPatient(Long patientId) {
        return notificationRepository.findByPatientId(patientId);
    }

    public void creerNotificationAnnulation(RendezVous rendezVous) {
        Notification notification = new Notification();
        notification.setMessage("Annulation du rendez-vous avec "
                + rendezVous.getMedecin().getNom() + " prévu le "
                + rendezVous.getDateHeure());
        notification.setPatient(rendezVous.getPatient());
        notification.setDateCreation(LocalDateTime.now());
        notificationRepository.save(notification);
    }



}