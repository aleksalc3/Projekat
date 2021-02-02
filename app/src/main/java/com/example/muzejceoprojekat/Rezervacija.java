package com.example.muzejceoprojekat;

public class Rezervacija {
    private String ime;
    private String prezime;
    private String datum;
    private String brojTelefona;

    public Rezervacija() {
    }

    public Rezervacija(String ime, String prezime, String datum, String brojTelefona) {
        this.ime = ime;
        this.prezime = prezime;
        this.datum = datum;
        this.brojTelefona = brojTelefona;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }
}
