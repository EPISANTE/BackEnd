package Episante.back.Service;

import Episante.back.Models.*;
import Episante.back.Repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RendezVousServiceTest {

    @InjectMocks
    private RendezVousService rendezVousService;

    @Mock
    private RendezVousRepository rendezVousRepository;
    @Mock
    private DisponibiliteRepository disponibiliteRepository;
    @Mock
    private INotificationRepository notificationRepository;
    @Mock
    private PatientRepository patientRepository;
    @Mock
    private NotificationService notificationService;

    private RendezVous testRendezVous;
    private Disponibilite testDisponibilite;
    private Notification testNotification;
    private Long testRendezVousId = 1L;
    private Long testNotificationId = 10L;

    @BeforeEach
    void setUp() {

        testDisponibilite = new Disponibilite();
        testDisponibilite.setId(5L);


        Patient testPatient = new Patient();
        testPatient.setId(2L);

        Medecin testMedecin = new Medecin();
        testMedecin.setId(3L);

        testRendezVous = new RendezVous();
        testRendezVous.setId(testRendezVousId);
        testRendezVous.setDisponibilite(testDisponibilite);
        testRendezVous.setPatient(testPatient);
        testRendezVous.setMedecin(testMedecin);
        testRendezVous.setDateHeure(LocalDateTime.now().plusDays(1));
        testRendezVous.setStatut(StatutRendezVous.CONFIRME);

        testNotification = new Notification();
        testNotification.setId(testNotificationId);
        testNotification.setRendezVous(testRendezVous);
    }

    @Test
    @DisplayName("annulerRendezVous should delete RDV, Notification and update Disponibilite when found")
    void annulerRendezVous_ExistingRdv_DeletesEntitiesAndNotifies() {

        when(rendezVousRepository.findById(testRendezVousId)).thenReturn(Optional.of(testRendezVous));

        when(notificationRepository.getByRendezVous_Id(testRendezVousId)).thenReturn(List.of(testNotification));


        when(disponibiliteRepository.save(any(Disponibilite.class))).thenAnswer(invocation -> invocation.getArgument(0));


        rendezVousService.annulerRendezVous(testRendezVousId);


        verify(notificationRepository, times(1)).deleteById(testNotificationId);

        verify(disponibiliteRepository, times(1)).save(testDisponibilite);

        verify(rendezVousRepository, times(1)).delete(testRendezVous);

        verify(notificationService, times(1)).creerNotificationAnnulation(testRendezVous);
    }

    @Test
    @DisplayName("annulerRendezVous should do nothing if no notifications found")
    void annulerRendezVous_NoNotifications_SkipsNotificationDeletion() {

        when(rendezVousRepository.findById(testRendezVousId)).thenReturn(Optional.of(testRendezVous));

        when(notificationRepository.getByRendezVous_Id(testRendezVousId)).thenReturn(new ArrayList<>());
        when(disponibiliteRepository.save(any(Disponibilite.class))).thenAnswer(invocation -> invocation.getArgument(0));


        rendezVousService.annulerRendezVous(testRendezVousId);


        verify(notificationRepository, never()).deleteById(anyLong());
        verify(disponibiliteRepository, times(1)).save(testDisponibilite);
        verify(rendezVousRepository, times(1)).delete(testRendezVous);
        verify(notificationService, times(1)).creerNotificationAnnulation(testRendezVous);
    }


    @Test
    @DisplayName("annulerRendezVous should throw exception if RDV not found")
    void annulerRendezVous_RdvNotFound_ThrowsRuntimeException() {

        when(rendezVousRepository.findById(testRendezVousId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> rendezVousService.annulerRendezVous(testRendezVousId))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Rendez-vous introuvable !");

        verify(notificationRepository, never()).deleteById(anyLong());
        verify(disponibiliteRepository, never()).save(any(Disponibilite.class));
        verify(rendezVousRepository, never()).delete(any(RendezVous.class));
        verify(notificationService, never()).creerNotificationAnnulation(any(RendezVous.class));
    }

}
