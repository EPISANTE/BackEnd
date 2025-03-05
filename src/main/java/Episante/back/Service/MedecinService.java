package Episante.back.Service;
import Episante.back.Models.*;
import Episante.back.Repository.MedecinRepository;
import Episante.back.Repository.DisponibiliteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MedecinService {
    private final MedecinRepository medecinRepository;
    private final DisponibiliteRepository disponibiliteRepository;
    private final Random random = new Random();

    public MedecinService(MedecinRepository medecinRepository, DisponibiliteRepository disponibiliteRepository) {
        this.medecinRepository = medecinRepository;
        this.disponibiliteRepository = disponibiliteRepository;
    }

    public Medecin sauvegarderMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    public List<Medecin> getAllMedecins() {
        return medecinRepository.findAll();
    }

    public List<Medecin> getMedecinsParSpecialite(Specialite specialite) {
        return medecinRepository.findBySpecialite(specialite);
    }

    public Medecin getMedecinAvecDisponibilites(Long id) {
        return medecinRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medecin non trouve avec ID : " + id));
    }

    public List<Medecin> creerMedecinsEnMasse(int nombre) {
        List<Medecin> medecins = new ArrayList<>();
        Specialite[] specialites = Specialite.values();

        String[] PRENOMS = {"Jean", "Marie", "Paul", "Sophie", "Lucas", "Emma", "Hugo", "Lea", "Nina", "Alex"};
        String[] NOMS = {"Dupont", "Durand", "Martin", "Bernard", "Morel", "Simon", "Lefebvre", "Girard", "Mercier", "Roux"};

        Random random = new Random();

        for (int i = 0; i < nombre; i++) {
            Medecin medecin = new Medecin();

            String prenom = PRENOMS[random.nextInt(PRENOMS.length)];
            String nom = NOMS[random.nextInt(NOMS.length)];

            medecin.setNom("Dr. " + prenom + " " + nom);
            medecin.setSpecialite(specialites[random.nextInt(specialites.length)]);

            medecins.add(medecin);
        }
        return medecinRepository.saveAll(medecins);
    }

    @Transactional
    public void genererDisponibilitesAleatoires() {
        List<Medecin> medecins = medecinRepository.findAll();
        if (medecins.isEmpty()) {
            throw new RuntimeException("Aucun medecin trouvé, impossible de generer des disponibilites !");
        }

        Random random = new Random();
        JourSemaine[] jours = JourSemaine.values();
        Periode[] periodes = Periode.values();

        LocalDate today = LocalDate.now();
        LocalDate endDate = today.plusDays(30);

        for (Medecin medecin : medecins) {
            int disponibilitesCetteSemaine = 0;
            LocalDate date = today;

            while (date.isBefore(endDate)) {
                if (disponibilitesCetteSemaine < 3) {  // Max 3 disponibilites par semaine
                    JourSemaine jour = JourSemaine.valueOf(date.getDayOfWeek().name());
                    Periode periode = periodes[random.nextInt(periodes.length)];

                    if (disponibiliteRepository.findByMedecinAndJourAndPeriode(medecin, jour, periode).isEmpty()) {
                        Disponibilite dispo = new Disponibilite(jour, periode, medecin);
                        disponibiliteRepository.saveAndFlush(dispo);
                        disponibilitesCetteSemaine++;
                    }
                }

                // Passer au jour suivant
                date = date.plusDays(1);
                if (date.getDayOfWeek() == DayOfWeek.MONDAY) {
                    disponibilitesCetteSemaine = 0; // Reinitialiser chaque semaine
                }
            }
        }
    }



    public List<Disponibilite> getDisponibilites(Long medecinId, JourSemaine jour) {

        if (medecinId != null && jour != null) {
            return disponibiliteRepository.findByMedecinIdAndJour(medecinId, jour);
        } else if (medecinId != null) {
            return disponibiliteRepository.findByMedecinId(medecinId);
        } else if (jour != null) {
            return disponibiliteRepository.findByJour(jour);
        }
        return disponibiliteRepository.findAll();
    }


    public List<String> getAllSpecialites() {
        return Arrays.stream(Specialite.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}