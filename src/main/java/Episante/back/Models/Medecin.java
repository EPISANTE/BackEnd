package Episante.back.Models;

import jakarta.persistence.*;
import java.util.List;
import lombok.Data;


@Data
@Entity
public class Medecin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @Enumerated(EnumType.STRING) //je stocke ici enum comme chaine caractere
    private Specialite specialite;

    @OneToMany(mappedBy = "medecin", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Disponibilite> disponibilites;

    @OneToMany(mappedBy = "medecin", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RendezVous> rendezVous;

}