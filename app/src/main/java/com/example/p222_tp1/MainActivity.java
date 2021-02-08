package com.example.p222_tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity:LOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, R.string.textToastBonjour, Toast.LENGTH_SHORT).show();

        Button addButton = (Button)findViewById(R.id.button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.textToastClicButton, Toast.LENGTH_SHORT).show();

                // Affichage d'un message verbose dans le log
                Log.v(MainActivity.TAG, "Clic");
            }
        });



    }

}