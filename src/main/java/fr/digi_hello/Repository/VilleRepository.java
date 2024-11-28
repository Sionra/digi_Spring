package fr.digi_hello.Repository;

import fr.digi_hello.classes.Ville;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VilleRepository extends CrudRepository<Ville, Integer> {
    /**
     * Retourne les ville qui commence par la chaine de charactere en parametre
     * @param nom
     * @return
     */
    Ville findByNom(String nom);

    /**
     * Cherche les ville au dessus d'un min d'habitant
     * @param min
     * @return
     */
    List<Ville> findByNbHabitantsGreaterThan(int min);

    /**
     * Cherche les ville entre 2 nb d'habitant
     * @param nbHabitantsMin
     * @param nbHabitantsMax
     * @return
     */
    List<Ville> findByNbHabitantsBetween(int nbHabitantsMin, int nbHabitantsMax);
    List<Ville> findByNomContains(String nom);
    List<Ville> findByDepartement_CodeAndNbHabitantsGreaterThan(String departementId, int nbHabitants);
    List<Ville> findByDepartement_CodeAndNbHabitantsBetween(String departementId, int nbHabitants, int nbHabitantsMax);
    Page<Ville> findByDepartement_CodeOrderByNbHabitantsDesc(String departementId, Pageable pageable);
    Ville findById(int id);
}
