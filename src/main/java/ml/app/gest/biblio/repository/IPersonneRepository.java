package ml.app.gest.biblio.repository;


import ml.app.gest.biblio.domaine.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonneRepository extends JpaRepository<Personne, Long> {
}
