/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu1964.framework.util;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author to
 */
public class FrameworkUtility {

    // Affecte la valeur d'un attribut à un objet
    public static void affectAttribute(Object objet, Field attribut, String value) throws Exception {
        String nomAttribut = attribut.getName().substring(0, 1).toUpperCase() + attribut.getName().substring(1);
        Method[] fonctions = objet.getClass().getDeclaredMethods();
        Method method = null; // methode recherché
        
        // Recherche du methode ayant le nom set + Attribut
        for(Method meth : fonctions) {
            if (meth.getName().equals("set" + nomAttribut)) {
                method = meth;
                break;
            }
        }
        
        // Appel du setter et cast value
        Object parametre = null;
        Class typeEntrer = method.getParameterTypes()[0];
        if (typeEntrer == int.class || typeEntrer == Integer.class) {
            parametre = Integer.valueOf(value);
        } 
        else if (typeEntrer == double.class || typeEntrer == Double.class) {
            parametre = Double.valueOf(value);
        } 
        else if (typeEntrer == Date.class || typeEntrer == Double.class) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            parametre = formatter.parse(value);
        }
        else {
            parametre = value;
        }
        
        method.invoke(objet, parametre);
    }
    
    // Prends tous les .class a l'interieur des sous package
    public static void getSubClasses(String parentPackage, File dossier, List classes) throws Exception {
        File[] inside = dossier.listFiles();
        String packageName = formatPackageName(parentPackage, dossier.getName());
        String className;
        for (File file : inside) {
            if (file.isDirectory()) {
                FrameworkUtility.getSubClasses(packageName, file, classes);
            } else {
                className = packageName + "." + file.getName();
                if (className.endsWith(".class")) {
                    classes.add(FrameworkUtility.getClass(className));
                }
            }
        }
    }
    
    // Prepare le packagename pour s'adapter si vide
    public static String formatPackageName(String packageName, String fileName) {
        if (packageName == "") {
            return fileName;
        }
        return packageName + "." + fileName;
    }

    // Pour avoir tous les .class dans un package
    public static List getClassesIn(String packageName) throws Exception {
        String path = packageName.replaceAll("[.]", "/");
        // si package name == "" recherche depuis source
        URL packageURL = Thread.currentThread().getContextClassLoader().getResource(path);
        File packageDirectory = new File(packageURL.toURI());
        File[] inside = packageDirectory.listFiles();
        List classes = new ArrayList<Class>();
        String className;
        for (File file : inside) {
            if (file.isDirectory()) {
                FrameworkUtility.getSubClasses(packageName, file, classes);
            } else {
                className = formatPackageName(packageName, file.getName());
                if (className.endsWith(".class")) {
                    classes.add(FrameworkUtility.getClass(className));
                }
            }
        }
        return classes;
    }

    public static Class getClass(String className) throws Exception {
        className = className.replaceAll(".class", "");
        Class result = Class.forName(className);
        return result;
    }
}
