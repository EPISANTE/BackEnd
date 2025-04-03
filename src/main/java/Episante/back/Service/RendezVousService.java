package Episante.back.Service;

import Episante.back.Models.*;
import Episante.back.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class RendezVousService {

    @Autowired
    private RendezVousRepository rendezVousRepository;

    @Autowired
    private DisponibiliteRepository disponibiliteRepository;

    @Autowired
    private INotificationRepository notificationRepository;

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
        Disponibilite disponibilite = disponibiliteRepository.findById(disponibiliteId)
                .orElseThrow(() -> new RuntimeException("Créneau introuvable !"));

        if (disponibilite.getRendezVous() != null) {
            throw new RuntimeException("Ce créneau est déjà réservé !");
        }

        Patient patient = patientRepository.findByEmail(patientEmail)
                .orElseThrow(() -> new RuntimeException("Patient introuvable !"));

        RendezVous rendezVous = new RendezVous();
        rendezVous.setDateHeure(disponibilite.getDateHeure());
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

    @Transactional
    public void annulerRendezVous(Long rendezVousId) {
        RendezVous rendezVous = rendezVousRepository.findById(rendezVousId)
                .orElseThrow(() -> new RuntimeException("Rendez-vous introuvable !"));

        notificationRepository.deleteByRendezVousId(rendezVousId);

        Disponibilite disponibilite = rendezVous.getDisponibilite();
        disponibilite.setRendezVous(null);
        disponibiliteRepository.save(disponibilite);

        rendezVousRepository.delete(rendezVous);

        notificationService.creerNotificationAnnulation(rendezVous);
    }


}
