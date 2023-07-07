/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu1964.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author to
 */
public class ModelView {
/// Attributs
    String view;
    HashMap<String, Object> data = new HashMap<String, Object>();    
    HashMap<String, Object> session = new HashMap<String, Object>();
    boolean isJSON;
    List<String> deletedSession = new ArrayList<>();
    boolean invalidateSession = false;
    
/// Constructeur
    public ModelView(String view) {
        this.view = view;
    }

/// Encapsulation
    public String getView() {
        return view;
    }

    public void isJSON(boolean value) {
        this.isJSON = value;
    }
    
    public boolean isJSON() {
        return this.isJSON;
    }
    
    public void setView(String view) {
        this.view = view;
    }
    
    public HashMap<String, Object> getData() {
        return this.data;
    }

    public HashMap<String, Object> getSession() {
        return this.session;
    }

    public List<String> getDeletedSession() {
        return deletedSession;
    }

    public void setDeletedSession(List<String> deletedSession) {
        this.deletedSession = deletedSession;
    }

    public boolean isInvalidateSession() {
        return invalidateSession;
    }

    public void setInvalidateSession(boolean invalidateSession) {
        this.invalidateSession = invalidateSession;
    }
    
/// Fonctions du classe
    public void addItem(String cle, Object valeur) {
        getData().put(cle, valeur);
    }
    
    public void addSession(String cle, Object valeur) {
        getSession().put(cle, valeur);
    }
    
    public void deleteSession(String cle) {
        getDeletedSession().add(cle);
    }
    
    
}
