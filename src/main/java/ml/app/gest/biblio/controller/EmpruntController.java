package ml.app.gest.biblio.controller;

import ml.app.gest.biblio.domaine.Emprunt;
import ml.app.gest.biblio.repository.IDocumentRepository;
import ml.app.gest.biblio.repository.IEmpruntRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@Controller
@RequestMapping("/emprunt")
public class EmpruntController {


    @Autowired
    private IEmpruntRepository daoEmprunt;
    @Autowired
    private IDocumentRepository daoDoc;

    @GetMapping("/add")
    public String toAdd(Model model) {
        model.addAttribute("emprunt", new Emprunt());
        return "emprunt/ajout";
    }


    @PostMapping("/add")
    @Transactional
    public String add(@ModelAttribute("emprunt") Emprunt emprunt, Model model) {


        daoDoc.updateDocById(emprunt.getDocument().getCodeDoc(), false);
        daoEmprunt.save(emprunt);
        model.addAttribute("emprunts", daoEmprunt.findByDateEmprunt(emprunt.getDateEmprunt()));
        model.addAttribute("nbreTotal", 0);
        return "emprunt/liste";
    }

    @GetMapping("/update")
    public String toUpdate(@RequestParam("id") Long id, Model model) {
        Emprunt emprunt = daoEmprunt.findById(id).get();
        model.addAttribute("emprunt", emprunt);
        return "emprunt/ajout";
    }

    @GetMapping("/remise")
    @Transactional
    public String remettre(@RequestParam("id") Long id, Model model) {

        Emprunt emprunt = daoEmprunt.findById(id).get();
        daoDoc.updateDocById(emprunt.getDocument().getCodeDoc(), true);
        daoEmprunt.updateStatus(id, false);
        model.addAttribute("emprunts", daoEmprunt.findAll());
        model.addAttribute("nbreTotal", 0);
        return "emprunt/liste";
    }

    @GetMapping("/search")
    public String rechercher(@RequestParam("email") String email, Model model) {
        model.addAttribute("emprunts", daoEmprunt.findByEtudiantEmail(email));
        model.addAttribute("nbreTotal", 0);
        return "emprunt/liste";
    }

    @GetMapping({"/", "/liste"})
    public String list(Model model, @RequestParam(value = "p", defaultValue = "0") int page) {
        Page<Emprunt> pageEmprunt = daoEmprunt.findAll(PageRequest.of(page, 7));
        model.addAttribute("emprunts", pageEmprunt.getContent());
        model.addAttribute("nbreTotal", pageEmprunt.getTotalElements());
        model.addAttribute("pages", new int[pageEmprunt.getTotalPages()]);
        return "emprunt/liste";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id, Model model) {
        daoEmprunt.deleteById(id);
        model.addAttribute("emprunts", daoEmprunt.findAll());
        model.addAttribute("nbreTotal", 0);
        return "emprunt/liste";
    }
}
