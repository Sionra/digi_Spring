package fr.digi_hello.services;

import fr.digi_hello.DTO.VilleDTO;
import fr.digi_hello.DTO.VilleMapper;
import fr.digi_hello.Repository.VilleRepository;
import fr.digi_hello.classes.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VilleService {

    @Autowired
    VilleRepository villeRepository;

    public List<VilleDTO> extractVilles() {
        return listVilleToDTO(villeRepository.findAll());
    }

    public List<VilleDTO> findByNomLike(String nom) {
        return listVilleToDTO(villeRepository.findByNomContains(nom));
    }

    public List<VilleDTO> findGreater(int nb) {
        return listVilleToDTO(villeRepository.findByNbHabitantsGreaterThan(nb));
    }

    public VilleDTO extractVilleId(int idVille) {
        Ville ville = villeRepository.findById(idVille);
        return  VilleMapper.toDto(ville);
    }

    public VilleDTO extractVilleName(String nom) {
        return  VilleMapper.toDto(villeRepository.findByNom(nom));
    }

    public List<VilleDTO> findBetween(int min, int max) {
        Iterable<Ville> listVille = villeRepository.findByNbHabitantsBetween(min, max);
        return listVilleToDTO(listVille);
    }

    public List<VilleDTO> findDepartementGreater(String code, int min) {
        return listVilleToDTO(villeRepository.findByDepartement_CodeAndNbHabitantsGreaterThan(code, min));
    }

    public List<VilleDTO> findDepartementBetween(String code, int min, int max) {
        return listVilleToDTO(villeRepository.findByDepartement_CodeAndNbHabitantsBetween(code, min, max));
    }

    public List<VilleDTO> findPageable(String id, int size) {
        Pageable pageable = PageRequest.of(0, size);
        return listVilleToDTO(villeRepository.findByDepartement_CodeOrderByNbHabitantsDesc(id, pageable));
    }

    public ResponseEntity<String> insertVille(Ville ville) {
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

    private List<VilleDTO> listVilleToDTO(Iterable<Ville> listVille){
        List<VilleDTO> listVilleDTO = new ArrayList<>();
        for (Ville ville : listVille) {
            listVilleDTO.add(VilleMapper.toDto(ville));
        }
        return listVilleDTO;
    }
}
