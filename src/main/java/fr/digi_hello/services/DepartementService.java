package fr.digi_hello.services;

import fr.digi_hello.DAO.DepartementDAO;
import fr.digi_hello.classes.Departement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartementService {

    @Autowired
    DepartementDAO departementDAO;

    public List<Departement> extractVilles() {
        return departementDAO.extractDepartements();
    }

    public Departement getDepartementById(int id) {
        return departementDAO.getDepartement(id);
    }

    public void insertDepartement(Departement departement) {
        departementDAO.insertDepartement(departement);
    }

    public void modifyDepartement(int id, Departement departement) {
        departementDAO.updateDepartement(id, departement);
    }

    public void deleteDepartement(int id) {
        departementDAO.deleteDepartement(id);
    }
}
