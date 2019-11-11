package com.example.brint_aflevering2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class WinActivity extends AppCompatActivity {

    private TextView textViewWin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
        textViewWin = findViewById(R.id.textViewWin);
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        textViewWin.setText("Du har vundet! Du har vundet)");
    }
}
