package sport;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import async.AsyncResponse;
import async.PostResponseAsyncTask;
import efrei.healthymb.R;

/**
 * Created by PAUL on 28/03/2016.
 */
public class SportExercice extends Activity {

    private long idSeance;
    private String nomSeance;
    private int idUser;
    private TextView titre;
    private ListView listeExo;
    private List<Exercice> exos = new ArrayList<Exercice>();

    // @TODO Gerer les vidéos :
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sportexercice);

        Bundle extras = getIntent().getExtras();
        idSeance = Long.parseLong(extras.getString("idSeance"));
        idUser = Integer.parseInt(extras.getString("idUser"));
        nomSeance = extras.getString("nomSeance");

        titre = (TextView) findViewById(R.id.titreSeance);
        titre.setText(nomSeance);

        listeExo = (ListView) findViewById(R.id.listeExo);

        HashMap postData = new HashMap();
        postData.put("idSeance", String.valueOf(idSeance));

        PostResponseAsyncTask task1 = new PostResponseAsyncTask(SportExercice.this, postData, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                Log.e("Test", s);
                if(s.contains("success")) {
                    String data[] = s.split(";");
                    int i = 1;
                    while(i < data.length) {
                        int cpt = Integer.parseInt(data[i]);
                        for(int j = 0; j < cpt; j++) {
                            int pause = (j < cpt - 1) ? Integer.parseInt(data[i + 7]) : Integer.parseInt(data[i + 8]);
                            exos.add(new Exercice(Integer.parseInt(data[i+1]), data[i+2], data[i+3],
                                    data[i+4],Integer.parseInt(data[i+5]), (data[i+6]).equals("1") ? true : false ,pause));
                        }
                        i += 9;
                    }
                    ExerciceAdapter adapter = new ExerciceAdapter(exos,SportExercice.this);
                    listeExo.setAdapter(adapter);
                } else {
                    Toast.makeText(SportExercice.this, "Connection failed!", Toast.LENGTH_LONG).show();
                }
            }
        });

        task1.execute("http://healthymb.no-ip.org:8080/PA8/exercice.php");


        listeExo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view,
                                    int position,
                                    long id) {
                // @TODO Definir ce qu'on veut faire lors d'un click :
                /*
                Intent intent = new Intent(SportExercice.this, SportExercice.class);
                intent.putExtra("idSeance", String.valueOf(id));
                intent.putExtra("idUser", String.valueOf(idUser));
                startActivity(intent);
                */
            }
        });

    }

}
