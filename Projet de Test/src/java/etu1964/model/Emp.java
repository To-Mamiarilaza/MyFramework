/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu1964.model;

import etu1964.framework.ModelView;
import etu1964.framework.annotations.Url;
import java.util.List;

/**
 *
 * @author to
 */
public class Emp {
/// Attributs
    String nom;

/// Encapsulation
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) throws Exception {
        if (nom.equals("") || nom == null) {
            throw new Exception("Le nom ne doit pas etre null");
        }
        this.nom = nom;
    }

/// Constructeur
    public Emp(String nom) throws Exception {
        setNom(nom);
    }

    public Emp() {
    }
    
/// Fonctions du classe
    
    @Url(path="getAllEmployer")
    public List getAll() {
        System.out.println("Listes de tous les employées");
        return null;
    }

    @Url(path="insertNewEmp")
    public ModelView insertionEmp() {
        System.out.println("Insertion nouveau Emp");
        return new ModelView("insertionEmploye.jsp");
    }
}
