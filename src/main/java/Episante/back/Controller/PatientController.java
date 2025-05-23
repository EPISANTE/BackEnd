package Episante.back.Controller;

import Episante.back.DTO.LoginDTO;
import Episante.back.DTO.PatientBilanDTO;
import Episante.back.Models.Patient;
import Episante.back.Service.PatientService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/patients")
public class PatientController {


    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);


    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<Patient> getAllPatients() {
        logger.info("Get all patients");
        return patientService.getAllPatients();
    }



    @PostMapping("/register")
    public void createPatient(@Valid @RequestBody Patient patient) {
        patientService.Inscription(patient);
        logger.info("Patient created successfully: {}", patient.getNom() + " " + patient.getPrenom());
    }

    @PostMapping("/login")
    public ResponseEntity<String> logPatient(@Valid @RequestBody LoginDTO loginRequest) {
        try {
            patientService.Login(loginRequest.getEmail(), loginRequest.getPassword());
            logger.info("Patient logged in successfully: {}", patientService.getByEmail(loginRequest.getEmail()).getNom() + " " + patientService.getByEmail(loginRequest.getEmail()).getPrenom());
            return ResponseEntity.ok("Connexion réussie");
        } catch (Exception e) {
            logger.error("Erreur lors de la connexion : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou mot de passe incorrect");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Patient> deletePatient(@PathVariable Long id) {
        Optional<Patient> patient = patientService.getById(id);
        patientService.deletePatient(patient);
        logger.info("Patient deleted successfully: {}", id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> getPatientInfo(@RequestParam String email) {
        logger.info("Requête reçue pour l'email : {}", email);
        if (email == null || email.isEmpty()) {
            logger.error("Email manquant ou vide");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "L'email est requis"));
        }

        Patient patient = patientService.getByEmail(email);
        if (patient == null) {
            logger.error("Patient introuvable pour l'email : {}", email);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Patient introuvable avec l'email fourni"));
        }

        Map<String, Object> patientInfo = Map.of(
                "nom", patient.getNom(),
                "prenom", patient.getPrenom(),
                "email", patient.getEmail(),
                "telephone", patient.getTelephone(),
                "adresse", patient.getAdresse(),
                "taille", patient.getTaille(),
                "poids", patient.getPoids(),
                "sexe", String.valueOf(patient.getSexe()),
                "age", patient.getAge()
        );

        logger.info("Informations du patient récupérées : {}", patientInfo);
        return ResponseEntity.status(HttpStatus.OK).body(patientInfo);
    }

    @GetMapping("/bilan")
    public ResponseEntity<PatientBilanDTO> generatePatientBilan(@Valid @RequestParam String email) {
        try {
            Patient patient = patientService.getByEmail(email);

            System.out.println("Patient found: " + patient.getEmail());

            String bilan = patientService.BilanS(patient);

            System.out.println("Bilan generated: " + bilan);

            PatientBilanDTO bilanDTO = new PatientBilanDTO(patient.getNom(), patient.getPrenom(), bilan);
            return ResponseEntity.status(HttpStatus.OK).body(bilanDTO);
        } catch (Exception e) {

            e.printStackTrace();
            System.err.println("Error in /bilan: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/stress")
    public ResponseEntity<PatientBilanDTO> stressLvl(
            @RequestParam String email,
            @RequestParam List<Integer> sr){
        try {
            Patient patient = patientService.getByEmail(email);
            String bilan = patientService.nivStress(sr , patient);
            PatientBilanDTO bilanDTO = new PatientBilanDTO(patient.getNom(), patient.getPrenom(), bilan);
            return ResponseEntity.status(HttpStatus.OK).body(bilanDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<Patient> getPatientByEmail(@PathVariable String email) {
        try {
            Patient patient = patientService.getByEmail(email);
            return ResponseEntity.ok(patient);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(null);
        }
    }


}