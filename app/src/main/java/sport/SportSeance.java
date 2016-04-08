package sport;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import async.AsyncResponse;
import async.PostResponseAsyncTask;
import efrei.healthymb.Profil;
import efrei.healthymb.R;


/**
 * Created by PAUL on 28/03/2016.
 */
public class SportSeance extends Activity {

    private ListView listeSeance;
    private ArrayList<Seance> seances = new ArrayList<Seance>();
    private int idUser = 1; // @TODO enlever le = 1 (juste pour les tests)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sportseance);

    }

    public void init(Context context){
        seances = new ArrayList<Seance>();
        // @TODO à remettre apres les tests
        /*
        Bundle extras = getIntent().getExtras();
        idUser = Integer.parseInt(extras.getString("idUser"));
        */
        listeSeance = (ListView)  ((Activity)context).findViewById(R.id.listeSeance);

        HashMap postData = new HashMap();
        postData.put("idUser", String.valueOf(idUser));

        PostResponseAsyncTask task1 = new PostResponseAsyncTask(SportSeance.this, postData, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                Log.e("error", s);
                if(s.contains("success")) {
                    String data[] = s.split(";");
                    int i = 1;
                    while(i < data.length) {
                        seances.add(new Seance(Integer.parseInt(data[i++]), data[i++], Integer.parseInt(data[i++]), Integer.parseInt(data[i++])));
                    }
                    SeanceAdapter adapter = new SeanceAdapter(seances,SportSeance.this);
                    listeSeance.setAdapter(adapter);
                } else {
                    Toast.makeText(SportSeance.this, "Connection failed!", Toast.LENGTH_LONG).show();
                }
            }
        });

        task1.execute("http://healthymb.no-ip.org:8080/PA8/seance.php");

        listeSeance.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view,
                                    int position,
                                    long id) {
                Intent intent = new Intent(SportSeance.this, SportExercice.class);
                intent.putExtra("idSeance", String.valueOf(id));
                intent.putExtra("idUser", String.valueOf(idUser));
                intent.putExtra("nomSeance", findNomSeanceById(id));
                startActivity(intent);
            }
        });


    }




    private String findNomSeanceById(long id) {
        for(Seance seance : seances){
            if(seance.getId() == id) {
                return seance.getNom();
            }
        }
        return null;
    }
}
