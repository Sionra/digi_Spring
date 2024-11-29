package fr.digi_hello.services;

import fr.digi_hello.DAO.DepartementDAO;
import fr.digi_hello.classes.Departement;
import fr.digi_hello.exceptions.DepartementException;
import fr.digi_hello.repositorys.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartementService {

    @Autowired
    DepartementRepository departementRepository;

    public Iterable<Departement> getAllDepartements() {
        return departementRepository.findAll();
    }

    public Departement getDepartementById(int id) {
        return departementRepository.findById(id);
    }

    public List<Departement> getDepartementStartingWith(String nom) throws DepartementException {
        List<Departement> listD = departementRepository.findByNomStartingWith(nom);
        if (listD.isEmpty()) {
            throw new DepartementException("Aucun departement commençant avec ces lettres '" + nom + "' exist");
        }
        return listD;
    }

    public void insertDepartement(Departement departement) {
        departementRepository.save(departement);
    }

//    public void modifyDepartement(int id, Departement departement) {
//        departementDAO.updateDepartement(id, departement);
//    }

    public void deleteDepartement(int id) {
        departementRepository.deleteById(id);
    }
}
