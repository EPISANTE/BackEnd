package Episante.back;

import Episante.back.Models.Patient;
import Episante.back.Models.Sexe;
import Episante.back.Service.DiagnosticService;
import Episante.back.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan()
@EnableJpaRepositories()
public class EpisanteAPP {

	@Autowired
	private PatientService patientService;

	public static void main(String[] args) {
		SpringApplication.run(EpisanteAPP.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(DiagnosticService diagnosticService) {
		return args -> {
			diagnosticService.startDiagnosis();
		};
	}

}