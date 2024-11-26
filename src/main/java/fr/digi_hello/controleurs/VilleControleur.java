package fr.digi_hello.controleurs;

import fr.digi_hello.classes.Ville;
import fr.digi_hello.services.VilleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

    @Autowired
    VilleService villeService;

    @GetMapping
    public List<Ville> listVille() {
        return this.villeService.extractVilles();
    }

    @GetMapping(path = "/id/{id}")
    public Ville getVilleById(@PathVariable int id) {
        return this.villeService.extractVilleId(id);
    }

    @GetMapping(path = "/name/{name}")
    public Ville getVilleByName(@PathVariable String name) {
        return this.villeService.extractVilleName(name);
    }

    @PostMapping
    public ResponseEntity<String> addVille(@Valid @RequestBody Ville ville, BindingResult result) throws Exception{
        if (result.hasErrors()){
            throw new Exception(result.getAllErrors().get(0).getDefaultMessage());
        }
        return this.villeService.insertVille(ville);
        //return this.villeService.insertVille(ville.getNom(), ville.getNbHabitants());
    }

    @PutMapping(path = "update/{id}")
    public ResponseEntity<String> updateVille(@PathVariable("id") int id, @Valid @RequestBody Ville villes, BindingResult result) {
        this.villeService.modifyVille(id, villes);
        return ResponseEntity.ok("Ville mise a jour avec succes");
    }

    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity<String> deleteVille(@PathVariable int id) {
        this.villeService.deleteVille(id);
        return ResponseEntity.ok("Ville supprimer avec succes");
    }
}
