package diet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import efrei.healthymb.R;

/**
 * Created by Valentin on 09/04/2016.
 */
public class Repas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repas);


    }

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
