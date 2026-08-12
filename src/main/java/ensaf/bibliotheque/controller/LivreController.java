package ensaf.bibliotheque.controller;

import ensaf.bibliotheque.model.Livre;
import ensaf.bibliotheque.service.LivreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livres")
public class LivreController {

    private final LivreService livreService;

    public LivreController(LivreService livreService) {
        this.livreService = livreService;
    }

    // GET /api/livres
    @GetMapping
    public List<Livre> getAllLivres() {
        return livreService.findAll();
    }

    // GET /api/livres/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Livre> getLivreById(@PathVariable Long id) {

        return livreService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/livres
    @PostMapping
    public Livre createLivre(@RequestBody Livre livre) {
        return livreService.save(livre);
    }

    // PUT /api/livres/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Livre> updateLivre(
            @PathVariable Long id,
            @RequestBody Livre livre) {

        return livreService.findById(id)
                .map(existingLivre -> {

                    existingLivre.setTitre(livre.getTitre());
                    existingLivre.setIsbn(livre.getIsbn());
                    existingLivre.setAnneePublication(
                            livre.getAnneePublication()
                    );
                    existingLivre.setCategorie(livre.getCategorie());
                    existingLivre.setArchivage(livre.getArchivage());
                    existingLivre.setAuteurs(livre.getAuteurs());

                    return ResponseEntity.ok(
                            livreService.save(existingLivre)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }


    // POST /api/livres/{livreId}/auteurs/{auteurId}
    @PostMapping("/{livreId}/auteurs/{auteurId}")
    public ResponseEntity<Livre> ajouterAuteur(
        @PathVariable Long livreId,
        @PathVariable Long auteurId) {

    return livreService.ajouterAuteur(livreId, auteurId)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}

    // DELETE /api/livres/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLivre(@PathVariable Long id) {

        if (livreService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        livreService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}