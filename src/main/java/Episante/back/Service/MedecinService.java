package Episante.back.Service;
import Episante.back.Models.*;
import Episante.back.Repository.MedecinRepository;
import Episante.back.Repository.DisponibiliteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
        if (medecins.isEmpty()) throw new RuntimeException("Pas de medecins !");

        LocalDate aujourdhui = LocalDate.now();

        for (Medecin medecin : medecins) {
            for (int semaine = 0; semaine < 3; semaine++) {
                LocalDate date = aujourdhui.plusWeeks(semaine);


                for (int jourOffset = 0; jourOffset < 14; jourOffset++) {
                    LocalDate currentDate = date.plusDays(jourOffset);
                    JourSemaine jour = JourSemaine.valueOf(currentDate.getDayOfWeek().name());

                    if (jour == JourSemaine.SATURDAY || jour == JourSemaine.SUNDAY) {
                        continue;
                    }


                    for (Periode creneau : Periode.values()) {
                        LocalDateTime dateHeure = LocalDateTime.of(currentDate, creneau.getHeureDebut());


                        if ((creneau.getHeureDebut().isAfter(LocalTime.of(8, 59))
                                && (creneau.getHeureDebut().isBefore(LocalTime.of(12, 1))
                                || (creneau.getHeureDebut().isAfter(LocalTime.of(13, 59))
                                && (creneau.getHeureDebut().isBefore(LocalTime.of(17, 31))))))) {

                            if (disponibiliteRepository.findByMedecinAndDateHeure(medecin, dateHeure).isEmpty()) {
                                Disponibilite dispo = new Disponibilite(dateHeure, jour, medecin);
                                disponibiliteRepository.save(dispo);
                            }
                        }
                    }
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