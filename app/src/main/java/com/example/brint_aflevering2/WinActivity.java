package com.example.brint_aflevering2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class WinActivity extends AppCompatActivity {

    private TextView textViewGz, textViewWin, textViewWinGuesses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
        Bundle bundle = getIntent().getExtras();

        textViewGz = findViewById(R.id.textViewGz);
        textViewWin = findViewById(R.id.textViewWin);
        textViewWinGuesses = findViewById(R.id.textViewWinGuesses);

        textViewWin.setText(bundle.getString("spil.getOrdet"));
        textViewWin.setText("Du har vundet \n" + bundle.getInt("getWinCounter") + " gang(e)!");
        textViewWinGuesses.setText("Du brugte \n" + bundle.getInt("spil.getBrugteBogstaverSize") + " gæt.");
    }
}
