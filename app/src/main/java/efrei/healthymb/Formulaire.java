package efrei.healthymb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Normalizer;
import java.util.HashMap;

import async.AsyncResponse;
import async.PostResponseAsyncTask;

public class Formulaire extends AppCompatActivity {

    private int idUser;
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
        initializeData();

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

        Bundle extras = getIntent().getExtras();
        idUser = Integer.parseInt(extras.getString("idUser"));
    }

    private void initializeListener() {

        masculin.setOnClickListener(new CheckBoxListenerGenre());
        feminin.setOnClickListener(new CheckBoxListenerGenre());

        poidsBox.setOnClickListener(new CheckBoxListenerObjectif());
        physiqueBox.setOnClickListener(new CheckBoxListenerObjectif());
        muscuBox.setOnClickListener(new CheckBoxListenerObjectif());


        tailleBar.setOnSeekBarChangeListener(new seekBarListener(tailleValeur, " cm"));
        poidsBar.setOnSeekBarChangeListener(new seekBarListener(poidsValeur, " kg"));
        ageBar.setOnSeekBarChangeListener(new seekBarListener(ageValeur, " ans"));

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Données à enregistrer
                Boolean isMasculin = masculin.isChecked();
                Boolean isFeminin = feminin.isChecked();
                Boolean perdrePoids = poidsBox.isChecked();
                Boolean physique = physiqueBox.isChecked();
                Boolean muscu = muscuBox.isChecked();
                int taille = Integer.parseInt(String.valueOf(tailleValeur.getText()).split(" ") [0]);
                int poids = Integer.parseInt(String.valueOf(poidsValeur.getText()).split(" ") [0]);
                int age = Integer.parseInt(String.valueOf(ageValeur.getText()).split(" ") [0]);

                // Vérification que toutes les données nécessaires sont bien saisis :
                if (!isMasculin && !isFeminin) {
                    Toast.makeText(Formulaire.this, "Vous devez renseigner votre genre!", Toast.LENGTH_LONG).show();
                } else if (taille == 0){
                    Toast.makeText(Formulaire.this, "Vous devez renseigner votre taille!", Toast.LENGTH_LONG).show();
                } else if (poids == 0){
                    Toast.makeText(Formulaire.this, "Vous devez renseigner votre poids!", Toast.LENGTH_LONG).show();
                } else if (age == 0){
                    Toast.makeText(Formulaire.this, "Vous devez renseigner votre age!", Toast.LENGTH_LONG).show();
                } else if (!perdrePoids && !physique && !muscu) {
                    Toast.makeText(Formulaire.this, "Vous devez renseigner au moins un objectif!", Toast.LENGTH_LONG).show();
                } else {
                    HashMap postData = new HashMap();

                    postData.put("idUser", String.valueOf(idUser));
                    postData.put("genre", isMasculin ? "masculin" : "feminin");
                    postData.put("taille", String.valueOf(taille));
                    postData.put("poids", String.valueOf(poids));
                    postData.put("age", String.valueOf(age));

                    PostResponseAsyncTask task1 = new PostResponseAsyncTask(Formulaire.this, postData, new AsyncResponse() {
                        @Override
                        public void processFinish(String s) {
                            if(s.contains("success")) {
                                Intent intent = new Intent(Formulaire.this, MenuActivity.class);
                                intent.putExtra("idUser", String.valueOf(idUser));
                                startActivity(intent);
                            } else {
                                Toast.makeText(Formulaire.this, "Connection failed!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                    task1.execute("http://healthymb.no-ip.org:8080/PA8/formulaire.php");

                }
            }
        });
    }

    private void initializeData() {
        HashMap postData = new HashMap();

        postData.put("idUser", String.valueOf(idUser));

        PostResponseAsyncTask task1 = new PostResponseAsyncTask(Formulaire.this, postData, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                if (s.contains("success")) {
                    String data[] = s.split(" ");

                    if(data[1].equals("1")) {
                        masculin.setChecked(true);
                    } else {
                        feminin.setChecked(true);
                    }

                    tailleBar.setProgress(Integer.parseInt(data[2]));
                    poidsBar.setProgress(Integer.parseInt(data[3]));
                    ageBar.setProgress(Integer.parseInt(data[4]));

                    tailleValeur.setText(data[2] + " cm");
                    poidsValeur.setText(data[3] + " kg");
                    ageValeur.setText(data[4] + " ans");
                }
            }
        });
        task1.execute("http://healthymb.no-ip.org:8080/PA8/initFormulaire.php");
    }


    class CheckBoxListenerGenre implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // On s'assure qu'une seule case peut-être cochée à la fois
            masculin.setChecked(false);
            feminin.setChecked(false);
            ((CheckBox) v).setChecked(true);
        }
    }

    class CheckBoxListenerObjectif implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // On s'assure qu'une seule case peut-être cochée à la fois
            poidsBox.setChecked(false);
            physiqueBox.setChecked(false);
            muscuBox.setChecked(false);
            ((CheckBox) v).setChecked(true);
        }
    }

    class seekBarListener implements SeekBar.OnSeekBarChangeListener {
        private int progress = 0;
        private TextView textView;
        private String unite;

        public seekBarListener(TextView textView, String unite) {
            this.textView = textView;
            this.unite = unite;
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


