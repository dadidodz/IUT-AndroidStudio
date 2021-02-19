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
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity:LOG";

    // Récupération de la liste
    private ListView listeV;

    // Liste des valeurs affchées dans la listeView
    private List<String> listeValeursDansLaListe;

    // Création d'un adapter à partir de la liste
    private AdapterListeImageTexte adapter;

    private TextView valeur;

    static final String BASE_URL = "https://pokeapi.co/api/v2/";

    Retrofit retrofit;
    PokemonAPIService monService;

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

        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.monService = retrofit.create(PokemonAPIService.class);

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
                Call<JsonElement> call = monService.listPokemons();
                call.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                        if (response.isSuccessful()){
                            JsonElement result =response.body();

                            JsonObject jsonGlobal = result.getAsJsonObject();

                            int nbResultats = jsonGlobal.get("count").getAsInt();
                            Log.v(TAG, "" + nbResultats);

                            JsonArray listePokemons = jsonGlobal.getAsJsonArray("results");
                            StringBuffer chaine = new StringBuffer();
                            for (int i=0; i < listePokemons.size(); i++){
                                JsonElement pokemon = listePokemons.get(i);
                                chaine.append(pokemon.toString()+"\n");
                            }

                            valeur.setText(chaine);
                        } else {
                            Log.e(TAG, response.errorBody().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonElement> call, Throwable t) {
                        Log.e(TAG, t.getMessage());
                    }
                });

                //clic();
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

interface PokemonAPIService{
    // 1er appel possible
    @GET("pokemon?limit=20&offset=200")
    Call<JsonElement> listPokemons();

    // 2ème appel possible avec paramètre : IDPOKEMON = id
    @GET("pokemon/{IDPOKEMON}")
    Call<JsonElement> getPokemon(@Path("IDPOKEMON") String id);
}