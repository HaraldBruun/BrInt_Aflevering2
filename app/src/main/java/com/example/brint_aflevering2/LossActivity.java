package com.example.brint_aflevering2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

public class LossActivity extends AppCompatActivity {

    private TextView textViewUnlucky, textViewLose, textViewLossGuesses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loss);
        Bundle bundle = getIntent().getExtras();

        textViewUnlucky = findViewById(R.id.textViewUnlucky);
        textViewLose = findViewById(R.id.textViewLose);
        textViewLossGuesses = findViewById(R.id.textViewLossGuesses);
        textViewLose.setText(bundle.getString("spil.getOrdet"));
        textViewLose.setText("Du har tabt \n" + bundle.getInt("getLossCounter") + " gang(e)!");
        textViewLossGuesses.setText("Du brugte \n" + bundle.getInt("spil.getBrugteBogstaverSize") + " gæt. \n" +
                "Det rigtige ord var: '" + bundle.getString("spil.getOrdet") + "'");

        playLossSound();
    }

    public void playLossSound() {
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.losingsound);
        mp.start();

    }
}
