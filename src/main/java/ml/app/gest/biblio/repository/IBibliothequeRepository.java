package ml.app.gest.biblio.repository;

import ml.app.gest.biblio.domaine.Bibliotheque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBibliothequeRepository extends JpaRepository<Bibliotheque, String> {

    public List<Bibliotheque> findByNomContaining(String id);

}
