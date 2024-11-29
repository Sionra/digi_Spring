package fr.digi_hello.controleurs;

import fr.digi_hello.DTO.VilleDTO;
import fr.digi_hello.classes.Ville;
import fr.digi_hello.services.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

    @Autowired
    private VilleService villeService;

    @GetMapping("")
    public List<VilleDTO> findAll() {
        return villeService.extractVilles();
    }

    @GetMapping("nomLike/{nom}")
    public List<VilleDTO> findByNomLike(@PathVariable String nom) {
        return villeService.findByNomLike(nom);
    }
    
    @GetMapping(path = "greaterThan/{nb}")
    public List<VilleDTO> findGreater(@PathVariable int nb) {
        return villeService.findGreater(nb);
    }

    @GetMapping(path = "between/{min},{max}")
    public List<VilleDTO> findBetween(@PathVariable int min, @PathVariable int max) {
        return villeService.findBetween(min, max);
    }

    @GetMapping(path = "DepartementGreater/{code},{min}")
    public List<VilleDTO> findDepartementGreater(@PathVariable String code, @PathVariable int min) {
        return villeService.findDepartementGreater(code, min);
    }

    @GetMapping(path = "DepartementBetween/{code},{min},{max}")
    public List<VilleDTO> findDepartementBetween(@PathVariable String code, @PathVariable int min, @PathVariable int max) {
        return villeService.findDepartementBetween(code, min, max);
    }

    @GetMapping(path = "/id/{id}")
    public VilleDTO getVilleById(@PathVariable int id) {
        return villeService.extractVilleId(id);
    }

    @GetMapping(path = "/name/{name}")
    public VilleDTO getVilleByName(@PathVariable String name) {
        return villeService.extractVilleName(name);
    }

    @GetMapping(path = "/findPageable/{id},{size}")
    public List<VilleDTO> findPageable(@PathVariable String id, @PathVariable int size) {
        return villeService.findPageable(id, size);
    }

    @PostMapping
    public ResponseEntity<String> addVille(@RequestBody Ville ville){
        villeService.insertVille(ville);
        return ResponseEntity.ok("Ville added");
    }

//    @PutMapping(path = "update/{id}")
//    public ResponseEntity<String> updateVille(@PathVariable("id") int id, @Valid @RequestBody Ville villes, BindingResult result) {
//        this.villeService.save(villes);
//        return ResponseEntity.ok("Ville mise a jour avec succes");
//    }

    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity<String> deleteVille(@PathVariable int id) {
        villeService.deleteVille(id);
        return ResponseEntity.ok("Ville deleted");
    }
}
