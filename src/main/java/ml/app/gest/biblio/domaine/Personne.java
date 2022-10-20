package ml.app.gest.biblio.domaine;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_compte")
public abstract class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String adresse;
    private String tel;
    private String email;
    private String login;
    private String password;
    @ManyToOne
    private Role role;

    public Personne() {
        if (this instanceof Gestionnaire) {
            role = new Role(1L, LibelleRole.Gestionnaire);
        } else {
            role = new Role(2L, LibelleRole.Etudiant);
        }
    }

    public Personne(String nom, String prenom, String adresse, String tel, String email, String login, String password) {
        this();
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        this.email = email;
        this.login = login;
        this.password = password;
    }
}
