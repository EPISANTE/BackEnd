package Episante.back.Controller;

import Episante.back.Models.Notification;
import Episante.back.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
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

}
