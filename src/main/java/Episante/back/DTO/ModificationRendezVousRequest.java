package Episante.back.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ModificationRendezVousRequest {
    @NotNull(message = "L'ID du nouveau créneau est obligatoire")
    private Long nouvelleDisponibiliteId;
}
