package diet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.HashMap;

import efrei.healthymb.R;

/**
 * Created by Valentin on 09/04/2016.
 */
public class resumeRepas extends AppCompatActivity {
    private TextView syntheseRepas;
    private TextView totalcal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_repas);

        syntheseRepas = (TextView) findViewById(R.id.d_synthese);
        totalcal = (TextView) findViewById(R.id.d_total_calorie);

        HashMap<Aliment, Double> repas = ((syntheseRepas)getApplication()).getRepas();

        String s = "" ;
        int total = 0;
        for(Aliment aliment : repas.keySet()){
            s += repas.get(aliment) + " " + aliment.getNom_Aliment() + "\n valeur énergetique "
                    + aliment.getValeur_energetique() + "\n";
            total += aliment.getValeur_energetique();
        }

        syntheseRepas.setText(s);
        totalcal.setText("total calorie:" + total);

    }

}
