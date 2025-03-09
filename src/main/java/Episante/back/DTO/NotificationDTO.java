package Episante.back.DTO;

import Episante.back.Models.Notification;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class NotificationDTO {
    private Long id;
    private String message;
    private LocalDateTime dateCreation;
    private String medecinNom;
    private String specialite;
    private LocalDateTime dateRdv;


    public NotificationDTO(Notification notification) {
        this.id = notification.getId();
        this.message = notification.getMessage();
        this.dateCreation = notification.getDateCreation();
        this.medecinNom = notification.getRendezVous().getMedecin().getNom();
        this.specialite = notification.getRendezVous().getMedecin().getSpecialite().name();
        this.dateRdv = notification.getRendezVous().getDateHeure();
    }

}