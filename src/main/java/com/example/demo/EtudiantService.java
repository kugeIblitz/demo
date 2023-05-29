package com.example.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;





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
    public int maxId() {
        try {
            String query = "SELECT MAX(id) AS max_id FROM etudiant";
            Connection connection = Connexion.getCn();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int maxId = resultSet.getInt("max_id");
                return maxId;
            }
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex.getMessage());
        }

        return 0; // Return a default value if there was an error or no result
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

    public boolean delete(Etudiant etudiant) {
        String query = "DELETE FROM Etudiant WHERE id = ?";
        try {
            PreparedStatement statement = Connexion.getCn().prepareStatement(query);
            statement.setInt(1, etudiant.getId());
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Etudiant findById(int id) {
        String query = "SELECT * FROM Etudiant WHERE id = ?";
        try (PreparedStatement statement = Connexion.getCn().prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int etudiantId = resultSet.getInt("id");
                    String nom = resultSet.getString("nom");
                    String prenom = resultSet.getString("prenom");
                    String sex = resultSet.getString("sex");
                    String filiere = resultSet.getString("filiere");
                    return new Etudiant(etudiantId, nom, prenom, sex, filiere);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public boolean update(Etudiant etudiant) {
        String query = "UPDATE Etudiant SET nom = ?, prenom = ?, sexe = ?, filiere = ? WHERE id = ?";
        try {
            PreparedStatement statement = Connexion.getCn().prepareStatement(query);
            statement.setString(1, etudiant.getNom().getValue());
            statement.setString(2, etudiant.getPrenom().getValue());
            statement.setString(3, etudiant.getSex().getValue());
            statement.setString(4, etudiant.getFiliere().getValue());
            statement.setInt(5, etudiant.getId());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}