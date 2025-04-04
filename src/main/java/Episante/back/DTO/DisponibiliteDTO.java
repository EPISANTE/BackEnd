
package Episante.back.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public record DisponibiliteDTO(
        Long id,
        LocalDate date,
        String jour,
        LocalTime heureDebut,
        LocalTime heureFin,
        boolean reserve
) {}
