package ml.app.gest.biblio.domaine;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Data
@DiscriminatorValue("etudiant")
public class Etudiant extends Personne {

    private String classe;

    @OneToMany(mappedBy = "etudiant")
    private List<Emprunt> emprunt;

    public Etudiant() {
        super();
    }

    public Etudiant(String nom, String prenom, String adresse, String tel, String email, String login, String password, String classe) {
        super(nom, prenom, adresse, tel, email, login, password);
        this.classe = classe;
    }
}