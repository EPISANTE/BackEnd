package Episante.back.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<RendezVous> rendezVous = new ArrayList<>();

}