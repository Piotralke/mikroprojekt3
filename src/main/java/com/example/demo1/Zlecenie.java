package com.example.demo1;

import java.util.Date;

public class Zlecenie {
    private int idZlecenia;
    private int idPracownika;
    private String opis;
    private String tytul;
    private String tresc;
    private Date data;

    public Zlecenie(){}

    public Zlecenie(int idZlecenia, int idPracownika, String opis, String tytul, String tresc, Date data) {
        this.idZlecenia = idZlecenia;
        this.idPracownika = idPracownika;
        this.opis = opis;
        this.tytul = tytul;
        this.tresc = tresc;
        this.data = data;
    }

    public int getIdZlecenia() {
        return idZlecenia;
    }

    public void setIdZlecenia(int idZlecenia) {
        this.idZlecenia = idZlecenia;
    }

    public int getIdPracownika() {
        return idPracownika;
    }

    public void setIdPracownika(int idPracownika) {
        this.idPracownika = idPracownika;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
