package fr.digi_hello.controleurs;

import fr.digi_hello.classes.Ville;
import fr.digi_hello.services.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<String> addVille(@RequestBody Ville ville) {
        return this.villeService.addVille(ville.getNom(), ville.getNbHabitants());
    }
}
