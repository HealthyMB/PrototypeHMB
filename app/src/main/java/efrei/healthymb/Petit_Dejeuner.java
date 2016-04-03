package efrei.healthymb;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import static java.lang.Double.parseDouble;

public class Petit_Dejeuner extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petit__dejeuner);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.d_vienoiserie: {
                Intent intent = new Intent(getApplicationContext(), Viennoiserie.class);
                startActivity(intent);
            }
            break;
            case R.id.d_valider: {

                if (((syntheseRepas)getApplication()).getRepas().size() == 0)
                Toast.makeText(Petit_Dejeuner.this, "Rentrer au moins un repas", Toast.LENGTH_LONG).show();
                else
                {
                    Toast.makeText(Petit_Dejeuner.this, "Repas enregistrer !!", Toast.LENGTH_LONG).show();
                    //Intent intent = new Intent(getApplicationContext(), Viennoiserie.class);
                    //startActivity(intent);
                }

            }
            break;
            case R.id.d_finish: {
                Intent intent = new Intent(getApplicationContext(), resumeRepas.class);
                startActivity(intent);
            }
            break;
            default:
                break;
        }
    }
}
