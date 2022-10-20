package ml.app.gest.biblio.controller;

import ml.app.gest.biblio.domaine.Gestionnaire;
import ml.app.gest.biblio.repository.IGestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/gest")
public class GestionnaireController {

    @Autowired
    private IGestionnaireRepository daoGestionnaire;

    @GetMapping("/add")
    public String toAdd(Model model) {
        model.addAttribute("gestionnaire", new Gestionnaire());
        return "gestionnaire/ajout";
    }


    @PostMapping("/add")
    public String add(@ModelAttribute("gestionnaire") Gestionnaire gestionnaire, Model model) {

        daoGestionnaire.save(gestionnaire);

        model.addAttribute("gestionnaires", daoGestionnaire.findByEmailContaining(gestionnaire.getEmail()));
        model.addAttribute("nbreTotal", 0);
        return "gestionnaire/liste";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") Long id, Model model) {
        model.addAttribute("gestionnaire", daoGestionnaire.findById(id));
        return "gestionnaire/ajout";
    }

    @GetMapping("/search")
    public String rechercher(@RequestParam("email") String email, Model model) {
        model.addAttribute("gestionnaires", daoGestionnaire.findByEmailContaining(email));
        model.addAttribute("nbreTotal", 0);
        return "gestionnaire/liste";
    }

    @GetMapping({"/", "/liste"})
    public String list(Model model, @RequestParam(value = "p", defaultValue = "0") int page) {
        Page<Gestionnaire> pageGest=daoGestionnaire.findAll(PageRequest.of(page, 7));
        model.addAttribute("gestionnaires", pageGest.getContent());
        model.addAttribute("nbreTotal", pageGest.getTotalElements());
        model.addAttribute("pages", new int[pageGest.getTotalPages()]);
        return "gestionnaire/liste";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id, Model model) {
        daoGestionnaire.deleteById(id);
        model.addAttribute("gestionnaires", daoGestionnaire.findAll());
        model.addAttribute("nbreTotal", 0);
        return "gestionnaire/liste";
    }

}
