package ensaf.bibliotheque.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "livre")
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_livre")
    private Long id;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false, unique = true)
    private String isbn;

    private Integer anneePublication;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    @OneToOne
    @JoinColumn(name = "archivage_id")
    private Archivage archivage;


    @ManyToMany
    @JoinTable(
    name = "livre_auteur",
    joinColumns = @JoinColumn(name = "id_livre"),
    inverseJoinColumns = @JoinColumn(name = "id_auteur")
)
private List<Auteur> auteurs = new ArrayList<>();

    public Livre() {
    }

    public Livre(String titre, String isbn, int anneePublication) {
        this.titre = titre;
        this.isbn = isbn;
        this.anneePublication = anneePublication;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getAnneePublication() {
        return anneePublication;
    }

    public void setAnneePublication(Integer anneePublication) {
        this.anneePublication = anneePublication;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Archivage getArchivage() {
        return archivage;
    }

    public void setArchivage(Archivage archivage) {
        this.archivage = archivage;
    }

    public List<Auteur> getAuteurs() {
        return auteurs;
    }

    public void setAuteurs(List<Auteur> auteurs) {
        this.auteurs = auteurs;
    }
}