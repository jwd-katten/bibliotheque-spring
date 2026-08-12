package ensaf.bibliotheque.repository;

import ensaf.bibliotheque.model.Auteur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuteurRepository extends JpaRepository<Auteur, Long> {
}