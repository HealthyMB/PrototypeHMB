package efrei.healthymb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by PAUL on 16/03/2016.
 */
public class Profil extends Activity {

    private TextView sexeT, tailleT, poidsT, ageT;
    private CheckBox poidsBox, physiqueBox, muscuBox;
    private Button modifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire);
        initializeVariables();
    }

    private void initializeVariables() {
        sexeT = (TextView) findViewById(R.id.sexeTid);
        tailleT = (TextView) findViewById(R.id.tailleTid);
        poidsT = (TextView) findViewById(R.id.poidsTid);
        ageT = (TextView) findViewById(R.id.ageTid);
        poidsBox = (CheckBox) findViewById(R.id.poidsBoxId);
        physiqueBox = (CheckBox) findViewById(R.id.physiqueBoxId);
        muscuBox = (CheckBox) findViewById(R.id.muscuBoxId);
        modifier = (Button) findViewById(R.id.modifierId);
        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profil.this, Formulaire.class);
                startActivity(intent);
            }
        });
    }
}
