package Episante.back.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private String prenom;
    private String age;
    private String adresse;
    @Column(unique = true, nullable = false)
    private String email;
    @Size(min = 6, message = "Le mot de passe doit contenir au moins 6 caractères.")
    private String mdp;
    private String telephone;
    private String taille;
    private String poids;
    private Sexe sexe;
    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private H_Medical h_m;



}