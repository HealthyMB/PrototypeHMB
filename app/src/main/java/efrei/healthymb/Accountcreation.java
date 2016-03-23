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
import android.widget.Toast;

public class Accountcreation extends AppCompatActivity implements View.OnClickListener {

    String loginEt;
    String mdpEt;
    String confimMdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountcreation);
    }

    @Override
    public void onClick(View v) {
        loginEt = ((EditText) findViewById(R.id.login)).getText().toString();
        mdpEt = ((EditText) findViewById(R.id.password)).getText().toString();
        confimMdp = ((EditText) findViewById(R.id.confirmPassword)).getText().toString();

        if(loginEt.isEmpty() || mdpEt.isEmpty() || confimMdp.isEmpty()){
            Toast.makeText(Accountcreation.this, "Vous devez renseigner tous les champs!", Toast.LENGTH_LONG).show();
        } else if(!mdpEt.equals(confimMdp)) {
            Toast.makeText(Accountcreation.this, "Vos mots de passe doivent être identiques", Toast.LENGTH_LONG).show();
        } else {
            User membre = new User(loginEt, mdpEt);

            Intent intent = new Intent(Accountcreation.this, Formulaire.class);
            startActivity(intent);
        }
    }
}
