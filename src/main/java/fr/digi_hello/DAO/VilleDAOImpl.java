package fr.digi_hello.DAO;

import fr.digi_hello.classes.Ville;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VilleDAOImpl implements VilleDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Ville> extractVilles() {
        return em.createQuery("from Ville", Ville.class).getResultList();
    }

    @Override
    public Ville extractVilleId(int idVille){
        return em.find(Ville.class, idVille);
    }

    @Override
    public Ville extractVilleName(String nom){
        System.out.println(nom);
        TypedQuery<Ville> query = em.createQuery("from Ville where nom = :nom", Ville.class);
        query.setParameter("nom", nom);
        return query.getResultList().getFirst();
    }

    @Override
    @Transactional
    public List<Ville> insertVille(Ville ville) {
        em.persist(ville);
        return extractVilles();
    }

    @Override
    @Transactional
    public List<Ville> modifierVille(int idVille, Ville ville) {
        Ville villeToUpdate = extractVilleId(idVille);
        if (villeToUpdate != null) {
            villeToUpdate.setNbHabitants(ville.getNbHabitants());
            em.merge(villeToUpdate);
        }
        return extractVilles();
    }

    @Override
    @Transactional
    public List<Ville> supprimerVille(int idVille) {
        Ville villeToDelete = extractVilleId(idVille);
        em.remove(villeToDelete);
        return extractVilles();
    }
}
