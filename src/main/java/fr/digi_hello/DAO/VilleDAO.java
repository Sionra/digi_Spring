package fr.digi_hello.DAO;

import fr.digi_hello.classes.Ville;

import java.util.List;

public interface VilleDAO {
    List<Ville> extractVilles();
    Ville extractVilleId(int idVille);
    Ville extractVilleName(String nom);
    List<Ville> insertVille(Ville ville);
    List<Ville> modifierVille(int idVille, Ville ville);
    List<Ville> supprimerVille(int idVille);
}
