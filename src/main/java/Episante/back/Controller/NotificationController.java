package Episante.back.Controller;

import Episante.back.DTO.NotificationDTO;
import Episante.back.Models.Notification;
import Episante.back.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;


    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Notification>> getNotificationsByPatient(@PathVariable Long patientId) {
        List<Notification> notifications = notificationService.getNotificationsByPatient(patientId);
        return ResponseEntity.ok(notifications);
    }
    @GetMapping("/patient/email/{email}")
    public ResponseEntity<?> getNotificationsByPatientEmail(@PathVariable String email) {
        try {
            if (!isValidEmail(email)) {
                return ResponseEntity.badRequest().body("Email invalide");
            }

            List<NotificationDTO> notifications = notificationService.getNotificationsByPatientEmail(email);

            if (notifications.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(notifications);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la récupération des notifications : " + e.getMessage());
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email != null && email.matches(emailRegex);
    }
}



