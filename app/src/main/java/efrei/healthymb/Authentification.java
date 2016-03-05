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

public class Authentification extends AppCompatActivity implements View.OnClickListener {

    String loginEt = null;
    String mdpEt = null;
    String messagerror = "Error";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b_signin: {
                loginEt = ((EditText) findViewById(R.id.login)).getText().toString();
                mdpEt = ((EditText) findViewById(R.id.password)).getText().toString();
                User membre = new User(loginEt, mdpEt);
                if(loginEt.equals("Admin") && mdpEt.equals("mdp")) {
                    Intent intent = new Intent(Authentification.this, Menu.class);
                    startActivity(intent);
                }
                else{
                    TextView error = (TextView) findViewById(R.id.l_failed);
                    error.setText(messagerror);
                }
            }
            break;

            case R.id.b_checkco:
            {
                TextView e = (TextView) findViewById(R.id.L_Co);
                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    e.setText("Congrat ! You are connected to internet !");
                    Log.d("MyApp", "I am here");
                } else {
                    e.setText("Failed !");
                }
            }
            break;
        }
    }
}
