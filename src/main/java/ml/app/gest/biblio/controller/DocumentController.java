package ml.app.gest.biblio.controller;

import ml.app.gest.biblio.domaine.Document;
import ml.app.gest.biblio.repository.IDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/doc")
public class DocumentController {
    @Autowired
    private IDocumentRepository daoDoc;

    @GetMapping("/add")
    public String toAdd(Model model) {
        model.addAttribute("document", new Document());
        return "document/ajout";
    }


    @PostMapping("/add")
    public String add(@ModelAttribute("document") Document document, Model model) {

        daoDoc.save(document);
        model.addAttribute("nbreTotal", 0);
        model.addAttribute("documents", daoDoc.findByTitreContaining(document.getTitre()));
        return "document/liste";
    }


    @GetMapping("/update")
    public String toUpdate(Model model, @RequestParam("id")String id) {
        model.addAttribute("document", daoDoc.findById(id));
        return "document/ajout";
    }

    @GetMapping("/search")
    public String rechercher(@RequestParam("titre") String titre, Model model) {
        model.addAttribute("documents", daoDoc.findByTitreContaining(titre));
        model.addAttribute("nbreTotal", 0);
        return "document/liste";
    }

    @GetMapping({"/", "/liste"})
    public String list(Model model, @RequestParam(value = "p", defaultValue = "0") int page) {
        Page<Document> docPages =  daoDoc.findAll(PageRequest.of(page, 7));
        model.addAttribute("documents", docPages.getContent());
        model.addAttribute("nbreTotal", docPages.getTotalElements());
        model.addAttribute("pages", new int[docPages.getTotalPages()]);
        return "document/liste";
    }

    @GetMapping("/delete")
    public String  delete(@RequestParam("id") String id, Model model) {
        daoDoc.deleteById(id);
        model.addAttribute("documents", daoDoc.findAll());
        model.addAttribute("nbreTotal", 0);
        return "document/liste";
    }
}
