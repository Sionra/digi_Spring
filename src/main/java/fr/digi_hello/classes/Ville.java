package fr.digi_hello.classes;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

@Entity
public class Ville {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Size(min = 2)
    @Column(name = "NOM")
    private String nom;
    @Range(min = 1)
    @Column(name = "NBHABITANTS")
    private int nbHabitants;
    @ManyToOne
    @JoinColumn(name = "DEPARTEMENT_ID")
    private Departement departement;

    public Ville() {}

    public Ville(String nom, int nbHabitants) {
        this.nom = nom;
        this.nbHabitants = nbHabitants;
    }

    public String getNom() {
        return nom;
    }

    public int getNbHabitants() {
        return nbHabitants;
    }

    public void setNbHabitants(int nbHabitants) {
        this.nbHabitants = nbHabitants;
    }

    public int getId() {
        return id;
    }
}
