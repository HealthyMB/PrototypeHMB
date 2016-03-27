package efrei.healthymb;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

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

            default:
                break;
        }
    }
}
