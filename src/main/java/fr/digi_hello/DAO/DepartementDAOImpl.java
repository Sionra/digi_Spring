package fr.digi_hello.DAO;

import fr.digi_hello.classes.Departement;
import fr.digi_hello.classes.Ville;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartementDAOImpl implements DepartementDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Departement> extractDepartements() {
        return this.em.createQuery("from Departement", Departement.class).getResultList();
    }

    @Override
    public Departement getDepartement(int id) {
        return em.find(Departement.class, id);
    }

    @Override
    @Transactional
    public void insertDepartement(Departement departement) {
        em.persist(departement);
    }

    @Override
    @Transactional
    public void updateDepartement(int id, Departement departement) {
        Departement departement1 = em.find(Departement.class, id);
        if (departement1 != null) {
            departement1.setNom(departement.getNom());
        }
    }

    @Override
    @Transactional
    public void deleteDepartement(int id) {
        Departement departement = em.find(Departement.class, id);
        em.remove(departement);
    }

}
