/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu1964.model;

import etu1964.framework.ModelView;
import etu1964.framework.annotations.Url;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author to
 */
public class Legume {
/// Attribut du classe legume

    int idLegume;
    String nom;
    double prix;

/// Encapsulation et test unitaire
    public int getIdLegume() {
        return idLegume;
    }

    public void setIdLegume(int idLegume) {
        this.idLegume = idLegume;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

/// Constructeur du classe
    public Legume() {
    }

    public Legume(int idLegume, String nom, double prix) {
        this.idLegume = idLegume;
        this.nom = nom;
        this.prix = prix;
    }

    public Legume(String nom, double prix) {
        this.nom = nom;
        this.prix = prix;
    }

/// Fonction du classe
    // Ajouter nouveau legume
    @Url("new-legume.do")
    public ModelView newLegume() {
        ModelView view = new ModelView("/View/insertion.jsp");
        return view;
    }
    
    @Url("modify-legume.do")
    public ModelView modifierLegume(String idLegume) throws Exception {
        ModelView view = new ModelView("/View/modification.jsp");
        Legume legume = Legume.findById(Integer.valueOf(idLegume));
        view.addItem("legume", legume);
        return view;
    }
    
    @Url("modifier.do")
    public ModelView modification() throws Exception {
        this.update();
        ModelView view = new ModelView("/View/index.jsp");
        List<Legume> legumes = Legume.findAll();
        view.addItem("legumes", legumes);
        return view;
    }

    @Url("insert-legume.do")
    public ModelView insertionLegume() throws Exception {
        this.save();
        ModelView view = new ModelView("/View/index.jsp");
        List<Legume> legumes = Legume.findAll();
        view.addItem("legumes", legumes);
        return view;
    }

    @Url("delete-legume.do")
    public ModelView supprimerLegume(String idLegume) throws Exception {
        Legume legume = new Legume();
        legume.setIdLegume(Integer.valueOf(idLegume));
        legume.delete();
        
        ModelView view = new ModelView("/View/index.jsp");
        List<Legume> legumes = Legume.findAll();
        view.addItem("legumes", legumes);
        return view;
    }

    // Page liste legumes
    @Url("liste.do")
    public ModelView listeLegume() throws Exception {
        ModelView view = new ModelView("/View/index.jsp");
        List<Legume> legumes = Legume.findAll();
        view.addItem("legumes", legumes);
        return view;
    }

    // Page detail legumes
    @Url("detail.do")
    public ModelView detailLegume(String idLegume) throws Exception {
        ModelView view = new ModelView("/View/detail.jsp");
        Legume legume = Legume.findById(Integer.valueOf(idLegume));
        view.addItem("legume", legume);
        return view;
    }

    // Supprimer un legume
    public void delete() throws Exception {
        String query = String.format("DELETE FROM legume WHERE idlegume = %d", getIdLegume());
        try (Connection connection = PGConnection.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            connection.commit();
        }
    }

    // Enregistrer un legume dans la base de données
    public void save() throws Exception {
        String query = String.format("INSERT INTO legume VALUES (DEFAULT, '%s', %s)", getNom(), getPrix());
        try (Connection connection = PGConnection.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            connection.commit();
        }
    }

    // Mettre à jour le legume dans la base
    public void update() throws Exception {
        String query = String.format("UPDATE legume SET nom = '%s', prix = %s WHERE idLegume = %d", getNom(), getPrix(), getIdLegume());
        try (Connection connection = PGConnection.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            connection.commit();
        }
    }

    // Supprimer un legume
    public static void deleteLegume(String idLegume) throws Exception {
        Legume legume = new Legume();
        legume.setIdLegume(4);
        legume.delete();
    }

    // Mettre à jour un legume
    public static void updateLegume(String idLegume, String nom, String prix) throws Exception {
        Legume legume = new Legume(Integer.valueOf(idLegume), nom, Double.valueOf(prix));
        legume.update();
    }

    // Insérer nouveau legume
    public static void insertLegume(String nom, String prix) throws Exception {
        Legume legume = new Legume(nom, Double.valueOf(prix));
        legume.save();
    }

    // Pour avoir la liste des légumes
    public static List<Legume> findAll() throws Exception {
        List<Legume> legumes = new ArrayList<>();
        String query = "SELECT * FROM legume";
        try (Connection connection = PGConnection.getConnection(); Statement statement = connection.createStatement(); ResultSet resultset = statement.executeQuery(query)) {

            while (resultset.next()) {
                int idLegume = resultset.getInt("idlegume");
                String nom = resultset.getString("nom");
                double prix = resultset.getDouble("prix");

                legumes.add(new Legume(idLegume, nom, prix));
            }
        }

        return legumes;
    }

    // Avoir un legume par son ID
    public static Legume findById(int idLegume) throws Exception {
        String query = String.format("SELECT * FROM legume WHERE idlegume = %d", idLegume);
        try (Connection connection = PGConnection.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                String nom = resultSet.getString("nom");
                Double prix = resultSet.getDouble("prix");
                return new Legume(idLegume, nom, prix);
            }
        }
        return null;
    }

}
