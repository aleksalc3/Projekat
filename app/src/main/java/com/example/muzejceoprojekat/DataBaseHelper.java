package com.example.muzejceoprojekat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.sql.Date;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "rezervacijeDB.db";
    private static final int DB_VERSION = 2;
    //public static final String TABLE_NAME = "rezervacije_table";
    public static final String Col_1 = "ID";
    public static final String Col_2 = "IME";
    public static final String Col_3 = "PREZIME";
    public static final String Col_4 = "DATUM";
    public static final String Col_5 = "BROJTELEFONA";

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE rezervacije_table ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ime TEXT," +
                "prezime TEXT," +
                "datum TEXT," +
                "brojTelefona TEXT)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS rezervacije_table");
        this.onCreate(db);
    }

    public boolean dodajRezervaciju(Rezervacija rezervacija) {
        SQLiteDatabase db = this.getWritableDatabase();
        System.out.println("rrrrrrrrrrrreeeeeeeeeeeeeeezzzzzzzz");
        System.out.println(rezervacija);
        ContentValues values = new ContentValues();
        values.put("ime", rezervacija.getIme());
        values.put("prezime", rezervacija.getPrezime());
        values.put("datum", rezervacija.getDatum());
        values.put("brojTelefona", rezervacija.getBrojTelefona());

        long result = db.insert("rezervacije_table", null, values);
        db.close();


        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Rezervacija dajRezervaciju(String brojTelefona) {

        String query = "SELECT  * FROM rezervacije_table WHERE brojTelefona =" + brojTelefona;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Rezervacija rezervacija = null;

        if (cursor != null)
            cursor.moveToFirst();

        rezervacija = new Rezervacija();
        rezervacija.setIme(cursor.getString(0));
        rezervacija.setPrezime(cursor.getString(1));
        rezervacija.setDatum(cursor.getString(2));
        rezervacija.setBrojTelefona(cursor.getString(3));


        return rezervacija;
    }
}