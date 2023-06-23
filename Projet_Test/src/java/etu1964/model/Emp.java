/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu1964.model;

import etu1964.framework.ModelView;
import etu1964.framework.annotations.Auth;
import etu1964.framework.annotations.Singleton;
import etu1964.framework.annotations.Url;
import etu1964.framework.util.FileUpload;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author to
 */

@Singleton
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
    @Url("getAllEmployer.do")
    @Auth
    public ModelView getAll() {
        System.out.println("Reference de l'objet : " + this);

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

    @Url("insertNewEmp.do")
    public ModelView save(String name, Integer year, Date birth) {
        System.out.println("Reference de l'objet : " + this);
        System.out.println("J'insert un nouveau employee ");
        System.out.println("Son nom est : " + this.nom);
        System.out.println("Son age est : " + this.age);
        System.out.println("Née en : " + this.naissance);
        if (getPhoto() != null) {
            System.out.println(getPhoto().getName());
        }
        
        return new ModelView("");
    }
    
    @Url("deleteEmp.do")
    @Auth("admin")
    public ModelView delete() {
        System.out.println("Employé supprimer !");
        return new ModelView("station.jsp");
    }
    
    @Url("checkLogin.do")
    public ModelView checkLogin(String user, String mdp) {
        if (user.equals("Admin") && mdp.equals("1234")) {
            ModelView view = new ModelView("bienvenue.jsp");
            view.addSession("isConnected", true);
            view.addSession("profile", "admin");
            view.addItem("profile", "Admin");
            return view;
        }
        else if (user.equals("Client") && mdp.equals("1234")) {
            ModelView view = new ModelView("bienvenue.jsp");
            view.addSession("isConnected", true);
            view.addItem("profile", "Client");
            return view;
        } else {
            return new ModelView("home.jsp");
        }
    }
    
    @Url("testAPI.do")
    public ModelView testAPI() {
        ModelView view = new ModelView("station.jsp");
        List<String> listes = new ArrayList<>();
        listes.add("To");
        listes.add("Niavo");
        listes.add("Mamy");
        listes.add("Vero");;
        view.addItem("listes", listes);
        view.addItem("fleuve", "Andekaleka");
        view.isJSON(true);
        return view;
    }
    
    @JSON
    
    @Url("home.do")
    public ModelView home() {
        return new ModelView("home.jsp");
    }
}
