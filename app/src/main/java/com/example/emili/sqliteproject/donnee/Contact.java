package com.example.emili.sqliteproject.donnee;

/**
 * Created by emili on 31/07/2017.
 */

public class Contact {

    private int id;
    private String prenom;
    private String nom;
    private int age;
    private String genre;


    public Contact(){

    }
    public Contact(int id, String prenom, String nom, int age, String genre){

        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.age = age;
        this.genre = genre;
    }

    public int getId(){
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getPrenom(){
        return prenom;
    }
    public void setPrenom(String prenom){
        this.prenom = prenom;
    }

    public String getNom(){
        return nom;
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age = age;
    }

    public  String getGenre(){
        return genre;
    }
    public void setGenre(String genre){
        this.genre = genre;
    }
}
