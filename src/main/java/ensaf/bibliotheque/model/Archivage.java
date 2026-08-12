package ensaf.bibliotheque.model;

import jakarta.persistence.*;

@Entity
@Table(name = "archivage")
public class Archivage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String emplacement;

    public Archivage() {
    }

    public Archivage(String emplacement) {
        this.emplacement = emplacement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }
}