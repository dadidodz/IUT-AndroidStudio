package com.example.p222_tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity:LOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Affichage Toast bonjour
        Toast.makeText(this, R.string.textToastBonjour, Toast.LENGTH_SHORT).show();

        // Récupération de la liste
        ListView listeV = (ListView) findViewById(R.id.idListView);

        // Liste des valeurs affchées dans la listeView
        List<String> listeValeursDansLaListe = new ArrayList<>();

        // Création d'un adapter à partir de la liste
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listeValeursDansLaListe);

        // lie l'adapter à la listeView
        listeV.setAdapter(adapter);

        //Création du boutton
        Button addButton = (Button)findViewById(R.id.button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uniqueId = UUID.randomUUID().toString();
                Toast.makeText(MainActivity.this, R.string.textToastClicButton, Toast.LENGTH_SHORT).show();
                // Affichage d'un message verbose dans le log
                Log.v(MainActivity.TAG, "Clic");
                listeValeursDansLaListe.add(uniqueId);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override


}