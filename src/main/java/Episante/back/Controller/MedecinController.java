package Episante.back.Controller;

import Episante.back.DTO.DisponibiliteDTO;
import Episante.back.DTO.MedecinDTO;
import Episante.back.Models.Disponibilite;
import Episante.back.Models.JourSemaine;
import Episante.back.Models.Medecin;
import Episante.back.Models.Specialite;
import Episante.back.Repository.MedecinRepository;
import Episante.back.Service.MedecinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/medecins")
public class MedecinController {
    private final MedecinService medecinService;
    private final MedecinRepository medecinRepository;

    public MedecinController(MedecinService medecinService, MedecinRepository medecinRepository) {
        this.medecinService = medecinService;
        this.medecinRepository = medecinRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medecin> getMedecinAvecDisponibilites(@PathVariable Long id) {
        return ResponseEntity.ok(medecinService.getMedecinAvecDisponibilites(id));
    }

    @PostMapping("/creer")
    public ResponseEntity<Medecin> creerMedecin(@RequestBody Medecin medecin) {
        return ResponseEntity.ok(medecinService.sauvegarderMedecin(medecin));
    }

    @GetMapping("/")
    public ResponseEntity<List<Medecin>> getAllMedecins() {
        return ResponseEntity.ok(medecinService.getAllMedecins());
    }

    @GetMapping("/specialite/{specialite}")
    public ResponseEntity<List<MedecinDTO>> getMedecinsParSpecialite(@PathVariable String specialite) {
        try {
            Specialite specialiteEnum = Specialite.valueOf(specialite.toUpperCase());

            List<Medecin> medecins = medecinRepository.findBySpecialite(specialiteEnum);

            List<MedecinDTO> dtos = medecins.stream()
                    .map(medecin -> new MedecinDTO(
                            medecin.getId(),
                            medecin.getNom(),
                            medecin.getSpecialite().name(),
                            medecin.getDisponibilites().stream()
                                    .map(dispo -> new DisponibiliteDTO(
                                            dispo.getId(),
                                            dispo.getDateHeure().toLocalDate(),
                                            dispo.getJour().name(),
                                            dispo.getDateHeure().toLocalTime(),
                                            dispo.getDateHeure().toLocalTime().plusMinutes(30),
                                            dispo.getRendezVous() != null
                                    ))
                                    .collect(Collectors.toList())
                    ))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(dtos);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/specialites")
    public ResponseEntity<List<String>> getAllSpecialites() {
        return ResponseEntity.ok(medecinService.getAllSpecialites());
    }
    @PostMapping("/creer-en-masse")
    public ResponseEntity<List<Medecin>> creerMedecinsEnMasse(@RequestParam int nombre) {
        return ResponseEntity.ok(medecinService.creerMedecinsEnMasse(nombre));
    }

    @PostMapping("/generer-disponibilites")
    public ResponseEntity<Map<String, String>> genererDisponibilites() {
        medecinService.genererDisponibilitesAleatoires();
        return ResponseEntity.ok(Map.of("message", "Disponibilites generees avec succes !"));
    }

    @GetMapping("/disponibilites")
    public ResponseEntity<List<Disponibilite>> getDisponibilites(
            @RequestParam(required = false) Long medecinId,
            @RequestParam(required = false) JourSemaine jour) {
        return ResponseEntity.ok(medecinService.getDisponibilites(medecinId, jour));


    }
}