package efrei.healthymb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Formulaire extends AppCompatActivity {
    private CheckBox masculin, feminin;
    private CheckBox poidsBox, physiqueBox, muscuBox;
    private Button valider;
    private SeekBar tailleBar, poidsBar, ageBar;
    private TextView tailleValeur, poidsValeur, ageValeur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire);
        initializeVariables();
        initializeListener();
    }

    private void initializeVariables() {
        masculin = (CheckBox) findViewById(R.id.masculinId);
        feminin = (CheckBox) findViewById(R.id.femininId);
        poidsBox = (CheckBox) findViewById(R.id.poidsBoxId);
        physiqueBox = (CheckBox) findViewById(R.id.physiqueBoxId);
        muscuBox = (CheckBox) findViewById(R.id.muscuBoxId);
        valider = (Button) findViewById(R.id.validerId);
        tailleBar = (SeekBar) findViewById(R.id.tailleBarId);
        poidsBar = (SeekBar) findViewById(R.id.poidsBarId);
        ageBar = (SeekBar) findViewById(R.id.ageBarId);
        tailleValeur = (TextView) findViewById(R.id.tailleValeurId);
        poidsValeur = (TextView) findViewById(R.id.poidsValeurId);
        ageValeur = (TextView) findViewById(R.id.ageValeurId);
    }

    private void initializeListener() {

        masculin.setOnClickListener(new CheckBoxListener());
        feminin.setOnClickListener(new CheckBoxListener());

        tailleBar.setOnSeekBarChangeListener(new seekBarListener(tailleValeur));
        poidsBar.setOnSeekBarChangeListener(new seekBarListener(poidsValeur));
        ageBar.setOnSeekBarChangeListener(new seekBarListener(ageValeur));

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Données à enregistrer
                Boolean isMasculin = masculin.isChecked();
                Boolean isFeminin = feminin.isChecked();
                Boolean perdrePoids = poidsBox.isChecked();
                Boolean physique = physiqueBox.isChecked();
                Boolean muscu = muscuBox.isChecked();
                int taille = Integer.parseInt(String.valueOf(tailleValeur.getText().charAt(0)));
                int poids = Integer.parseInt(String.valueOf(poidsValeur.getText().charAt(0)));
                int age = Integer.parseInt(String.valueOf(ageValeur.getText().charAt(0)));

                // Vérification que toutes les données nécessaires sont bien saisis :
                if (!isMasculin && !isFeminin) {
                    Toast.makeText(Formulaire.this, "Vous devez renseigner votre genre!", Toast.LENGTH_LONG).show();
                } else if (!perdrePoids && !physique && !muscu) {
                    Toast.makeText(Formulaire.this, "Vous devez renseigner au moins un objectif!", Toast.LENGTH_LONG).show();
                } else if (taille == 0){
                    Toast.makeText(Formulaire.this, "Vous devez renseigner votre taille!", Toast.LENGTH_LONG).show();
                } else if (poids == 0){
                    Toast.makeText(Formulaire.this, "Vous devez renseigner votre poids!", Toast.LENGTH_LONG).show();
                } else if (age == 0){
                    Toast.makeText(Formulaire.this, "Vous devez renseigner votre age!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Formulaire.this, "Go Profil", Toast.LENGTH_LONG).show();
                }


            }
        });
    }


    class CheckBoxListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // On s'assure qu'une seule case peut-être cochée à la fois
            masculin.setChecked(false);
            feminin.setChecked(false);
            ((CheckBox) v).setChecked(true);
            Intent intent = new Intent(Formulaire.this, Menu.class);
            startActivity(intent);
        }
    }

    class seekBarListener implements SeekBar.OnSeekBarChangeListener {
        private int progress = 0;
        private TextView textView;
        private String unite;

        public seekBarListener(TextView textView) {
            this.textView = textView;
            this.unite = textView.getText().toString().substring(1);
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
            progress = progressValue;
            textView.setText(progress + unite);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            textView.setText(progress + unite);
        }
    }


}


