package ml.app.gest.biblio.repository;

import ml.app.gest.biblio.domaine.Emprunt;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface IEmpruntRepository extends JpaRepository<Emprunt, Long> {

    @Query("Select p from Emprunt p where p.etudiant.email like %:email%")
    public List<Emprunt> findByEtudiantEmail(String email);

    public List<Emprunt> findByDateEmprunt(Date dateEmprunt);

    @Modifying
    @Query("Update Emprunt emprunt set emprunt.status=?2 where emprunt.id=?1")
    public void updateStatus(Long id, boolean status);

}
