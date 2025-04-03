package Episante.back.DTO;


import java.util.List;

public record MedecinDTO(
            Long id,
            String nom,
            String specialite,
            List<DisponibiliteDTO> disponibilites
    ) {}
