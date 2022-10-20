package ml.app.gest.biblio.domaine;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LibelleRole libelleRole;
    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<Personne> personnes;

    public Role(Long id, LibelleRole libelleRole) {
        this.id = id;
        this.libelleRole = libelleRole;

    }


}
