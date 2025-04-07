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




}
