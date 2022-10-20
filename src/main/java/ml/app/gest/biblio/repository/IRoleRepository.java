package ml.app.gest.biblio.repository;

import ml.app.gest.biblio.domaine.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Long> {
}
