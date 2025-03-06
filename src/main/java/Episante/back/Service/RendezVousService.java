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
    private INotificationRepository notificationRepository;

    @Autowired
    private SmsService smsService;


        @Transactional
        public RendezVous reserverRendezVous(Long disponibiliteId, Long patientId) {
            Optional<Disponibilite> dispoOpt = disponibiliteRepository.findById(disponibiliteId);
            Optional<Patient> patientOpt = patientRepository.findById(patientId);

            if (dispoOpt.isEmpty() || patientOpt.isEmpty()) {
                throw new RuntimeException("Disponibilite ou patient introuvable !");
            }

            Disponibilite disponibilite = dispoOpt.get();
            Patient patient = patientOpt.get();

            if (disponibilite.getRendezVous() != null) {
                throw new RuntimeException("Ce creneau est deja reserve !");
            }

            // Créer le rendez-vous
            RendezVous rendezVous = new RendezVous();
            rendezVous.setDateHeure(disponibilite.getDate().atTime(disponibilite.getPeriode().getHeureDebut(), 0)); // Convertir LocalDate en LocalDateTime
            rendezVous.setPatient(patient);
            rendezVous.setMedecin(disponibilite.getMedecin());
            rendezVous.setStatut(StatutRendezVous.CONFIRME);
            rendezVous.setDisponibilite(disponibilite);

            rendezVous = rendezVousRepository.save(rendezVous);

            // Lier la disponibilité au rendez-vous
            disponibilite.setRendezVous(rendezVous);
            disponibiliteRepository.save(disponibilite);

            // Envoyer une notification de confirmation
            String message = "Bonjour " + patient.getNom() + ", votre rendez-vous avec Dr. "
                    + disponibilite.getMedecin().getNom() + " est confirme pour le "
                    + rendezVous.getDateHeure() + ".";
            smsService.envoyerSms(patient.getTelephone(), message);

            return rendezVous;
        }



    public List<RendezVous> getRendezVousParPatient(Long patientId) {
        return rendezVousRepository.findByPatientId(patientId);
    }
    @Transactional
    public void annulerRendezVous(Long rdvId) {
        Optional<RendezVous> rdvOpt = rendezVousRepository.findById(rdvId);

        if (rdvOpt.isEmpty()) {
            throw new RuntimeException("Rendez-vous introuvable !");
        }

        RendezVous rdv = rdvOpt.get();
        rdv.setStatut(StatutRendezVous.ANNULE);
        rendezVousRepository.save(rdv);
    }

}
