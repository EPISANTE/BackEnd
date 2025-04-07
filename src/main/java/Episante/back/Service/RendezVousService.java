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
     * Réserve un créneau de rendez-vous pour un patient donné.
     * Cette méthode est exécutée dans une transaction atomique.
     *
     * @param disponibiliteId L'identifiant unique du créneau de disponibilité à réserver
     * @param patientEmail L'adresse email du patient effectuant la réservation
     * @return Le rendez-vous confirmé avec toutes ses informations
     * @throws RuntimeException avec différents messages selon les cas d'erreur :
     *         - "Créneau introuvable !" si le créneau de disponibilité n'existe pas
     *         - "Ce créneau est déjà réservé !" si le créneau est déjà attribué
     *         - "Patient introuvable !" si aucun patient correspondant à l'email
     * @throws Exception Potentielles exceptions de persistance (JPA/Hibernate) ou d'envoi de notification
     *
     * @implSpec Le flux d'exécution :
     * 1. Vérification de l'existence du créneau
     * 2. Vérification de la disponibilité du créneau
     * 3. Recherche du patient par email
     * 4. Création et enregistrement du rendez-vous
     * 5. Liaison du rendez-vous au créneau de disponibilité
     * 6. Envoi d'une notification de confirmation
     *
     * @implNote Les effets secondaires :
     * - Met à jour l'entité Disponibilite avec la référence au rendez-vous
     * - Persiste une nouvelle entité RendezVous
     * - Déclenche une notification au patient
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



        //notificationRepository.deleteById(notificationRepository.getByRendezVous_Id(rendezVousId).getFirst().getId());

        List<Notification> notifications = notificationRepository.getByRendezVous_Id(rendezVousId);

        if (notifications != null && !notifications.isEmpty()) {
            Notification firstNotification = notifications.get(0);

            Long notificationIdToDelete = firstNotification.getId();


            if (notificationIdToDelete != null) {
                notificationRepository.deleteById(notificationIdToDelete);
                System.out.println("Deleted notification with ID: " + notificationIdToDelete);
            } else {
                System.err.println("Could not delete notification as the first one had a null ID for RendezVous ID: " + rendezVousId);
            }

        } else {
            System.out.println("No notifications found for RendezVous ID: " + rendezVousId + ". Nothing to delete.");
        }

        Disponibilite disponibilite = rendezVous.getDisponibilite();
        disponibiliteRepository.save(disponibilite);

        rendezVousRepository.delete(rendezVous);

        notificationService.creerNotificationAnnulation(rendezVous);
    }


    @Transactional
    public RendezVous modifierRendezVous(Long ancienRendezVousId, Long nouvelleDisponibiliteId) {
        RendezVous ancienRendezVous = rendezVousRepository.findById(ancienRendezVousId)
                .orElseThrow(() -> new RuntimeException("Rendez-vous introuvable"));

        Disponibilite nouvelleDispo = disponibiliteRepository.findById(nouvelleDisponibiliteId)
                .orElseThrow(() -> new RuntimeException("Nouveau créneau introuvable"));

        annulerRendezVous(ancienRendezVousId);

        RendezVous nouveauRendezVous = new RendezVous();
        nouveauRendezVous.setPatient(ancienRendezVous.getPatient());
        nouveauRendezVous.setMedecin(ancienRendezVous.getMedecin());
        nouveauRendezVous.setDisponibilite(nouvelleDispo);
        nouveauRendezVous.setDateHeure(nouvelleDispo.getDateHeure());

        return rendezVousRepository.save(nouveauRendezVous);
    }


}
