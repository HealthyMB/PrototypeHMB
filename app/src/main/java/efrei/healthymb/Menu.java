package efrei.healthymb;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Menu extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b_diet: {
                Intent intent = new Intent(getApplicationContext(), Repas.class);
                startActivity(intent);
            }
            break;

            default:
                break;
        }
    }
}
