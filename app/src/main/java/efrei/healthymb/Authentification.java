package efrei.healthymb;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import async.AsyncResponse;
import async.PostResponseAsyncTask;

public class Authentification extends AppCompatActivity implements View.OnClickListener {

    EditText emailEt;
    EditText mdpEt;
    Button btnLogin;
    final String LOG = "LoginActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);

        emailEt = ((EditText) findViewById(R.id.login));
        mdpEt = ((EditText) findViewById(R.id.password));
        btnLogin = (Button) findViewById(R.id.b_signin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap postData = new HashMap();

                String email = emailEt.getText().toString();
                String password = mdpEt.getText().toString();

                postData.put("txtEmail", email );
                postData.put("txtPassword", password);
                PostResponseAsyncTask task1 = new PostResponseAsyncTask(Authentification.this, postData, new AsyncResponse()
                {
                            @Override
                            public void processFinish(String s) {
                                Log.d("Page Web", s);
                                if(s.contains("success")){
                                    Toast.makeText(Authentification.this, "Sucessfully Login", Toast.LENGTH_LONG).show();
                                    Intent in = new Intent(Authentification.this, Formulaire.class);
                                    startActivity(in);
                                }
                                else{
                                    Toast.makeText(Authentification.this, "Try Again", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                //task1.execute("http://urlici");
                task1.execute("http://healthymb.alwaysdata.net/");

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

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
