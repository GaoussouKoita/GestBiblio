package ml.app.gest.biblio.repository;


import ml.app.gest.biblio.domaine.Gestionnaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IGestionnaireRepository extends JpaRepository<Gestionnaire, Long> {

    public List<Gestionnaire> findByEmailContaining(String email);
}
