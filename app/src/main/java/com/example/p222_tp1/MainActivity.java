package com.example.p222_tp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity:LOG";

    // Récupération de la liste
    private ListView listeV;

    // Liste des valeurs affchées dans la listeView
    private List<String> listeValeursDansLaListe;

    // Création d'un adapter à partir de la liste
    private AdapterListeImageTexte adapter;

    private void clic(){
        String uniqueId = UUID.randomUUID().toString();
        Toast.makeText(MainActivity.this, R.string.textToastClicButton, Toast.LENGTH_SHORT).show();
        // Affichage d'un message verbose dans le log
        Log.v(MainActivity.TAG, "Clic");
        this.listeValeursDansLaListe.add(uniqueId);
        this.adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Affichage Toast bonjour
        Toast.makeText(this, R.string.textToastBonjour, Toast.LENGTH_SHORT).show();

        this.listeV = (ListView) findViewById(R.id.idListView);

        this.listeValeursDansLaListe = new ArrayList<>();

        this.adapter = new AdapterListeImageTexte(this, this.listeValeursDansLaListe);

        // Liste des valeurs affchées dans la listeView
        //this.listeValeursDansLaListe = new ArrayList<>();

        // Création d'un adapter à partir de la liste
        //this.adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, listeValeursDansLaListe);

        // lie l'adapter à la listeView
        listeV.setAdapter(adapter);

        //Création du boutton
        Button addButton = (Button)findViewById(R.id.button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clic();
            }
        });

        // Code pour la gestion des clics sur ls items de la liste
        listeV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Fenetre.class);

                // Récupère la valeur de l'item à la position sur laquelle on a cliqué
                String valeurItem = (String) parent.getItemAtPosition(position);

                // Fixe un paramètre sous la forme clé-valeur
                intent.putExtra("letexte", valeurItem);

                //Toast.makeText(MainActivity.this, valeurItem, Toast.LENGTH_SHORT).show();

                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.infomenu:
                clic();
                break;
            case R.id.quitmenu:
                System.exit(0) ;
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

}