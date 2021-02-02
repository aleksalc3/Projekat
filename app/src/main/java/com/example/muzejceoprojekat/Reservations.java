package com.example.muzejceoprojekat;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Reservations extends AppCompatActivity {

    DataBaseHelper baza;

    EditText editTextIme;
    EditText editTextPrezime;
    EditText editTextDatum;
    EditText editTextBrojTelefona;

    Button btnRezervisi;

    TextView lblIme;
    TextView lblPrezime;
    TextView lblDatum;
    TextView lblBrojTelefona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);

        baza = new DataBaseHelper(this);

        editTextIme = (EditText) findViewById(R.id.txtIme);
        editTextPrezime = (EditText) findViewById(R.id.txtPrezime);
        editTextDatum = (EditText) findViewById(R.id.txtDatum);
        editTextBrojTelefona = (EditText) findViewById(R.id.txtBrojTelefona);

        btnRezervisi = (Button) findViewById(R.id.btnRezervisi);

        lblIme = findViewById(R.id.lblIme);
        lblPrezime = findViewById(R.id.lblPrezime);
        lblDatum = findViewById(R.id.lblDatum);
        lblBrojTelefona = findViewById(R.id.lblBrojTelefona);

        btnRezervisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rezervisi();
                /*editTextIme.setText("");
                editTextPrezime.setText("");
                editTextDatum.setText("");
                editTextBrojTelefona.setText("");*/
            }
        });
    }

    private void rezervisi() {
        String ime = String.valueOf(editTextIme.getText());
        String prezime = String.valueOf(editTextPrezime.getText());
        String datum = String.valueOf(editTextDatum.getText());
        String brojTelefona = String.valueOf(editTextBrojTelefona.getText());

        Rezervacija r = new Rezervacija(ime, prezime, datum ,brojTelefona);
        System.out.println(r);
        boolean signal = baza.dodajRezervaciju(r);

        if(signal) {
            Toast.makeText(Reservations.this, "Uspesna rezervacija", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(Reservations.this, "Neuspesna rezervacija", Toast.LENGTH_LONG).show();
        }

    }

}