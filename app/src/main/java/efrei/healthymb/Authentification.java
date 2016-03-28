package efrei.healthymb;

import android.app.ProgressDialog;
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
import android.widget.Toast;

public class Authentification extends AppCompatActivity implements View.OnClickListener {

    private String loginEt;
    private String mdpEt;
    private String messagerror = "Erreur de login ou mot de passe";


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
                    Intent intent = new Intent(Authentification.this, Formulaire.class);
                    startActivity(intent);
                }
                else{
                    TextView error = (TextView) findViewById(R.id.l_failed);
                    error.setText(messagerror);
                }
            }
            break;

            case R.id.b_create:
            {
                Intent intent = new Intent(Authentification.this, Accountcreation.class);
                startActivity(intent);
            }
            break;

            default:
                break;
        }
    }
}
