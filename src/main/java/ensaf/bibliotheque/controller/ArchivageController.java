package ensaf.bibliotheque.controller;

import ensaf.bibliotheque.model.Livre;
import ensaf.bibliotheque.service.LivreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/archivage")
public class ArchivageController {

    private final LivreService livreService;

    public ArchivageController(LivreService livreService) {
        this.livreService = livreService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<Map<String, String>> archiver(@PathVariable Long id) {

        return livreService.findById(id)
                .map(livre -> {
                    livre.sauvegarder();
                    return ResponseEntity.ok(Map.of(
                            "message", "Livre archivé : " + livre.getTitre()
                    ));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
