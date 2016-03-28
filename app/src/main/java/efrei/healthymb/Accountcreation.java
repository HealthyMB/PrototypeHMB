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

import java.util.HashMap;

import async.AsyncResponse;
import async.PostResponseAsyncTask;

public class Accountcreation extends AppCompatActivity implements View.OnClickListener {

    private String prenom;
    private String nom;
    private String mdp;
    private String confimMdp;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountcreation);
    }

    @Override
    public void onClick(View v) {
        prenom = ((EditText) findViewById(R.id.firstName)).getText().toString();
        nom = ((EditText) findViewById(R.id.lastName)).getText().toString();
        email = ((EditText) findViewById(R.id.email)).getText().toString();
        mdp = ((EditText) findViewById(R.id.password)).getText().toString();
        confimMdp = ((EditText) findViewById(R.id.confirmPassword)).getText().toString();

        if(prenom.isEmpty() || nom.isEmpty() || email.isEmpty() || mdp.isEmpty() || confimMdp.isEmpty()){
            Toast.makeText(Accountcreation.this, "Vous devez renseigner tous les champs!", Toast.LENGTH_LONG).show();
        } else if(!mdp.equals(confimMdp)) {
            Toast.makeText(Accountcreation.this, "Vos mots de passe doivent être identiques", Toast.LENGTH_LONG).show();
        } else {
            HashMap postData = new HashMap();

            postData.put("prenom", prenom);
            postData.put("nom", nom);
            postData.put("email", email);
            postData.put("mdp", mdp);

            PostResponseAsyncTask task1 = new PostResponseAsyncTask(Accountcreation.this, postData, new AsyncResponse() {
                @Override
                public void processFinish(String s) {
                    if (s.contains("success")) {
                        Toast.makeText(Accountcreation.this, "Compte créer", Toast.LENGTH_LONG).show();
                        Intent in = new Intent(Accountcreation.this, Formulaire.class);
                        in.putExtra("idUser",s.split(" ")[1]);
                        startActivity(in);
                    } else if(s.contains("Error1")){
                        Toast.makeText(Accountcreation.this, "Votre email est déjà utilisé!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Accountcreation.this, "Error unknown", Toast.LENGTH_LONG).show();
                    }
                }
            });
            task1.execute("http://healthymb.no-ip.org:8080/PA8/creerCompte.php");
        }


    }
}

