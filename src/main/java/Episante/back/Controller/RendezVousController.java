package Episante.back.Controller;

import Episante.back.Models.RendezVous;
import Episante.back.Service.RendezVousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rendezvous")
public class RendezVousController {

    @Autowired
    private RendezVousService rendezVousService;

    @PostMapping("/reserver")
    public ResponseEntity<RendezVous> reserverRendezVous(
            @RequestParam Long disponibiliteId,
            @RequestParam Long patientId) {
        RendezVous rendezVous = rendezVousService.reserverRendezVous(disponibiliteId, patientId);
        return ResponseEntity.ok(rendezVous);
    }
}
