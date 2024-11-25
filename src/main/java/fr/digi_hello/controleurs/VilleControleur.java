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

    @GetMapping(path = "/{id}")
    public Ville getVilleById(@PathVariable int id) {
        return this.villeService.getVilleById(id);
    }

    @PostMapping
    public ResponseEntity<String> addVille(@RequestBody Ville ville) {
        return this.villeService.addVille(ville.getNom(), ville.getNbHabitants());
    }

    @PutMapping
    public ResponseEntity<String> updateVille(@RequestBody Ville ville) {
        return this.villeService.modifyVille(ville);
    }

    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity<String> deleteVille(@PathVariable int id) {
        return this.villeService.deleteVille(id);
    }
}
