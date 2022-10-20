package ml.app.gest.biblio.domaine;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class Document {
    @Id
    @Column(length = 15)
    private String codeDoc;
    private String titre;
    private String auteur;
    private String type;
    private boolean dispo;
    private String domaine;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Bibliotheque biblio;
    @OneToMany(mappedBy = "document")
    private List<Emprunt> emprunt;

    public Document() {
        dispo = true;
    }
}
