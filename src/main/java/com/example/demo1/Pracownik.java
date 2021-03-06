package com.example.demo1;

public class Pracownik {
    private int id;
    private String imie;
    private String nazwisko;
    private String login;
    private String haslo;
    private boolean isAdmin;

    public Pracownik(){}

    public Pracownik(int id,String imie, String nazwisko, String login, String haslo, boolean isAdmin) {
        this.id=id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.login = login;
        this.haslo = haslo;
        this.isAdmin = isAdmin;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}

