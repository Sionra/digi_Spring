package fr.digi_hello.repositorys;

import fr.digi_hello.classes.Departement;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepartementRepository extends CrudRepository<Departement, Integer> {

    Departement findById(int id);
    Departement findByNom(String nom);
    Departement findByCode(String code);
    List<Departement> findByNomStartingWith(String nom);

}
