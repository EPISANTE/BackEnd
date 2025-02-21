package Episante.back.Service;

import Episante.back.Models.*;
import Episante.back.Repository.IPatientrepository;
import Episante.back.Repository.MedecinRepository;
import Episante.back.Repository.DisponibiliteRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class MedecinService {
    private final MedecinRepository medecinRepository;
    private final DisponibiliteRepository disponibiliteRepository;

    public MedecinService(MedecinRepository medecinRepository, DisponibiliteRepository disponibiliteRepository) {
        this.medecinRepository = medecinRepository;
        this.disponibiliteRepository = disponibiliteRepository;
    }
    public void genererDisponibilitesAleatoires() {
        List<Medecin> medecins = medecinRepository.findAll();
        Random random = new Random();

        JourSemaine[] jours = JourSemaine.values();
        Periode[] periodes = Periode.values();

        for (Medecin medecin : medecins) {
            int nbJours = random.nextInt(5) + 1;

            for (int i = 0; i < nbJours; i++) {
                JourSemaine jour = jours[random.nextInt(jours.length)];
                Periode periode = periodes[random.nextInt(periodes.length)];


                if (disponibiliteRepository.findByMedecinAndJourAndPeriode(medecin, jour, periode).isEmpty()) {
                    Disponibilite disponibilite = new Disponibilite();
                    disponibilite.setJour(jour);
                    disponibilite.setPeriode(periode);
                    disponibilite.setMedecin(medecin);
                    disponibiliteRepository.save(disponibilite); // Sauvegarde uniquement si non existante
                }
            }
        }
    }



    public Medecin getMedecinAvecDisponibilites(Long id) {
        return medecinRepository.findById(id).orElseThrow(() -> new RuntimeException("Médecin non trouvé"));
    }

    public List<Medecin> creerMedecinsEnMasse(int nombre) {
        List<Medecin> medecins = new ArrayList<>();
        Random random = new Random();
        Specialite[] specialites = Specialite.values();

        for (int i = 1; i <= nombre; i++) {
            Medecin medecin = new Medecin();
            medecin.setNom("Dr. " + i);
            medecin.setSpecialite(specialites[random.nextInt(specialites.length)]);
            medecins.add(medecin);
        }
        return sauvegarderMedecins(medecins);
    }


    public List<Medecin> sauvegarderMedecins(List<Medecin> medecins) {
        return medecinRepository.saveAll(medecins);
    }

    public List<Specialite> getAllSpecialites() {
        return Arrays.asList(Specialite.values());
    }
    public List<Medecin> getMedecinsParSpecialite(Specialite specialite) {
        return medecinRepository.findBySpecialite(specialite);
    }
}