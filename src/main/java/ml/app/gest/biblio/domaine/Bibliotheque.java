package ml.app.gest.biblio.domaine;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Bibliotheque {
    @Id
    private String codeBiblio;
    private String nom;
    private String adresse;
    private String tel;
    private String email;
    @OneToMany(mappedBy = "biblio")
    private List<Document> documents;

    public Bibliotheque(String codeBiblio, String nom, String adresse, String tel, String email) {
        this.codeBiblio = codeBiblio;
        this.nom = nom;
        this.adresse = adresse;
        this.tel = tel;
        this.email = email;
    }
}
