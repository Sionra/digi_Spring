package fr.digi_hello.controleurs;

import fr.digi_hello.classes.Departement;
import fr.digi_hello.exceptions.DepartementException;
import fr.digi_hello.services.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departements")
public class DepartementControleur {

    @Autowired
    DepartementService departementService;

    @GetMapping
    public Iterable<Departement> listDepartements() {
        return departementService.getAllDepartements();
    }

    @GetMapping(path = "/{id}")
    public Departement getDepartement(@PathVariable int id) {
        return departementService.getDepartementById(id);
    }

    @GetMapping("NomStart/{nom}")
    public List<Departement> listDepartementsNomStart(@PathVariable String nom) throws DepartementException {
        return departementService.getDepartementStartingWith(nom);
    }

    @PostMapping
    public void addDepartement(@RequestBody Departement departement) {
        departementService.insertDepartement(departement);
    }

//    @PutMapping(path = "/{id}")
//    public void updateDepartement(@PathVariable("id") int id, @RequestBody Departement departement) {
//        departementService.modifyDepartement(id, departement);
//    }

    @DeleteMapping(path = "/{id}")
    public void deleteDepartement(@PathVariable("id") int id) {
        departementService.deleteDepartement(id);
    }

}
