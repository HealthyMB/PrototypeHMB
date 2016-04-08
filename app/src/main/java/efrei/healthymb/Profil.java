package efrei.healthymb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import async.AsyncResponse;
import async.PostResponseAsyncTask;

/**
 * Created by PAUL on 16/03/2016.
 */
public class Profil extends Activity {

    private TextView sexeT, tailleT, poidsT, ageT;
    private CheckBox poidsBox, physiqueBox, muscuBox;
    private Button modifier;
    private int idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        initializeVariables();
        initializeData();
    }

    private void initializeVariables() {
        sexeT = (TextView) findViewById(R.id.sexeTid);
        tailleT = (TextView) findViewById(R.id.tailleTid);
        poidsT = (TextView) findViewById(R.id.poidsTid);
        ageT = (TextView) findViewById(R.id.ageTid);

        poidsBox = (CheckBox) findViewById(R.id.poidsBoxId);
        physiqueBox = (CheckBox) findViewById(R.id.physiqueBoxId);
        muscuBox = (CheckBox) findViewById(R.id.muscuBoxId);
        poidsBox.setClickable(false);
        physiqueBox.setClickable(false);
        muscuBox.setClickable(false);

        modifier = (Button) findViewById(R.id.modifierId);
        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profil.this, Formulaire.class);
                intent.putExtra("idUser", String.valueOf(idUser));
                startActivity(intent);
            }
        });
        Bundle extras = getIntent().getExtras();
        idUser = Integer.parseInt(extras.getString("idUser"));
    }

    private void initializeData() {
        HashMap postData = new HashMap();

        postData.put("idUser", String.valueOf(idUser));

        PostResponseAsyncTask task1 = new PostResponseAsyncTask(Profil.this, postData, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                if (s.contains("success")) {
                    String data[] = s.split(" ");
                    sexeT.setText((data[1].equals("1")) ? "masculin" : "feminin");

                    tailleT.setText(data[2] + " cm");
                    poidsT.setText(data[3] + " kg");
                    ageT.setText(data[4] + " ans");

                    if(data.length == 6) {
                        if (data[5].equals("1")) {
                            poidsBox.setChecked(true);
                        } else if (data[5].equals("2")) {
                            physiqueBox.setChecked(true);
                        } else if (data[5].equals("3")) {
                            muscuBox.setChecked(true);
                        } else {
                            Log.e("Erreur", "L'objectif reçu est incorrect!");
                        }
                    }
                }
            }
        });
        task1.execute("http://healthymb.no-ip.org:8080/PA8/profil.php");
    }

}
