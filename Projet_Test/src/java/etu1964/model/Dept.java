/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu1964.model;

import etu1964.framework.ModelView;
import etu1964.framework.annotations.Singleton;
import etu1964.framework.annotations.Url;
import java.util.List;

/**
 *
 * @author to
 */
public class Dept {
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
    public Dept(String nom) throws Exception {
        setNom(nom);
    }
    
    public Dept() {
        
    }

/// Fonctions du classe
    @Url("getAllDepartement.do")
    public List getAllDept() {
        System.out.println("Listes de tous les département");
        return null;
    }
    
    @Url("welcome.do")
    public ModelView departement() {
        return new ModelView("departement.jsp");
    }

    @Url("insertNewDept.do")
    public void insertionDept() {
    }
    
    public void refresh() {
    }
}
