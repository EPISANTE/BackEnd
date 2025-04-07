package Episante.back.Service;

import Episante.back.Models.Patient;
import Episante.back.Models.Sexe;
import Episante.back.Repository.IPatientrepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @InjectMocks
    private PatientService patientService;


    @Mock
    private IPatientrepository patientDao;

    private Patient testPatient;

    @BeforeEach
    void setUp() {
        testPatient = new Patient();
        testPatient.setId(1L);
        testPatient.setNom("TestNom");
        testPatient.setPrenom("TestPrenom");
        testPatient.setEmail("test@example.com");
        testPatient.setMdp("password123");
        testPatient.setAge("30");
        testPatient.setPoids("70");
        testPatient.setTaille("175");
        testPatient.setSexe(Sexe.HOMME);
    }

    @Test
    @DisplayName("BilanS should calculate normal BMI for standard male patient")
    void bilanS_StandardMale_ReturnsNormalWeightMessage() {


        String bilan = patientService.BilanS(testPatient);

        assertThat(bilan).isNotNull();
        assertThat(bilan).contains("Bonjour TestPrenom TestNom");
        assertThat(bilan).contains("votre IMC est 22.86");
        assertThat(bilan).contains("poids normal");
    }

    @Test
    @DisplayName("BilanS should calculate underweight BMI for thin female patient")
    void bilanS_ThinFemale_ReturnsUnderweightMessage() {
        testPatient.setSexe(Sexe.FEMME);
        testPatient.setPoids("45");
        testPatient.setTaille("170");


        String bilan = patientService.BilanS(testPatient);

        assertThat(bilan).isNotNull();
        assertThat(bilan).contains("Bonjour TestPrenom TestNom");
        assertThat(bilan).contains("votre IMC est 15.57");
        assertThat(bilan).contains("insuffisance pondérale");
    }

    @Test
    @DisplayName("BilanS should calculate overweight BMI for older patient")
    void bilanS_OlderPatient_ReturnsOverweightMessage() {

        testPatient.setAge("70");
        testPatient.setPoids("85");
        testPatient.setTaille("175");

        String bilan = patientService.BilanS(testPatient);

        assertThat(bilan).isNotNull();
        assertThat(bilan).contains("Bonjour TestPrenom TestNom");
        assertThat(bilan).contains("votre IMC est 27.76");
        assertThat(bilan).contains("surpoids");
    }

    @Test
    @DisplayName("BilanS should throw exception for invalid numeric input")
    void bilanS_InvalidInput_ThrowsIllegalArgumentException() {
        testPatient.setPoids("seventy");

        assertThatThrownBy(() -> patientService.BilanS(testPatient))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Les valeurs de poids, taille ou âge sont invalides");
    }

    @Test
    @DisplayName("BilanS should throw exception for non-positive input")
    void bilanS_NonPositiveInput_ThrowsIllegalArgumentException() {
        testPatient.setAge("0");

        assertThatThrownBy(() -> patientService.BilanS(testPatient))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Les valeurs de poids, taille et âge doivent être positives");
    }

    @Test
    @DisplayName("BilanS should throw exception for null patient")
    void bilanS_NullPatient_ThrowsIllegalArgumentException() {

        Patient nullPatient = null;


        assertThatThrownBy(() -> patientService.BilanS(nullPatient))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Le patient ne peut pas être null");
    }


}