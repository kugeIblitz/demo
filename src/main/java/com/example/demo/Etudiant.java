package com.example.demo;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Etudiant {
    private int id;
    private SimpleStringProperty nom;
    private SimpleStringProperty prenom;
    private SimpleStringProperty sex;

    private ObservableList<Etudiant> etudiantList;

    public Etudiant() {
        etudiantList = FXCollections.observableArrayList();
    }

    public void setEtudiantList(ObservableList<Etudiant> etudiantList) {
        this.etudiantList = etudiantList;
    }

    public ObservableList<Etudiant> getList() {
        return etudiantList;
    }



    private SimpleStringProperty filiere;





    public Etudiant(String nom, String prenom, String sex, String filiere) {
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.sex = new SimpleStringProperty(sex);
        this.filiere = new SimpleStringProperty(filiere);
    }


    public Etudiant(int id, String nom, String prenom, String sex, String filiere) {
        this.id = id;
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.sex = new SimpleStringProperty(sex);
        this.filiere = new SimpleStringProperty(filiere);
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public SimpleStringProperty getNom() {
        return nom;
    }
    public void setNom(SimpleStringProperty nom) {
        this.nom = nom;
    }
    public SimpleStringProperty getPrenom() {
        return prenom;
    }
    public void setPrenom(SimpleStringProperty prenom) {
        this.prenom = prenom;
    }
    public SimpleStringProperty getSex() {
        return sex;
    }
    public void setSexe(SimpleStringProperty sex) {
        this.sex = sex;
    }
    public SimpleStringProperty getFiliere() {
        return filiere;
    }
    public void setFiliere(SimpleStringProperty filiere) {
        this.filiere = filiere;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", sex='" + sex + '\'' +
                ", filiere='" + filiere + '\'' +
                '}';
    }
}