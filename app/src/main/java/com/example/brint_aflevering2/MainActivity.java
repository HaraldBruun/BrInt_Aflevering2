package com.example.brint_aflevering2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Galgelogik spil = new Galgelogik();
    private EditText gæt;
    private Button knap1, knap2, knap3;
    private TextView ord, tvBrugteBogstaver,textViewW,textViewL;
    private ImageView imageView;
    int wincounter, losscounter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        spil.nulstil();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gæt = findViewById(R.id.editText);
        ord = findViewById(R.id.textView2);
        tvBrugteBogstaver = findViewById(R.id.textView3);
        textViewW = findViewById(R.id.textViewW);
        textViewL = findViewById(R.id.textViewL);
        imageView = findViewById(R.id.imageView);

        knap1 = findViewById(R.id.button1);
        knap1.setOnClickListener(this);
        knap2 = findViewById(R.id.button2);
        knap2.setOnClickListener(this);
        knap3 = findViewById(R.id.button3);
        knap3.setOnClickListener(this);

        ord.setText(spil.getSynligtOrd());

    }

    @Override
    public void onClick(View v) {
        if (v == knap1) {

            spil.gætBogstav(gæt.getText().toString());
            ord.setText(spil.getSynligtOrd());
            String currentWord = "";
            imageViewChanger();
            for (int i = 0; i < spil.getBrugteBogstaver().size(); i++) {
                currentWord = currentWord + "  " + spil.getBrugteBogstaver().get(i);

            }
            if (!spil.erSidsteBogstavKorrekt() && spil.getAntalForkerteBogstaver() < 6){

                Toast.makeText(this,"Du har gættet forkert " + (spil.getAntalForkerteBogstaver())+ " gang(e)",Toast.LENGTH_SHORT).show();
            }
            else if (spil.erSidsteBogstavKorrekt() && !spil.erSpilletVundet()){
                Toast.makeText(this,"Du gættede rigtigt: '" + gæt.getText().toString().toUpperCase() + "' indgår i ordet",Toast.LENGTH_SHORT).show();
            }
            else if (spil.erSpilletVundet()) {
                 Toast.makeText(this, "Du vandt", Toast.LENGTH_SHORT).show();
                 wincounter++;
                 knap3.setVisibility(View.VISIBLE);
                 textViewW.setText("W = " + wincounter);
             }
            else if (spil.erSpilletTabt()){
                     Toast.makeText(this, "Du har tabt", Toast.LENGTH_SHORT).show();
                     losscounter++;
                     knap3.setVisibility(View.VISIBLE);
                     textViewL.setText("L = "+losscounter);
            }
            tvBrugteBogstaver.setText(currentWord);

        } else if (v == knap2) {
            Toast.makeText(this, spil.getOrdet(), Toast.LENGTH_SHORT).show();
        } else if (v == knap3) {
            spil.nulstil();
            imageViewChanger();
            knap3.setVisibility(View.INVISIBLE);
            ord.setText(spil.getSynligtOrd());
            tvBrugteBogstaver.setText("");
            Toast.makeText(this, "Du har genstartet spillet", Toast.LENGTH_SHORT).show();
        }
        gæt.setText("");
    }






    public void imageViewChanger(){
        if (spil.getAntalForkerteBogstaver() == 0){
            imageView.setImageResource(R.drawable.galge);
        }

        if(spil.getAntalForkerteBogstaver() == 1){
            imageView.setImageResource(R.drawable.forkert1);
        }
        if(spil.getAntalForkerteBogstaver() == 2){
            imageView.setImageResource(R.drawable.forkert2);
        }
        if(spil.getAntalForkerteBogstaver() == 3){
            imageView.setImageResource(R.drawable.forkert3);
        }
        if(spil.getAntalForkerteBogstaver() == 4){
            imageView.setImageResource(R.drawable.forkert4);
        }
        if(spil.getAntalForkerteBogstaver() == 5){
            imageView.setImageResource(R.drawable.forkert5);
        }
        if(spil.getAntalForkerteBogstaver() == 6){
            imageView.setImageResource(R.drawable.forkert6);
        }
    }

}

