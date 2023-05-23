/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu1964.model;

import etu1964.framework.ModelView;
import etu1964.framework.annotations.Url;
import etu1964.framework.util.FileUpload;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author to
 */
public class Emp {
/// Attributs

    String nom;
    int age;
    Date naissance;
    FileUpload photo;
    
/// Encapsulation
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getNaissance() {
        return naissance;
    }

    public void setNaissance(Date naissance) {
        this.naissance = naissance;
    }

    public FileUpload getPhoto() {
        return photo;
    }

    public void setPhoto(FileUpload photo) {
        this.photo = photo;
    }

/// Constructeur
    public Emp(String nom) {
        setNom(nom);
    }

    public Emp() {
    }

/// Fonctions du classe
    @Url(path = "getAllEmployer.do")
    public ModelView getAll() {
        // Les données à envoyer
        List<Emp> listes = new ArrayList<Emp>();
        listes.add(new Emp("To"));
        listes.add(new Emp("Niavo"));
        listes.add(new Emp("Tsanta"));
        listes.add(new Emp("Naina"));

        String dateDebut = "2023 - 04 - 07";

        ModelView view = new ModelView("ListeEmployer.jsp");
        view.addItem("listes", listes);
        view.addItem("dateDebut", dateDebut);

        return view;
    }

    @Url(path = "insertNewEmp.do")
    public void save(String name, Integer year, Date birth) {
        System.out.println("J'insert un nouveau employee ");
        System.out.println("Son nom est : " + this.nom);
        System.out.println("Son age est : " + this.age);
        System.out.println("Née en : " + this.naissance);
        
        System.out.println("Photo : " + getPhoto() + " et bytes : " + getPhoto().getBytes());
    }
}
