package fr.digi_hello.services;

import fr.digi_hello.classes.Ville;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class VilleService {
    private static int count = 1;
    ArrayList<Ville> ville = new ArrayList<>();

    public VilleService() {
        ville.add(new Ville(count, "Nice", 343000));
        count++;
        ville.add(new Ville(count, "Carcassonne", 47800));
        count++;
        ville.add(new Ville(count, "Narbonne", 53400));
        count++;
        ville.add(new Ville(count, "Lyon", 484000));
        count++;
        ville.add(new Ville(count, "Foix", 9700));
        count++;
        ville.add(new Ville(count, "Pau", 77200));
        count++;
        ville.add(new Ville(count, "Marseille", 850700));
        count++;
        ville.add(new Ville(count, "Tarbes", 40600));
        count++;
    }

    public ArrayList<Ville> getVille() {
        return ville;
    }

    public ResponseEntity<String> addVille(String nom, int nbHabitants) {
        for (Ville v : ville) {
            if (v.getNom().equals(nom)) {
                return ResponseEntity.badRequest().body("Ville existente");
            }
        }
        this.ville.add(new Ville(count, nom, nbHabitants));
        count++;
        return ResponseEntity.ok("Ville " + nom + " ajouter avec succes");
    }

    public Ville getVilleById(int id) {
        for (Ville v : ville) {
            if (v.getId() == id) {
                return v;
            }
        }
        return null;
    }

    public ResponseEntity<String> modifyVille(Ville ville) {
        boolean found = false;
        for (Ville v : this.ville) {
            if (v.getNom().equals(ville.getNom())) {
                v.setNbHabitants(ville.getNbHabitants());
                found = true;
            }
        }
         if ( found ) return ResponseEntity.ok("Ville modifier avec succes");
         return ResponseEntity.badRequest().body("Ville non existente");
    }

    public ResponseEntity<String> deleteVille(int id) {
        for (Ville v : this.ville) {
            if (v.getId() == id) {
                this.ville.remove(v);
                return ResponseEntity.ok("Ville supprimer avec succes");
            }
        }
        return ResponseEntity.badRequest().body("Ville non existente");
    }
}
