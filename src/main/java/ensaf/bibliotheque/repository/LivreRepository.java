package ensaf.bibliotheque.repository;

import ensaf.bibliotheque.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivreRepository extends JpaRepository<Livre, Long> {

    List<Livre> findByTitreContainingIgnoreCase(String titre);

    List<Livre> findByAuteursNomContainingIgnoreCase(String nom);
}
