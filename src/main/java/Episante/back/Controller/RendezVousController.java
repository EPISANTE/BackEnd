package Episante.back.Controller;

import Episante.back.Models.RendezVous;
import Episante.back.Service.RendezVousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rendezvous")
public class RendezVousController {

    @Autowired
    private RendezVousService rendezVousService;

    @PostMapping("/reserver")
    public ResponseEntity<RendezVous> reserverRendezVous(
            @RequestParam Long disponibiliteId,
            @RequestParam String email) {
        RendezVous rendezVous = rendezVousService.reserverRendezVous(disponibiliteId, email);
        return ResponseEntity.ok(rendezVous);
    }



    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<RendezVous>> getRendezVousParPatient(@PathVariable Long patientId) {
        List<RendezVous> rdvs = rendezVousService.getRendezVousParPatient(patientId);
        return ResponseEntity.ok(rdvs);
    }
    @PutMapping("/annuler/{rdvId}")
    public ResponseEntity<String> annulerRendezVous(@PathVariable Long rdvId) {
        rendezVousService.annulerRendezVous(rdvId);
        return ResponseEntity.ok("Rendez-vous annulé avec succès.");
    }

}


