package Episante.back.DTO;


import lombok.Data;

@Data
public class PatientBilanDTO {

    private String nom;
    private String prenom;
    private String messageBilan;


    public PatientBilanDTO(String nom, String prenom, String bilan) {
        this.nom = nom;
        this.prenom = prenom;
        this.messageBilan = bilan;
    }
}
