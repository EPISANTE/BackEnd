package Episante.back.Service;

import Episante.back.Models.*;
import Episante.back.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RendezVousService {

    @Autowired
    private RendezVousRepository rendezVousRepository;

    @Autowired
    private DisponibiliteRepository disponibiliteRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private INotificationRepository notificationRepository;

    @Transactional
    public RendezVous reserverRendezVous(Long disponibiliteId, Long patientId) {
        Optional<Disponibilite> dispoOpt = disponibiliteRepository.findById(disponibiliteId);
        Optional<Patient> patientOpt = patientRepository.findById(patientId);

        if (dispoOpt.isEmpty() || patientOpt.isEmpty()) {
            throw new RuntimeException("Disponibilité ou patient introuvable !");
        }

        Disponibilite disponibilite = dispoOpt.get();
        Patient patient = patientOpt.get();

        if (disponibilite.getRendezVous() != null) {
            throw new RuntimeException("Ce créneau est déjà réservé !");
        }


        RendezVous rendezVous = new RendezVous();
        rendezVous.setDateHeure(disponibilite.getDateHeure());
        rendezVous.setPatient(patient);
        rendezVous.setStatut(StatutRendezVous.CONFIRME);

        rendezVous = rendezVousRepository.save(rendezVous);


        disponibilite.setRendezVous(rendezVous);
        disponibiliteRepository.save(disponibilite);

        return rendezVous;
    }

    @Scheduled(cron = "0 0 9 * * ?")
    public void envoyerNotifications() {
        LocalDateTime maintenant = LocalDateTime.now();
        LocalDateTime dans24h = maintenant.plusHours(24);

        List<RendezVous> rdvs = rendezVousRepository.findByDateHeureBetween(maintenant, dans24h);

        for (RendezVous rdv : rdvs) {
            Notification notification = new Notification();
            notification.setDate(LocalDate.now());
            notification.setRendezVous(rdv);
            notificationRepository.save(notification);
            System.out.println("📩 Notification envoyée pour le rendez-vous du " + rdv.getDateHeure());
        }
    }
}
