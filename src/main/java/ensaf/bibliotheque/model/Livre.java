package ensaf.bibliotheque.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "livre")
public class Livre implements Archivage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_livre")
    private Long id;

    @Column(nullable = false)
    private String titre;

    private int annee;

    private String editeur;

    private int nbExemplaires;

    @Enumerated(EnumType.STRING)
    private Categorie categorie;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "livre_auteur",
        joinColumns = @JoinColumn(name = "id_livre"),
        inverseJoinColumns = @JoinColumn(name = "id_auteur")
    )
    private List<Auteur> auteurs = new ArrayList<>();

    public Livre() {
    }

    public Livre(String titre, int annee, String editeur, int nbExemplaires, Categorie categorie) {
        this.titre = titre;
        this.annee = annee;
        this.editeur = editeur;
        this.nbExemplaires = nbExemplaires;
        this.categorie = categorie;
    }

    @Override
    public void sauvegarder() {
        System.out.println("Archivé : " + titre);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public int getNbExemplaires() {
        return nbExemplaires;
    }

    public void setNbExemplaires(int nbExemplaires) {
        this.nbExemplaires = nbExemplaires;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public List<Auteur> getAuteurs() {
        return auteurs;
    }

    public void setAuteurs(List<Auteur> auteurs) {
        this.auteurs = auteurs;
    }
}
