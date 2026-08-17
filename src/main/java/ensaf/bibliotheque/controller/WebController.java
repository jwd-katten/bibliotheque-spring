package ensaf.bibliotheque.controller;

import ensaf.bibliotheque.model.Categorie;
import ensaf.bibliotheque.service.AuteurService;
import ensaf.bibliotheque.service.LivreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    private final LivreService livreService;
    private final AuteurService auteurService;

    public WebController(LivreService livreService, AuteurService auteurService) {
        this.livreService = livreService;
        this.auteurService = auteurService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("livres", livreService.findAll());
        model.addAttribute("auteurs", auteurService.findAll());
        model.addAttribute("categories", Categorie.values());
        return "index";
    }
}
