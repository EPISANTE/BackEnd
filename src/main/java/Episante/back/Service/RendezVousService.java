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
    private NotificationService notificationService;

    /**
     * Réserve un rendez-vous médical pour un patient sur un créneau horaire spécifique.
     * <p>
     * Cette méthode effectue une réservation transactionnelle avec les étapes suivantes :
     * <ol>
     *   <li>Vérification de l'existence du créneau et du patient</li>
     *   <li>Contrôle des contraintes métier (doublon de rendez-vous, créneau déjà réservé)</li>
     *   <li>Création et persistance du rendez-vous</li>
     *   <li>Mise à jour du statut du créneau</li>
     *   <li>Déclenchement d'une notification au patient</li>
     * </ol>
     *
     * @param disponibiliteId Identifiant unique du créneau horaire disponible (non null)
     * @param patientEmail Email unique identifiant le patient (format validé)
     * @return Le rendez-vous confirmé avec ses métadonnées complètes
     * @throws RuntimeException avec message explicite dans les cas suivants :
     *           <ul>
     *             <li>Code 01 : Créneau introuvable</li>
     *             <li>Code 02 : Patient non enregistré</li>
     *             <li>Code 03 : Rendez-vous existant avec le même médecin</li>
     *             <li>Code 04 : Créneau déjà réservé par un autre patient</li>
     *           </ul>
     * @Transactional Garantit l'intégrité des données par rollback automatique en cas d'erreur
     * @see NotificationService#creerNotification(RendezVous) pour le flux de notification
     */
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


        notificationService.creerNotification(rendezVous);

        return rendezVous;
    }


    public List<RendezVous> findByPatientEmail(String email) {
        return null;
    }
}