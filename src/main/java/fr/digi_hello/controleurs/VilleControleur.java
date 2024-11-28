package fr.digi_hello.controleurs;

import fr.digi_hello.Repository.VilleRepository;
import fr.digi_hello.classes.Ville;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

    @Autowired
    private VilleRepository villeRepository;

    @GetMapping("")
    public Iterable<Ville> findAll() {
        return villeRepository.findAll();
    }

    @GetMapping("nomLike/{nom}")
    public List<Ville> findByNomLike(@PathVariable String nom) {
        return villeRepository.findByNomContains(nom);
    }
    
    @GetMapping(path = "greaterThan/{nb}")
    public Iterable<Ville> findGreater(@PathVariable int nb) {
        return villeRepository.findByNbHabitantsGreaterThan(nb);
    }

    @GetMapping(path = "between/{min},{max}")
    public Iterable<Ville> findBetween(@PathVariable int min, @PathVariable int max) {
        return villeRepository.findByNbHabitantsBetween(min, max);
    }

    @GetMapping(path = "DepartementGreater/{code},{min}")
    public List<Ville> findDepartementGreater(@PathVariable String code, @PathVariable int min) {
        return villeRepository.findByDepartement_CodeAndNbHabitantsGreaterThan(code, min);
    }

    @GetMapping(path = "DepartementBetween/{code},{min},{max}")
    public List<Ville> findDepartementBetween(@PathVariable String code, @PathVariable int min, @PathVariable int max) {
        return villeRepository.findByDepartement_CodeAndNbHabitantsBetween(code, min, max);
    }

    @GetMapping(path = "/id/{id}")
    public Ville getVilleById(@PathVariable int id) {
        return this.villeRepository.findById(id);
    }
    @GetMapping(path = "/name/{name}")
    public Ville getVilleByName(@PathVariable String name) {
        return this.villeRepository.findByNom(name);
    }

    @GetMapping(path = "/findPageable/{id},{size}")
    public Page<Ville> findPageable(@PathVariable String id, @PathVariable int size) {
        Pageable pageable = PageRequest.of(0, size);
        return villeRepository.findByDepartement_CodeOrderByNbHabitantsDesc(id, pageable);
    }

    @PostMapping
    public ResponseEntity<String> addVille(@RequestBody Ville ville){
        this.villeRepository.save(ville);
        return ResponseEntity.ok("Ville added");
    }

    @PutMapping(path = "update/{id}")
    public ResponseEntity<String> updateVille(@PathVariable("id") int id, @Valid @RequestBody Ville villes, BindingResult result) {
        this.villeRepository.save(villes);
        return ResponseEntity.ok("Ville mise a jour avec succes");
    }

    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity<String> deleteVille(@PathVariable int id) {
        this.villeRepository.deleteById(id);
        return ResponseEntity.ok("Ville deleted");
    }


    /*
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
     */
}
