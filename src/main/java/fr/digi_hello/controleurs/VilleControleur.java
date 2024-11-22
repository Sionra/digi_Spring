package fr.digi_hello.controleurs;

import fr.digi_hello.classes.Ville;
import fr.digi_hello.services.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

    @Autowired
    VilleService villeService;

    @GetMapping
    public ArrayList<Ville> Listville() {
        return this.villeService.getVille();
    }
}
