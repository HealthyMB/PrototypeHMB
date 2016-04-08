package diet;

import android.app.Application;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by Valentin on 09/04/2016.
 */
public class syntheseRepas extends Application {
    private HashMap<Aliment, Double> repas = new HashMap<Aliment, Double>();

    public HashMap getRepas(){
        return repas;
    }
    public void setRepas(HashMap repas){
        this.repas = repas;
    }

    @Override
    public String toString() {
        return "syntheseRepas{" +
                "repas=" + repas +
                '}';
    }

    public boolean addAliment(Aliment aliment, Double portion){
        Log.i("test2", "in the error");
        if(repas.containsKey(aliment)) return false;
        else
        {
            Log.i("test2", "in the loop");
            repas.put(aliment,portion);
            return true;
        }
    }

    @Override
    public void onCreate(){
        super.onCreate();

    }
}
