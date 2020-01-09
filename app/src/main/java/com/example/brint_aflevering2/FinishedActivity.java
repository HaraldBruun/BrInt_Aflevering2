package com.example.brint_aflevering2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class FinishedActivity extends AppCompatActivity {
     private  ArrayList<Highscore> highscores = new ArrayList<Highscore>(){
        {
            add(new Highscore("Harald",100,3));

            add(new Highscore("Bruun",20,3));
        }

    };

     private TextView textViewUnlucky2,textViewFinished, textViewInfoScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished);
        Bundle bundle = getIntent().getExtras();

        textViewUnlucky2 = findViewById(R.id.textViewUnlucky2);
        textViewFinished = findViewById(R.id.textViewFinished);
        textViewInfoScore = findViewById(R.id.textViewInfoScore);

        textViewFinished.setTextSize(14);
        textViewFinished.setText("Du har tabt. \n Du lod hangman dø " + bundle.getInt("getLossCounter") + " gange.");

        //for (Highscore highscore : highscores){
        //    textViewFinished.setText("Name: " + highscores.get(i).getName() + "Wins: " + highscores.get(i).getWinCounter()+ " Loss: " + highscores.get(i).getLossCounter());
        //}
        //textViewFinished.setText("Du har tabt \n" + bundle.getInt("getLossCounter") + " gang(e)!");


    }
}
