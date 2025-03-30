package Episante.back;

import Episante.back.Models.Patient;
import Episante.back.Service.MockPatientGenerator;
import Episante.back.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class EpisanteAPP implements CommandLineRunner {

	@Autowired
	private PatientService patientService;

	public static void main(String[] args) {
		SpringApplication.run(EpisanteAPP.class, args);
	}

	@Override
	public void run(String... args) {
//		List<Patient> patients = MockPatientGenerator.generatePatients(100, patientService);
////
//		patients.forEach(p -> {
//			System.out.println("Nom: " + p.getNom());
//			System.out.println("Prenom: " + p.getPrenom());
//			System.out.println("Email: " + p.getEmail());
//			System.out.println("Taille/Poids: " + p.getTaille() + "cm/" + p.getPoids() + "kg");
//			System.out.println("-------------------");
//		});
	}
	}
