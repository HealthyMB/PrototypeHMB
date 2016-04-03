package efrei.healthymb;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class Viennoiserie extends AppCompatActivity implements View.OnClickListener{
    private SeekBar croissantBar, pain_chocBar, chaussonsBar,
            pain_raisinBar, pain_laitBar, briocheBar;
    private TextView croissant_Portion, pain_choc_Portion, chaussons_Portion,
            pain_raisin_Portion, pain_lait_Portion, brioche_Portion ;
    private Double portion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viennoiserie);
        initializeVariables();
        initializeListener();

    }
    private void initializeVariables() {
        croissantBar = (SeekBar) findViewById(R.id.d_croisaant_BarId);
        pain_chocBar = (SeekBar) findViewById(R.id.d_pain_au_chocolat_Bar_Id);
        chaussonsBar = (SeekBar) findViewById(R.id.d_chaussons_Bar_Id);
        pain_raisinBar =(SeekBar) findViewById(R.id.d_pain_au_raisins_Bar_Id);
        pain_laitBar = (SeekBar) findViewById(R.id.d_pain_au_lait_Bar_Id);
        briocheBar = (SeekBar) findViewById(R.id.d_brioche_Bar_Id);
        croissant_Portion = (TextView)findViewById(R.id.d_croissant_portion_Id);
        pain_choc_Portion = (TextView)findViewById(R.id.d_pain_au_chocolat_portion_Id);
        chaussons_Portion = (TextView)findViewById(R.id.d_chaussons_portion_Id);
        pain_raisin_Portion = (TextView)findViewById(R.id.d_pain_au_raisins_portion_Id);
        pain_lait_Portion = (TextView)findViewById(R.id.d_pain_au_lait_portion_Id);
        brioche_Portion = (TextView)findViewById(R.id.d_brioche_portion_Id);



    }
    private void initializeListener() {

        croissantBar.setOnSeekBarChangeListener(new seekBarListener(croissant_Portion));
        pain_chocBar.setOnSeekBarChangeListener(new seekBarListener(pain_choc_Portion));
        chaussonsBar.setOnSeekBarChangeListener(new seekBarListener(chaussons_Portion));
        pain_raisinBar.setOnSeekBarChangeListener(new seekBarListener(pain_raisin_Portion));
        pain_laitBar.setOnSeekBarChangeListener(new seekBarListener(pain_lait_Portion));
        briocheBar.setOnSeekBarChangeListener(new seekBarListener(brioche_Portion));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.d_valider: {

                Aliment croissant = new Aliment(1,"Croissant",180,"Patisserie");
                Aliment pain_au_lait = new Aliment(1,"Pain au lait",120,"Patisserie");

                try {
                    String croiss = croissant_Portion.getText().toString().substring(0,1);
                    String pain = pain_lait_Portion.getText().toString().substring(0,1);
                    Log.i("test2", "in the error");
                    Log.d("portion:" ,croiss);
                     portion = parseDouble(croiss);
                    ((syntheseRepas)getApplication()).addAliment(croissant, portion);
                    portion = parseDouble(pain);
                    ((syntheseRepas)getApplication()).addAliment(pain_au_lait, portion);
                    Toast.makeText(Viennoiserie.this, "Repas enregistrer !!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), Petit_Dejeuner.class);
                    startActivity(intent);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            break;
            case R.id.d_finish: {
                Intent intent = new Intent(getApplicationContext(), resumeRepas.class);
                startActivity(intent);
            }
            break;
            case R.id.d_modifier: {
                Intent intent = new Intent(getApplicationContext(), Viennoiserie.class);
                startActivity(intent);
            }
            break;
            default:
                break;
        }


    }
    class seekBarListener implements SeekBar.OnSeekBarChangeListener {
        private int progress = 0;
        private TextView textView;
        private String unite;

        public seekBarListener(TextView textView) {
            this.textView = textView;
            this.unite = textView.getText().toString().substring(1);
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
            progress = progressValue;
            textView.setText(progress + unite);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            textView.setText(progress + unite);
        }
    }
}
