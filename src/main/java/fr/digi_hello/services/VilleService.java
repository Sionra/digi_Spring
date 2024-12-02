package fr.digi_hello.services;

import fr.digi_hello.exceptions.VilleException;
import fr.digi_hello.repositorys.VilleRepository;
import fr.digi_hello.classes.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VilleService {

    @Autowired
    VilleRepository villeRepository;

    public Iterable<Ville> extractVilles() {
        return villeRepository.findAll();
    }

    public List<Ville> findByNomLike(String nom) throws VilleException {
        List<Ville> list = villeRepository.findByNomLike(nom);
        if (list.isEmpty()) {
            throw new VilleException("Aucune ville existe avec ce nom");
        }
        return list;
    }

    public List<Ville> findGreater(int nb) {
        return villeRepository.findByNbHabitantsGreaterThan(nb);
    }

    public Ville extractVilleId(int idVille) {
        return villeRepository.findById(idVille);
    }

    public Ville extractVilleName(String nom) {
        return  villeRepository.findByNom(nom);
    }

    public List<Ville> findBetween(int min, int max) {
         return villeRepository.findByNbHabitantsBetween(min, max);
    }

    public List<Ville> findDepartementGreater(String code, int min) {
        return villeRepository.findByDepartement_CodeAndNbHabitantsGreaterThan(code, min);
    }

    public List<Ville> findDepartementBetween(String code, int min, int max) {
        return villeRepository.findByDepartement_CodeAndNbHabitantsBetween(code, min, max);
    }

    public Page<Ville> findPageable(String id, int size) {
        Pageable pageable = PageRequest.of(0, size);
        return villeRepository.findByDepartement_CodeOrderByNbHabitantsDesc(id, pageable);
    }

    public ResponseEntity<String> insertVille(Ville ville) throws VilleException {
        if ( ville.getNbHabitants() < 10 ){
            throw new VilleException("La ville a moins de 10 habitants");
        }
        if ( ville.getNom().length() < 2){
            throw new VilleException("La ville doit contenir 2 lettres dans son nom");
        }
        if ( ville.getDepartement().getCode().length() == 2){
            throw new VilleException("Le code de departement, doit contenir exactement 2 lettre");
        }
        //Condition que le nom de la ville et unique dans un departement, mais method de departement a faire avant


        this.villeRepository.save(ville);
        return ResponseEntity.ok("Ville inserted successfully");
    }

//    public List<Ville> modifyVille(int villeId, Ville ville) {
//        return villeDAO.modifierVille(villeId, ville);
//
//    }

    public void deleteVille(int id) {
        villeRepository.deleteById(id);
    }
}
