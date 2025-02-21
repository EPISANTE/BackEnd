package Episante.back.Service;

import Episante.back.Models.Medecin;

import java.util.List;
import java.util.Optional;

public interface ImedecinService {
    List<Medecin> getAllMedecins();
    Optional<Medecin> getMedecinById(Long id);
    Medecin createMedecin(Medecin medecin);
    Medecin updateMedecin(Long id, Medecin medecin);
    void deleteMedecin(Long id);
}