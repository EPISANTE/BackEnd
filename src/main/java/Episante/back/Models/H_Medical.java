package Episante.back.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class H_Medical {

    @Id
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    @ManyToMany(fetch = FetchType.LAZY) // Added FetchType.LAZY (good practice)
    // Removed @JoinColumn
    @JoinTable(
            name = "h_medical_antecedants",  // CHOOSE the actual name of your join table
            joinColumns = @JoinColumn(name = "h_medical_patient_id"), // Column in join table pointing to H_Medical's PK (which is patient_id)
            inverseJoinColumns = @JoinColumn(name = "antecedant_id") // Column in join table pointing to Antecedant's PK
    )
    // Consider using Set<Antecedant> instead of List<Antecedant> for ManyToMany
    private List<Antecedant> antecedantList = new ArrayList<>();



}
