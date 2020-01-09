package com.example.brint_aflevering2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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
    private int winCounter = 0, lossCounter = 0;
    private boolean gameOver = false;


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
        buttonGetWord = findViewById(R.id.buttonGetWord);
        buttonRestart = findViewById(R.id.buttonRestart);
        buttonDR = findViewById(R.id.buttonDR);
        buttonRules = findViewById(R.id.buttonRules);

        buttonGuess.setOnClickListener(this);
        buttonGetWord.setOnClickListener(this);
        buttonRestart.setOnClickListener(this);
        buttonDR.setOnClickListener(this);
        buttonRules.setOnClickListener(this);
        //editTextGuess.setImeActionLabel("", KeyEvent.KEYCODE_ENTER);

        editTextGuess.setShowSoftInputOnFocus(false);

        textViewHiddenWord.setText(spil.getSynligtOrd());

        winCounter = getPreferences(winCounter).getInt("sWinCounter",winCounter);
        lossCounter = getPreferences(lossCounter).getInt("sLossCounter",lossCounter);

        updateCounters();
    }

    @Override
    public void onClick(View v) {
        if (v == buttonGuess) {
            guess();
        } else if (v == buttonGetWord) {
            Toast.makeText(this, spil.getOrdet(), Toast.LENGTH_SHORT).show();
        } else if (v == buttonRestart) {
            spil.nulstil();
            imageViewChanger();
            //buttonRestart.setVisibility(View.INVISIBLE);
            textViewHiddenWord.setText(spil.getSynligtOrd());
            textViewUsedLetters.setText("");
            gameOver = false;
            Toast.makeText(this, "Du har genstartet spillet", Toast.LENGTH_SHORT).show();
        } else if (v == buttonDR){
           getWordsFromDr();
        } else if (v == buttonRules){
            openRulesActivity();
        }
        editTextGuess.setText("");
    }

    @Override
    protected void onResume() {
        super.onResume();
        spil.nulstil();
        imageViewChanger();
        //buttonRestart.setVisibility(View.INVISIBLE);
        textViewHiddenWord.setText(spil.getSynligtOrd());
        textViewUsedLetters.setText("");
        gameOver = false;
    }

    public void imageViewChanger(){
        if (spil.getAntalForkerteBogstaver() == 0){ imageViewGalge.setImageResource(R.drawable.galge); }
        else if(spil.getAntalForkerteBogstaver() == 1){ imageViewGalge.setImageResource(R.drawable.forkert1); }
        else if(spil.getAntalForkerteBogstaver() == 2){ imageViewGalge.setImageResource(R.drawable.forkert2); }
        else if(spil.getAntalForkerteBogstaver() == 3){ imageViewGalge.setImageResource(R.drawable.forkert3); }
        else if(spil.getAntalForkerteBogstaver() == 4){ imageViewGalge.setImageResource(R.drawable.forkert4); }
        else if(spil.getAntalForkerteBogstaver() == 5){ imageViewGalge.setImageResource(R.drawable.forkert5); }
        else if(spil.getAntalForkerteBogstaver() == 6){ imageViewGalge.setImageResource(R.drawable.forkert6); }
    }
    public void openLossActivity(){
        Intent lossIntent = new Intent(this, LossActivity.class);
        lossIntent.putExtra("spil.getAntalForkerteBogstaver",spil.getAntalForkerteBogstaver());
        lossIntent.putExtra("spil.getBrugteBogstaverSize",spil.getBrugteBogstaver().size());
        lossIntent.putExtra("spil.getOrdet",spil.getOrdet());
        lossIntent.putExtra("getLossCounter", lossCounter);
        startActivity(lossIntent);
    }
    public void openWinActivity(){
        Intent winIntent = new Intent(this,WinActivity.class);
        winIntent.putExtra("spil.getAntalForkerteBogstaver",spil.getAntalForkerteBogstaver());
        winIntent.putExtra("spil.getBrugteBogstaverSize",spil.getBrugteBogstaver().size());
        winIntent.putExtra("spil.getOrdet",spil.getOrdet());
        winIntent.putExtra("getWinCounter", winCounter);
        startActivity(winIntent);
    }
    public void openRulesActivity(){
        Intent rulesIntent = new Intent(this,RulesActivity.class);
        startActivity(rulesIntent);
    }
    public void guess(){
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
                if (!gameOver) {
                    winCounter++;
                }
                //buttonRestart.setVisibility(View.VISIBLE);
                textViewWinCounter.setText("W = " + winCounter);
                SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("sWinCounter", winCounter);
                editor.commit();
                gameOver = true;
                openWinActivity();
            } else if (spil.erSpilletTabt()) {
                Toast.makeText(this, "Du har tabt", Toast.LENGTH_SHORT).show();
                if (!gameOver){
                    lossCounter++;
                    SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("sLossCounter", lossCounter);
                    editor.commit();
                    Log.d(sharedPreferences.getAll().toString(), "123123");
                }
                //buttonRestart.setVisibility(View.VISIBLE);
                textViewLossCounter.setText("L = " + lossCounter);
                gameOver = true;
                openLossActivity();
            }
        }
        textViewUsedLetters.setText(currentWord);
    }

    public void getWordsFromDr(){
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
                gameOver = false;
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
    }
    public void updateCounters(){
        textViewWinCounter.setText("W = " + winCounter);
        textViewLossCounter.setText("L = " + lossCounter);
    }


}

