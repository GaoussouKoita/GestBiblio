package ml.app.gest.biblio.repository;

import ml.app.gest.biblio.domaine.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IEtudiantRepository extends JpaRepository<Etudiant, Long> {
    public List<Etudiant> findByEmailContaining(String email);
}
