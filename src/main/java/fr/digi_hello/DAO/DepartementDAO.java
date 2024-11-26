package fr.digi_hello.DAO;

import fr.digi_hello.classes.Departement;

import java.util.List;

public interface DepartementDAO {
    List<Departement> extractDepartements();
    Departement getDepartement(int id);
    void insertDepartement(Departement departement);
    void updateDepartement(int id, Departement departement);
    void deleteDepartement(int id);
}
