package ensaf.bibliotheque.service;

import ensaf.bibliotheque.model.Auteur;
import ensaf.bibliotheque.model.Livre;
import ensaf.bibliotheque.repository.AuteurRepository;
import ensaf.bibliotheque.repository.LivreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivreService {

    private final LivreRepository livreRepository;
    private final AuteurRepository auteurRepository;

    public LivreService(
            LivreRepository livreRepository,
            AuteurRepository auteurRepository) {

        this.livreRepository = livreRepository;
        this.auteurRepository = auteurRepository;
    }

    public List<Livre> findAll() {
        return livreRepository.findAll();
    }

    public Optional<Livre> findById(Long id) {
        return livreRepository.findById(id);
    }

    public Livre save(Livre livre) {
        return livreRepository.save(livre);
    }

    public void deleteById(Long id) {
        livreRepository.deleteById(id);
    }

    // Ajouter un auteur à un livre
    public Optional<Livre> ajouterAuteur(Long livreId, Long auteurId) {

        Optional<Livre> livreOpt = livreRepository.findById(livreId);
        Optional<Auteur> auteurOpt = auteurRepository.findById(auteurId);

        if (livreOpt.isEmpty() || auteurOpt.isEmpty()) {
            return Optional.empty();
        }

        Livre livre = livreOpt.get();
        Auteur auteur = auteurOpt.get();

        if (!livre.getAuteurs().contains(auteur)) {
            livre.getAuteurs().add(auteur);
        }

        return Optional.of(livreRepository.save(livre));
    }
}