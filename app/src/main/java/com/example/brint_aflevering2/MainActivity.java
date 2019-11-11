package com.example.brint_aflevering2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Galgelogik spil = new Galgelogik();
    private EditText editTextGuess;
    private Button buttonGuess, buttonGetWord, buttonRestart, buttonDR, buttonRules;
    private TextView textViewHiddenWord, textViewUsedLetters,textViewWinCounter,textViewLossCounter;
    private ImageView imageViewGalge;
    int wincounter, losscounter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextGuess = findViewById(R.id.editTextGuess);
        textViewHiddenWord = findViewById(R.id.textViewHiddenWord);
        textViewUsedLetters = findViewById(R.id.textViewUsedLetters);
        textViewWinCounter = findViewById(R.id.textViewWinCounter);
        textViewLossCounter = findViewById(R.id.textViewLossCounter);
        imageViewGalge = findViewById(R.id.imageViewGalge);

        buttonGuess = findViewById(R.id.buttonGuess);
        buttonGuess.setOnClickListener(this);
        buttonGetWord = findViewById(R.id.buttonGetWord);
        buttonGetWord.setOnClickListener(this);
        buttonRestart = findViewById(R.id.buttonRestart);
        buttonRestart.setOnClickListener(this);
        buttonDR = findViewById(R.id.buttonDR);
        buttonDR.setOnClickListener(this);
        buttonRules = findViewById(R.id.buttonRules);
        buttonRules.setOnClickListener(this);

        editTextGuess.setShowSoftInputOnFocus(false);

        textViewHiddenWord.setText(spil.getSynligtOrd());
    }

    @Override
    public void onClick(View v) {
        if (v == buttonGuess) {

            spil.gætBogstav(editTextGuess.getText().toString());
            textViewHiddenWord.setText(spil.getSynligtOrd());
            String currentWord = "";
            imageViewChanger();
            for (int i = 0; i < spil.getBrugteBogstaver().size(); i++) {
                currentWord = currentWord + "  " + spil.getBrugteBogstaver().get(i);
            }

            if (editTextGuess.getText().toString().equals("")) {
                Toast.makeText(this, "Indtast bogstav", Toast.LENGTH_SHORT).show();
            } else if (!editTextGuess.getText().equals("")) {
                if (!spil.erSidsteBogstavKorrekt() && spil.getAntalForkerteBogstaver() < 6) {
                    if (spil.getAntalForkerteBogstaver() == 1) {
                        Toast.makeText(this, "Du har gættet forkert " + (spil.getAntalForkerteBogstaver()) + " gang.", Toast.LENGTH_SHORT).show();
                    } else if (spil.getAntalForkerteBogstaver() > 1) {
                        Toast.makeText(this, "Du har gættet forkert " + (spil.getAntalForkerteBogstaver()) + " gange.", Toast.LENGTH_SHORT).show();
                    }
                } else if (spil.erSidsteBogstavKorrekt() && !spil.erSpilletVundet()) {
                    Toast.makeText(this, "Du gættede rigtigt: '" + editTextGuess.getText().toString().toUpperCase() + "' indgår i ordet", Toast.LENGTH_SHORT).show();
                } else if (spil.erSpilletVundet()) {
                    Toast.makeText(this, "Du vandt", Toast.LENGTH_SHORT).show();
                    wincounter++;
                    buttonRestart.setVisibility(View.VISIBLE);
                    textViewWinCounter.setText("W = " + wincounter);
                    openWinActtivity();
                } else if (spil.erSpilletTabt()) {
                    Toast.makeText(this, "Du har tabt", Toast.LENGTH_SHORT).show();
                    losscounter++;
                    buttonRestart.setVisibility(View.VISIBLE);
                    textViewLossCounter.setText("L = " + losscounter);
                    openLossActivity();
                }
            }
            textViewUsedLetters.setText(currentWord);
        }
        else if (v == buttonGetWord) {
            Toast.makeText(this, spil.getOrdet(), Toast.LENGTH_SHORT).show();
        } else if (v == buttonRestart) {
            spil.nulstil();
            imageViewChanger();
            buttonRestart.setVisibility(View.INVISIBLE);
            textViewHiddenWord.setText(spil.getSynligtOrd());
            textViewUsedLetters.setText("");
            Toast.makeText(this, "Du har genstartet spillet", Toast.LENGTH_SHORT).show();
        } else if (v == buttonDR){
            new AsyncTask() {

                @Override
                protected Object doInBackground(Object... arg0) {
                    try {
                        spil.hentOrdFraDr();
                        return "Success";
                    } catch (Exception e) {
                        e.printStackTrace();
                        return "Fail: " + e;
                    }
                }

                @Override
                protected void onPostExecute(Object result) {
                    buttonDR.setText(result.toString());
                    textViewHiddenWord.setText(spil.getSynligtOrd());
                    textViewUsedLetters.setText("");
                    imageViewChanger();
                    final Handler handler = new Handler ();
                    handler.postDelayed(new Runnable(){
                        @Override
                        public void run(){
                            buttonDR.setText("nyt DR ord");
                        }
                    }, 5000);
                }
            }.execute();
        } else if (v == buttonRules){
            openRulesActtivity();
        }
        editTextGuess.setText("");
    }

    public void imageViewChanger(){
        if (spil.getAntalForkerteBogstaver() == 0){
            imageViewGalge.setImageResource(R.drawable.galge);
        }

        if(spil.getAntalForkerteBogstaver() == 1){
            imageViewGalge.setImageResource(R.drawable.forkert1);
        }
        if(spil.getAntalForkerteBogstaver() == 2){
            imageViewGalge.setImageResource(R.drawable.forkert2);
        }
        if(spil.getAntalForkerteBogstaver() == 3){
            imageViewGalge.setImageResource(R.drawable.forkert3);
        }
        if(spil.getAntalForkerteBogstaver() == 4){
            imageViewGalge.setImageResource(R.drawable.forkert4);
        }
        if(spil.getAntalForkerteBogstaver() == 5){
            imageViewGalge.setImageResource(R.drawable.forkert5);
        }
        if(spil.getAntalForkerteBogstaver() == 6){
            imageViewGalge.setImageResource(R.drawable.forkert6);
        }
    }
    public void openLossActivity(){
        Intent intent = new Intent(this, LossActivity.class);
        startActivity(intent);
    }
    public void openWinActtivity(){
        Intent intent = new Intent(this,WinActivity.class);
        startActivity(intent);
    }
    public void openRulesActtivity(){
        Intent intent = new Intent(this,RulesActivity.class);
        startActivity(intent);
    }
}

