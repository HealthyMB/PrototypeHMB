package efrei.healthymb;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.HashMap;

import async.AsyncResponse;
import async.PostResponseAsyncTask;
import sport.Seance;
import sport.SeanceAdapter;
import sport.SportExercice;
import sport.SportSeance;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
        init_profil();
        initializeData();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        ViewFlipper vf = (ViewFlipper)findViewById(R.id.vf);

        if (id == R.id.nav_sport) {
            vf.setDisplayedChild(1);


        }
        else if (id == R.id.nav_diet) {
            vf.setDisplayedChild(1);
        }
        else if (id == R.id.nav_stats) {
            vf.setDisplayedChild(2);
        }
        else if (id == R.id.nav_profil) {
            vf.setDisplayedChild(3);
        }
        else if (id == R.id.nav_manage) {
            vf.setDisplayedChild(5);
        }
        else if (id == R.id.nav_exit) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            if (android.os.Build.VERSION.SDK_INT >= 19) {
                getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                                View.SYSTEM_UI_FLAG_FULLSCREEN |
                                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }
        }
    }

    private ListView listeSeance;
    private ArrayList<Seance> seances = new ArrayList<Seance>();
    private int idUser = 1; // @TODO enlever le = 1 (juste pour les tests)

    public void init(){
        seances = new ArrayList<Seance>();
        // @TODO à remettre apres les tests
        /*
        Bundle extras = getIntent().getExtras();
        idUser = Integer.parseInt(extras.getString("idUser"));
        */
        listeSeance = (ListView) findViewById(R.id.listeSeance);

        HashMap postData = new HashMap();
        postData.put("idUser", String.valueOf(idUser));

        PostResponseAsyncTask task1 = new PostResponseAsyncTask(MenuActivity.this, postData, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                Log.e("error", s);
                if(s.contains("success")) {
                    String data[] = s.split(";");
                    int i = 1;
                    while(i < data.length) {
                        seances.add(new Seance(Integer.parseInt(data[i++]), data[i++], Integer.parseInt(data[i++]), Integer.parseInt(data[i++])));
                    }
                    SeanceAdapter adapter = new SeanceAdapter(seances,MenuActivity.this);
                    listeSeance.setAdapter(adapter);
                } else {
                    Toast.makeText(MenuActivity.this, "Connection failed!", Toast.LENGTH_LONG).show();
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
                Intent intent = new Intent(MenuActivity.this, SportExercice.class);
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

    private TextView sexeT, tailleT, poidsT, ageT;
    private CheckBox poidsBox, physiqueBox, muscuBox;
    private Button modifier;

    public void init_profil(){

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
                Intent intent = new Intent(MenuActivity.this, Formulaire.class);
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

        PostResponseAsyncTask task1 = new PostResponseAsyncTask(MenuActivity.this, postData, new AsyncResponse() {
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

