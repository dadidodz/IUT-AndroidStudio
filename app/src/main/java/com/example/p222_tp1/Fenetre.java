package com.example.p222_tp1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Fenetre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fenetre_layout);

        // Récupère le paramètre de l'intent via la clé utilisée
        String texte = getIntent().getStringExtra("letexte");

        // modifie le texte du label de l'activité avec cette valeur
        TextView textV = (TextView)findViewById(R.id.textView);
        textV.setText(texte);

        // Mise en place du traitement sur le bouton ...
        Button boutonRetour = (Button)findViewById(R.id.buttonrtn);

        boutonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
