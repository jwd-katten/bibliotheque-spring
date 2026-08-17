package ensaf.bibliotheque.controller;

import ensaf.bibliotheque.model.Categorie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategorieController {

    @GetMapping
    public List<Categorie> lister() {
        return Arrays.asList(Categorie.values());
    }
}
