package diet;

/**
 * Created by Valentin on 09/04/2016.
 */
public class Aliment {
    private int idAliment;
    private String nom_Aliment;
    private int valeur_energetique;
    private String categorie;

    public Aliment(int idAliment, String nom_Aliment, int valeur_energetique, String categorie) {
        this.idAliment = idAliment;
        this.nom_Aliment = nom_Aliment;
        this.valeur_energetique = valeur_energetique;
        this.categorie = categorie;
    }

    public int getIdAliment() {
        return idAliment;
    }

    public void setIdAliment(int idAliment) {
        this.idAliment = idAliment;
    }

    public String getNom_Aliment() {
        return nom_Aliment;
    }

    public void setNom_Aliment(String nom_Aliment) {
        this.nom_Aliment = nom_Aliment;
    }

    public int getValeur_energetique() {
        return valeur_energetique;
    }

    public void setValeur_energetique(int valeur_energetique) {
        this.valeur_energetique = valeur_energetique;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Aliment{" +
                "idAliment=" + idAliment +
                ", nom_Aliment='" + nom_Aliment + '\'' +
                ", valeur_energetique=" + valeur_energetique +
                ", categorie='" + categorie + '\'' +
                '}';
    }
}
