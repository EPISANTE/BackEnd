package Episante.back.Service;

import Episante.back.Repository.RendezVousRepository;
import Episante.back.Models.RendezVous;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RendezVousService {

    @Autowired
    private RendezVousRepository rendezVousDao;

    public List<RendezVous> getAllRendezVous() {
        return rendezVousDao.findAll();
    }

    public RendezVous getRendezVousById(Long id) {
        return rendezVousDao.findById(id).orElseThrow(() -> new RuntimeException("RendezVous not found with id: " + id));
    }

    public RendezVous saveRendezVous(RendezVous rendezVous) {
        return rendezVousDao.save(rendezVous);
    }

    public void deleteRendezVous(Long id) {
        rendezVousDao.deleteById(id);
    }
}
