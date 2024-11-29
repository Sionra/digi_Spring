package fr.digi_hello.DTO;

import fr.digi_hello.classes.Departement;
import fr.digi_hello.classes.Ville;
import org.springframework.stereotype.Component;

@Component
public class VilleMapper {

    static public VilleDTO toDto(Ville ville) {
        VilleDTO villeDTO = new VilleDTO();
        villeDTO.setCodeVille(ville.getNom());
        villeDTO.setNbHabitants(ville.getNbHabitants());
        villeDTO.setCodeDepartement(ville.getDepartement().getCode());
        villeDTO.setNomDepartement(ville.getDepartement().getNom());
        return villeDTO;
    }

    static public Ville toEntity(VilleDTO villeDTO) {
        Ville ville = new Ville();
        ville.setNbHabitants(villeDTO.getNbHabitants());
        ville.setNom(villeDTO.getNom());
        Departement departement = new Departement();
        departement.setNom(villeDTO.getNomDepartement());
        departement.setCode(villeDTO.getCodeDepartement());
        ville.setDepartement(departement);
        return ville;
    }
}
