package com.example.muzejceoprojekat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private Button button1;
    private Button btnReservations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.btnGallery);
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Galerija();
            }
        });

        btnReservations = findViewById(R.id.btnReservations);
        btnReservations.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) { Reservations();
            }
        });

    }

    public void Galerija() {
        Intent i = new Intent(this, Galerija.class);
        startActivity(i);
    }

    public void Reservations() {
        Intent i = new Intent(this, Reservations.class);
        startActivity(i);
    }


}

