package diet;

/**
 * Created by Kadi on 02/04/2016.
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Aliment aliment = (Aliment) o;

        if (idAliment != aliment.idAliment) return false;
        if (valeur_energetique != aliment.valeur_energetique) return false;
        if (nom_Aliment != null ? !nom_Aliment.equals(aliment.nom_Aliment) : aliment.nom_Aliment != null)
            return false;
        return !(categorie != null ? !categorie.equals(aliment.categorie) : aliment.categorie != null);

    }

    @Override
    public int hashCode() {
        int result = idAliment;
        result = 31 * result + (nom_Aliment != null ? nom_Aliment.hashCode() : 0);
        result = 31 * result + valeur_energetique;
        result = 31 * result + (categorie != null ? categorie.hashCode() : 0);
        return result;
    }
}
