package fr.nicolas.choquet.annuaire.entities;

public class Person {
    private int id;
    private String nom;
    private String prenom;
    private String telephone;

    public Person() {}

    public Person(int id, String nom, String prenom, String telephone) {
        setId(id);
        setNom(nom);
        setPrenom(prenom);
        setTelephone(telephone);
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
