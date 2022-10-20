package ml.app.gest.biblio.controller;

import ml.app.gest.biblio.domaine.Etudiant;
import ml.app.gest.biblio.repository.IEtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/etud")
public class EtudiantController {

    @Autowired
    private IEtudiantRepository daoEtud;

    @GetMapping("/add")
    public String toAdd(Model model) {
        model.addAttribute("etudiant", new Etudiant());
        return "etudiant/ajout";
    }


    @PostMapping("/add")
    public String add(@ModelAttribute("etudiant") @Valid Etudiant etudiant, Model model, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) return "etudiant/ajout";
        daoEtud.save(etudiant);
        model.addAttribute("etudiants", daoEtud.findByEmailContaining(etudiant.getEmail()));
        model.addAttribute("nbreTotal", 0);
        return "etudiant/liste";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") Long id, Model model) {
        model.addAttribute("etudiant", daoEtud.findById(id));
        return "etudiant/ajout";
    }

    @GetMapping("/search")
    public String rechercher(@RequestParam("email") String email, Model model) {
        model.addAttribute("etudiants", daoEtud.findByEmailContaining(email));
        model.addAttribute("nbreTotal", 0);
        return "etudiant/liste";
    }

    @GetMapping({"/", "/liste"})
    public String list(Model model, @RequestParam(value = "p", defaultValue = "0") int page) {
        Page<Etudiant> etudiantPages = daoEtud.findAll(PageRequest.of(page,7));
        model.addAttribute("etudiants", etudiantPages.getContent());
        model.addAttribute("nbreTotal", etudiantPages.getTotalElements());
        model.addAttribute("pages", new int[etudiantPages.getTotalPages()]);
        return "etudiant/liste";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id, Model model) {
        daoEtud.deleteById(id);
        model.addAttribute("etudiants", daoEtud.findAll());
        model.addAttribute("nbreTotal", 0);
        return "etudiant/liste";
    }
}