package com.example.brint_aflevering2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class RulesActivity extends AppCompatActivity {

    private TextView textViewRules, textViewRules2;
    private ImageView illustration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        textViewRules = findViewById(R.id.textViewRules);
        textViewRules2 = findViewById(R.id.textViewRules2);

        textViewRules.setText("1. - Der loades automatisk et (dansk) ord der skal gættes. Ønsker spilleren istedet at gætte et ord gennem DR-metoden, trykkes på knappen 'Hent DR-ord' \n \n " +
                "2. - I gætte-feltet kan der kan udelukkende indtastes bogstaver der indgår i det danske alfabet. \n \n " +
                "3. - Der kan kun indtastes ét bogstav per gæt. Bogstavet tilføjes til 'brugte-bogstaver' listen, og kan ikke gættes igen. \n \n hej ");
        textViewRules2.setText("4. - 'Genstart' giver et nyt ord, 'Få svar' returnerer det rigtige ord. \n \n" +
                "@author s185014 - Harald");
    }
}
