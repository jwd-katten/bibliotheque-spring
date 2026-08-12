package ensaf.bibliotheque.controller;

import ensaf.bibliotheque.model.Auteur;
import ensaf.bibliotheque.service.AuteurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auteurs")
public class AuteurController {

    private final AuteurService auteurService;

    public AuteurController(AuteurService auteurService) {
        this.auteurService = auteurService;
    }

    @GetMapping
    public List<Auteur> getAllAuteurs() {
        return auteurService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Auteur> getAuteurById(@PathVariable Long id) {
        return auteurService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Auteur createAuteur(@RequestBody Auteur auteur) {
        return auteurService.save(auteur);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Auteur> updateAuteur(
            @PathVariable Long id,
            @RequestBody Auteur auteur) {

        return auteurService.findById(id)
                .map(existingAuteur -> {

                    existingAuteur.setNom(auteur.getNom());
                    existingAuteur.setPrenom(auteur.getPrenom());

                    return ResponseEntity.ok(
                            auteurService.save(existingAuteur)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuteur(@PathVariable Long id) {

        if (auteurService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        auteurService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}