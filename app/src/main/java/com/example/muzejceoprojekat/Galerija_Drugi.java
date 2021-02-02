package com.example.muzejceoprojekat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Galerija_Drugi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galerija_drugi);

        Eksponat eksponat = (Eksponat) getIntent().getSerializableExtra("Eksponat");

        TextView txtTitle = findViewById(R.id.txtObjectTitle);
        TextView txtCountry = findViewById(R.id.txtCountry);
        TextView txtAccessionYear = findViewById(R.id.txtAccessionYear);

        txtTitle.setText(eksponat.getTitle());
        txtCountry.setText("Country: " + eksponat.getCountry());
        txtAccessionYear.setText("Accession year: " + eksponat.getAccessionYear());

        ImageView imgEksponat = findViewById(R.id.imgEksponat);
        String url = eksponat.getImageURL();

        if (url.isEmpty()) {
            imgEksponat.setImageResource(R.drawable.ic_launcher_background);
        } else{
            Picasso.get().load(url).into(imgEksponat);
        }
    }
}