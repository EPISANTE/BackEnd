package Episante.back.Service;

import Episante.back.Models.Medecin;
import Episante.back.Repository.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedecinServiceImpl implements ImedecinService {

    private final MedecinRepository medecinRepository;

    @Autowired
    public MedecinServiceImpl(MedecinRepository medecinRepository) {
        this.medecinRepository = medecinRepository;
    }

    @Override
    public List<Medecin> getAllMedecins() {
        return medecinRepository.findAll();
    }

    @Override
    public Optional<Medecin> getMedecinById(Long id) {
        return medecinRepository.findById(id);
    }

    @Override
    public Medecin createMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    @Override
    public Medecin updateMedecin(Long id, Medecin medecin) {
        medecin.setId(id);
        return medecinRepository.save(medecin);
    }

    @Override
    public void deleteMedecin(Long id) {
        medecinRepository.deleteById(id);
    }
}