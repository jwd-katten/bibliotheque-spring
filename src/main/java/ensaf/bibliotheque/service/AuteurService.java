package ensaf.bibliotheque.service;

import ensaf.bibliotheque.model.Auteur;
import ensaf.bibliotheque.repository.AuteurRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuteurService {

    private final AuteurRepository auteurRepository;

    public AuteurService(AuteurRepository auteurRepository) {
        this.auteurRepository = auteurRepository;
    }

    public List<Auteur> findAll() {
        return auteurRepository.findAll();
    }

    public Optional<Auteur> findById(Long id) {
        return auteurRepository.findById(id);
    }

    public Auteur save(Auteur auteur) {
        return auteurRepository.save(auteur);
    }

    public void deleteById(Long id) {
        auteurRepository.deleteById(id);
    }
}