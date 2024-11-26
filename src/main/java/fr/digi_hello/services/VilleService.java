package fr.digi_hello.services;

import fr.digi_hello.DAO.VilleDAO;
import fr.digi_hello.DAO.VilleDAOImpl;
import fr.digi_hello.classes.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VilleService {

    @Autowired
    VilleDAO villeDAO;

    public List<Ville> extractVilles() {
        return villeDAO.extractVilles();
    }

    public Ville extractVilleId(int idVille) {
        return villeDAO.extractVilleId(idVille);
//        return villeDAO.extractVille(nom);
//        for (Ville v : villes) {
//            if (v.getId() == id) {
//                return v;
//            }
//        }
//        return null;
    }

    public Ville extractVilleName(String nom) {
        return villeDAO.extractVilleName(nom);
//        for (Ville v : villes) {
//            if (v.getNom().equals(nom)) {
//                return v;
//            }
//        }
//        return null;
    }

    public ResponseEntity<String> insertVille(Ville ville) {
        List<Ville> villes = extractVilles();
        for (Ville v : villes) {
            if (v.getNom().equals(ville.getNom())){
                return ResponseEntity.badRequest().body("Ville existente");
            }
        }
        try {
            villeDAO.insertVille(ville);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Une erreur est survenue lors de l'ajout de la ville");
        }
        return ResponseEntity.ok("Ville " + ville.getNom() + " ajouter avec succes");
//        for (Ville v : ville) {
//            if (v.getNom().equals(nom)) {
//                return ResponseEntity.badRequest().body("Ville existente");
//            }
//        }
//        this.ville.add(new Ville(count, nom, nbHabitants));
//        count++;
//        return ResponseEntity.ok("Ville " + nom + " ajouter avec succes");
    }

    public List<Ville> modifyVille(int villeId, Ville ville) {
        return villeDAO.modifierVille(villeId, ville);

//        boolean found = false;
//        for (Ville v : this.ville) {
//            if (v.getNom().equals(ville.getNom())) {
//                v.setNbHabitants(ville.getNbHabitants());
//                found = true;
//            }
//        }
//         if ( found ) return ResponseEntity.ok("Ville modifier avec succes");
//         return ResponseEntity.badRequest().body("Ville non existente");
    }

    public List<Ville> deleteVille(int id) {
        return villeDAO.supprimerVille(id);
//        for (Ville v : this.ville) {
//            if (v.getId() == id) {
//                this.ville.remove(v);
//                return ResponseEntity.ok("Ville supprimer avec succes");
//            }
//        }
//        return ResponseEntity.badRequest().body("Ville non existente");
    }
}
