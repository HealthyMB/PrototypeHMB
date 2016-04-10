package diet;

import android.app.Application;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by Valentin on 09/04/2016.
 */
public class syntheseRepas extends Application {
    private HashMap<Aliment, Double> repas = new HashMap<Aliment, Double>();
    private HashMap<Aliment, Double> repasDejeuner = new HashMap<Aliment, Double>();
    private HashMap<Aliment, Double> repasPetitDejeuner = new HashMap<Aliment, Double>();
    private HashMap<Aliment, Double> repasDiner = new HashMap<Aliment, Double>();
    private HashMap<Aliment, Double> repasGouter = new HashMap<Aliment, Double>();
    public HashMap getRepas(){
        return repas;
    }
    public void setRepas(HashMap repas){
        this.repas = repas;
    }

    public HashMap<Aliment, Double> getRepasDejeuner() {
        return repasDejeuner;
    }

    public void setRepasDejeuner(HashMap<Aliment, Double> repasDejeuner) {
        this.repasDejeuner = repasDejeuner;
    }

    public HashMap<Aliment, Double> getRepasPetitDejeuner() {
        return repasPetitDejeuner;
    }

    public void setRepasPetitDejeuner(HashMap<Aliment, Double> repasPetitDejeuner) {
        this.repasPetitDejeuner = repasPetitDejeuner;
    }

    public HashMap<Aliment, Double> getRepasDiner() {
        return repasDiner;
    }

    public void setRepasDiner(HashMap<Aliment, Double> repasDiner) {
        this.repasDiner = repasDiner;
    }

    public HashMap<Aliment, Double> getRepasGouter() {
        return repasGouter;
    }

    public void setRepasGouter(HashMap<Aliment, Double> repasGouter) {
        this.repasGouter = repasGouter;
    }

    @Override
    public String toString() {
        return "syntheseRepas{" +
                "repas=" + repas +
                '}';
    }

    public boolean addAliment(Aliment aliment, Double portion){
        if(repas.containsKey(aliment)) return false;
        else
        {
            repas.put(aliment,portion);
            return true;
        }
    }
    public boolean addAlimentDejeuner(Aliment aliment, Double portion){
        if(repasDejeuner.containsKey(aliment)) return false;
        else
        {
            repasDejeuner.put(aliment,portion);
            return true;
        }
    }
    public boolean addAlimentDiner(Aliment aliment, Double portion){
        if(repasDiner.containsKey(aliment)) return false;
        else
        {
            repasDiner.put(aliment,portion);
            return true;
        }
    }
    public boolean addAlimentPetitDejeuner(Aliment aliment, Double portion){
        if(repasPetitDejeuner.containsKey(aliment)) return false;
        else
        {
            repasPetitDejeuner.put(aliment,portion);
            return true;
        }
    }
    public boolean addAlimentGouter(Aliment aliment, Double portion){
        if(repasGouter.containsKey(aliment)) return false;
        else
        {
            repasGouter.put(aliment,portion);
            return true;
        }
    }
    @Override
    public void onCreate(){
        super.onCreate();

    }
}
