package Episante.back.Service;

import Episante.back.Models.*;
import Episante.back.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private NotificationService notificationService; // Injectez le NotificationService

    @Transactional
    public RendezVous reserverRendezVous(Long disponibiliteId, String patientEmail) {
        Optional<Disponibilite> dispoOpt = disponibiliteRepository.findById(disponibiliteId);
        Optional<Patient> patientOpt = patientRepository.findByEmail(patientEmail);

        if (dispoOpt.isEmpty()) {
            throw new RuntimeException("Disponibilité introuvable !");
        }

        if (patientOpt.isEmpty()) {
            throw new RuntimeException("Patient introuvable !");
        }

        Disponibilite disponibilite = dispoOpt.get();
        Patient patient = patientOpt.get();

        List<RendezVous> rendezVousExistants = rendezVousRepository.findByPatientAndMedecin(patient, disponibilite.getMedecin());
        if (!rendezVousExistants.isEmpty()) {
            throw new RuntimeException("Vous avez déjà un rendez-vous avec ce médecin.");
        }

        if (disponibilite.getRendezVous() != null) {
            throw new RuntimeException("Ce créneau est déjà réservé !");
        }

        RendezVous rendezVous = new RendezVous();
        rendezVous.setDateHeure(disponibilite.getDate().atTime(disponibilite.getPeriode().getHeureDebut(), 0));
        rendezVous.setPatient(patient);
        rendezVous.setMedecin(disponibilite.getMedecin());
        rendezVous.setStatut(StatutRendezVous.CONFIRME);
        rendezVous.setDisponibilite(disponibilite);

        rendezVous = rendezVousRepository.save(rendezVous);

        disponibilite.setRendezVous(rendezVous);
        disponibiliteRepository.save(disponibilite);

        // Créer une notification après la réservation réussie
        notificationService.creerNotification(rendezVous);

        return rendezVous;
    }


    public List<RendezVous> findByPatientEmail(String email) {
        return null;
    }
}