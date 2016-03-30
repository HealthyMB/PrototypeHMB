package sport;

/**
 * Created by PAUL on 28/03/2016.
 */
public class Seance {

    private int id;
    private String nom;
    private int duree;
    private int difficulte;

    public Seance(int id, String nom, int duree, int difficulte) {
        this.id = id;
        this.nom = nom;
        this.duree = duree;
        this.difficulte = difficulte;
    }
    public int getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(int difficulte) {
        this.difficulte = difficulte;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
