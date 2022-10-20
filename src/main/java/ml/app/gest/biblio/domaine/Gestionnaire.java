package ml.app.gest.biblio.domaine;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("gestionnaire")
public class Gestionnaire extends Personne {

    public Gestionnaire() {
        super();
    }

    public Gestionnaire(String nom, String prenom, String adresse, String tel, String email, String login, String password) {
        super(nom, prenom, adresse, tel, email, login, password);
    }
}
