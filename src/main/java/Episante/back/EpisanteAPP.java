package Episante.back;

import Episante.back.Models.Patient;
import Episante.back.Models.Sexe;
import Episante.back.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EpisanteAPP {

	@Autowired
	private PatientService patientService;

	public static void main(String[] args) {
		SpringApplication.run(EpisanteAPP.class, args);
	}

//	@Bean
//	public CommandLineRunner run() {
//		return args -> {
//			Patient patient = new Patient();
//			patient.setAdresse("Paris");
//			patient.setAge("21");
//			patient.setEmail("anirsadiqui2@gmail.com");
//			patient.setPrenom("Anir");
//			patient.setNom("Anir");
//			patient.setSexe(Sexe.HOMME);
//			patient.setTelephone("1234567890");
//			patient.setPoids("73");
//			patient.setTaille("183");
//			patient.setMdp("Anir2003");
////			patientService.add(patient);
//		};
//	}
}