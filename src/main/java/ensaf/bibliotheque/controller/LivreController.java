package ensaf.bibliotheque.controller;

import ensaf.bibliotheque.model.Livre;
import ensaf.bibliotheque.service.LivreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/livres")
public class LivreController {

    private final LivreService livreService;

    public LivreController(LivreService livreService) {
        this.livreService = livreService;
    }

    @GetMapping
    public List<Livre> lister(
            @RequestParam(required = false) String titre,
            @RequestParam(required = false) String auteur) {

        if (titre != null || auteur != null) {
            return livreService.rechercher(titre, auteur);
        }
        return livreService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> trouver(@PathVariable Long id) {
        return livreService.findById(id)
                .map(livre -> ResponseEntity.ok((Object) livre))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("message", "Livre non trouvé avec l'id " + id)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Livre ajouter(@RequestBody Livre livre) {
        return livreService.save(livre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modifier(
            @PathVariable Long id,
            @RequestBody Livre livre) {

        var existing = livreService.findById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Livre non trouvé avec l'id " + id));
        }

        Livre existingLivre = existing.get();
        existingLivre.setTitre(livre.getTitre());
        existingLivre.setAnnee(livre.getAnnee());
        existingLivre.setEditeur(livre.getEditeur());
        existingLivre.setNbExemplaires(livre.getNbExemplaires());
        existingLivre.setCategorie(livre.getCategorie());
        existingLivre.setAuteurs(livre.getAuteurs());
        return ResponseEntity.ok(livreService.save(existingLivre));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {

        if (livreService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        livreService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{livreId}/auteurs/{auteurId}")
    public ResponseEntity<Livre> ajouterAuteur(
            @PathVariable Long livreId,
            @PathVariable Long auteurId) {

        return livreService.ajouterAuteur(livreId, auteurId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
