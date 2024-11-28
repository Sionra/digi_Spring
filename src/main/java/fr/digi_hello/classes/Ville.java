package fr.digi_hello.classes;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
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
    @Null
    @Column(name = "ID_REGION")
    private String idRegion;
    @Range(min = 1)
    @Column(name = "nb_habs")
    private int nbHabitants;
    @ManyToOne
    @JoinColumn(name = "id_dept")
    private Departement departement;

    public Ville() {
    }

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
