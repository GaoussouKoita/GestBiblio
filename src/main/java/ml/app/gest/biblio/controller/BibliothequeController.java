package ml.app.gest.biblio.controller;

import ml.app.gest.biblio.domaine.Bibliotheque;
import ml.app.gest.biblio.repository.IBibliothequeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/biblio")
public class BibliothequeController {

    @Autowired
    private IBibliothequeRepository daoBiblio;

    @GetMapping("/add")
    public String toAdd(Model model) {
        model.addAttribute("bibliotheque", new Bibliotheque());
        return "bibliotheque/ajout";
    }


    @PostMapping("/add")
    public String add(@Valid  @ModelAttribute("bibliotheque") Bibliotheque bibliotheque, Model model, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) return "bibliotheque/ajout";
        daoBiblio.save(bibliotheque);

        model.addAttribute("nbreTotal", 0);
        model.addAttribute("bibliotheques", daoBiblio.findByNomContaining(bibliotheque.getNom()));
        return "bibliotheque/liste";
    }

    @GetMapping("/update")
    public String toUpdate(Model model, @RequestParam("id") String id) {
        model.addAttribute("bibliotheque", daoBiblio.findById(id));
        return "bibliotheque/ajout";
    }


    @GetMapping("/search")
    public String rechercher(@RequestParam("nom") String nom, Model model) {
        model.addAttribute("bibliotheques", daoBiblio.findByNomContaining(nom));
        model.addAttribute("nbreTotal", 0);
        return "bibliotheque/liste";
    }


    @GetMapping({"/", "/liste"})
    public String list(Model model, @RequestParam(value = "p", defaultValue = "0") int page) {

        Page<Bibliotheque> pageBiblio = daoBiblio.findAll(PageRequest.of(page, 7));
        model.addAttribute("bibliotheques", pageBiblio.getContent());
        model.addAttribute("nbreTotal", pageBiblio.getTotalElements());
        model.addAttribute("pages", new int[pageBiblio.getTotalPages()]);
        return "bibliotheque/liste";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") String id, Model model) {
        daoBiblio.deleteById(id);
        model.addAttribute("bibliotheques", daoBiblio.findAll());
        model.addAttribute("nbreTotal", 0);
        return "bibliotheque/liste";
    }

}
