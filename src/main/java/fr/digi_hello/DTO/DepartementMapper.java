package fr.digi_hello.DTO;

import fr.digi_hello.classes.Departement;
import org.springframework.stereotype.Component;

@Component
public class DepartementMapper {

    public DepartementDTO toDTO(Departement departement) {
        DepartementDTO departementDTO = new DepartementDTO();
        departementDTO.setNomDepartement(departement.getNom());
        departementDTO.setCodeDepartement(departement.getCode());
        departementDTO.setNbHabitant(777);
        return departementDTO;
    }
}
