package Episante.back.Controller;

import Episante.back.DTO.ModificationRendezVousRequest;
import Episante.back.Models.RendezVous;
import Episante.back.Service.RendezVousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rendezvous")
public class RendezVousController {

    @Autowired
    private RendezVousService rendezVousService;

    @PostMapping("/reserver")
    public ResponseEntity<RendezVous> reserverRendezVous(
            @RequestBody Map<String, Object> requestBody) {
        try {
            Long disponibiliteId = Long.valueOf(requestBody.get("disponibiliteId").toString());
            String patientEmail = requestBody.get("patientEmail").toString();

            RendezVous rendezVous = rendezVousService.reserverRendezVous(disponibiliteId, patientEmail);
            return ResponseEntity.ok(rendezVous);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

        @GetMapping("/rendezvs")
        public ResponseEntity<List<RendezVous>> getRendezVousByPatientEmail(@RequestParam String email) {
            try {
                List<RendezVous> rendezVous = rendezVousService.findByPatientEmail(email);
                return ResponseEntity.ok(rendezVous);
            } catch (RuntimeException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        }



    @DeleteMapping("/annuler/{id}")
    public ResponseEntity<String> annulerRendezVous(@PathVariable Long id) {
        try {
            rendezVousService.annulerRendezVous(id);
            return ResponseEntity.ok("Rendez-vous annulé avec succès");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PostMapping("/rendezvous/{id}/modifier")
    public ResponseEntity<RendezVous> modifierRendezVous(
            @PathVariable Long id,
            @RequestBody ModificationRendezVousRequest request
    ) {
        return ResponseEntity.ok(rendezVousService.modifierRendezVous(id, request.getNouvelleDisponibiliteId()));
    }

}

