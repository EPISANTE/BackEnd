package Episante.back;


import Episante.back.Models.Patient ;
import Episante.back.Service.PatientService ;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PatientServiceTest {

    @Mock
    private IPatientDao patientDao;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private PatientService patientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerPatient_successfulRegistration() {
        Patient patient = new Patient();
        patient.setIdentifier("testUser");
        patient.setPassword("password");
        patient.setNom("Test");
        patient.setPrenom("User");

        when(patientDao.findByIdentifier("testUser")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password")).thenReturn("hashedPassword");

        String result = patientService.registerPatient(patient);

        assertEquals("Patient registered successfully!", result);
        verify(patientDao, times(1)).save(patient);
        assertEquals("hashedPassword", patient.getPassword());
        // You might also want to verify the timestamps are set, though it's a bit more involved.
    }

    @Test
    void registerPatient_duplicateIdentifier() {
        Patient patient = new Patient();
        patient.setIdentifier("existingUser");
        when(patientDao.findByIdentifier("existingUser")).thenReturn(Optional.of(new Patient()));

        String result = patientService.registerPatient(patient);

        assertEquals("Patient with this identifier already exists.", result);
        verify(patientDao, never()).save(any());
    }

    @Test
    void loginPatient_successfulLogin() {
        String identifier = "testUser";
        String password = "password";
        String hashedPassword = "hashedPassword";
        Patient patient = new Patient();
        patient.setIdentifier(identifier);
        patient.setPassword(hashedPassword);
        patient.setNom("Test");
        patient.setPrenom("User");

        when(patientDao.findByIdentifier(identifier)).thenReturn(Optional.of(patient));
        when(passwordEncoder.matches(password, hashedPassword)).thenReturn(true);

        String result = patientService.loginPatient(identifier, password);

        assertEquals("Login successful: Test User", result);
    }

    @Test
    void loginPatient_invalidPassword() {
        String identifier = "testUser";
        String password = "wrongPassword";
        String hashedPassword = "hashedPassword";
        Patient patient = new Patient();
        patient.setIdentifier(identifier);
        patient.setPassword(hashedPassword);

        when(patientDao.findByIdentifier(identifier)).thenReturn(Optional.of(patient));
        when(passwordEncoder.matches(password, hashedPassword)).thenReturn(false);

        String result = patientService.loginPatient(identifier, password);

        assertEquals("Invalid password.", result);
    }

    @Test
    void loginPatient_patientNotFound() {
        String identifier = "nonExistingUser";
        String password = "password";

        when(patientDao.findByIdentifier(identifier)).thenReturn(Optional.empty());

        String result = patientService.loginPatient(identifier, password);

        assertEquals("Patient with this identifier not found.", result);
    }
}
