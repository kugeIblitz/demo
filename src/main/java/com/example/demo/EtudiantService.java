package com.example.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class EtudiantService {
    public boolean create(Etudiant e) {
        try {
            String query = "INSERT INTO etudiant (nom, prenom, sex, filiere) VALUES (?, ?, ?, ?)";
            Connection connection = Connexion.getCn();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, e.getNom().getValue());
            statement.setString(2, e.getPrenom().getValue());
            statement.setString(3, e.getSex().getValue());
            statement.setString(4, e.getFiliere().getValue());

            if (statement.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Erreur SQL: " + ex);
        }
        return false;
    }


    public List<Etudiant> findAll() {
        String query = "SELECT * FROM Etudiant";
        List<Etudiant> etudiants = new ArrayList<>();
        try {
            PreparedStatement statement = Connexion.getCn().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String sexe = resultSet.getString("sex");
                String filiere = resultSet.getString("filiere");
                etudiants.add(new Etudiant(id, nom, prenom, sexe, filiere));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return etudiants;
    }

}