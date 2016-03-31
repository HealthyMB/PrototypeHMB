package efrei.healthymb;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Repas extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repas);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.d_petit_dej: {
                Intent intent = new Intent(getApplicationContext(), Petit_Dejeuner.class);
                startActivity(intent);
            }
            break;
            case R.id.d_dej: {
                Intent intent = new Intent(getApplicationContext(), Dejeuner.class);
                startActivity(intent);
            }
            break;
            case R.id.d_diner: {
                Intent intent = new Intent(getApplicationContext(), Diner.class);
                startActivity(intent);
            }
            break;
            case R.id.d_gouter: {
                Intent intent = new Intent(getApplicationContext(), Gouter.class);
                startActivity(intent);
            }
            break;

            default:
                break;
        }
    }
}
