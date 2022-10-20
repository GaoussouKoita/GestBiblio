package ml.app.gest.biblio.repository;

import ml.app.gest.biblio.domaine.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IDocumentRepository extends JpaRepository<Document, String> {
    public List<Document> findByTitreContaining(String titre);
    public List<Document> findByDispo(Boolean status);

    @Modifying
    @Query("Update Document doc set doc.dispo=?2 where doc.codeDoc=?1")
    public void updateDocById(String id, boolean status);

}
