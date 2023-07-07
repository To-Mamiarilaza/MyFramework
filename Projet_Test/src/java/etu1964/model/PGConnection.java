/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu1964.model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author to
 */
public class PGConnection {
    public static Connection getConnection() throws Exception {     
        // Fonction qui renvoie la connection vers la base : 
            String database = "grocery";       // Nom de la base
            String user = "postgres";       // User dans postgres
            String mdp = "postgres";       // Mot de passe
            
            // Charge la classe de driver
            Class.forName("org.postgresql.Driver");
            
            // Creation de l'objet de connection
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + database, user,  mdp);
            
            connection.setAutoCommit(false);
            return connection;
    }
    
    public static void main(String[] args) throws Exception {
        System.out.println(PGConnection.getConnection());
    }
}
