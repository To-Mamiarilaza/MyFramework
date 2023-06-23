/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu1964.framework.util;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.tomcat.util.http.fileupload.UploadContext;

/**
 *
 * @author to
 */
public class FrameworkUtility {

    // Recherche du setter 
    public static Method findSetter(Object objet, Field attribut) throws Exception {
        String nomAttribut = attribut.getName().substring(0, 1).toUpperCase() + attribut.getName().substring(1);
        Method[] fonctions = objet.getClass().getDeclaredMethods();
        Method method = null; // methode recherché

        // Recherche du methode ayant le nom set + Attribut
        for (Method meth : fonctions) {
            if (meth.getName().equals("set" + nomAttribut)) {
                method = meth;
                break;
            }
        }
        return method;
    }
    
    // Reset tous les valeurs d'attributs d'un class
    public static void resetObjectAttribute(Object objet) throws Exception {
        Field[] attributs = objet.getClass().getDeclaredFields();
        for (Field attribut : attributs) {
            FrameworkUtility.affectAttribute(objet, attribut, null);
        }
    }
    
    // Affecte un Upload File  à un objet
    public static void affectFileUploadAttribute(Object objet, Field attribut, FileUpload value) throws Exception {
        Method method = findSetter(objet, attribut);
        method.invoke(objet, value);
    }
    
    // Affecte le Session aux hash map session de l'objet
    public static void affectSessionAttribute(Object objet, Field attribut, HashMap<String, Object> value) throws Exception {
        Method method = findSetter(objet, attribut);
        method.invoke(objet, value);
    }

    // Affecte la valeur d'un attribut à un objet
    public static void affectAttribute(Object objet, Field attribut, String value) throws Exception {
        Method method = findSetter(objet, attribut);

        // Appel du setter et cast value
        Object parametre = null;
        Class typeEntrer = method.getParameterTypes()[0];
        parametre = FrameworkUtility.castValue(typeEntrer, value);

        method.invoke(objet, parametre);
    }

    // Affecte la valeur d'un attribut tableau à un objet
    public static void affectArrayAttribute(Object objet, Field attribut, String[] value) throws Exception {
        Method method = findSetter(objet, attribut);

        // Appel du setter et cast value
        Object parametre = null;
        Class typeEntrer = method.getParameterTypes()[0];
        parametre = FrameworkUtility.castArrayValue(typeEntrer, value);

        method.invoke(objet, parametre);
    }

    public static Object castArrayValue(Class typeEntrer, String[] values) throws Exception {
        System.out.println("Caste du tableau");
        if (typeEntrer == Integer[].class) {
            Integer[] results = new Integer[values.length];
            int indice = 0;
            for (String value : values) {
                results[indice] = Integer.valueOf(value);
                indice++;
            }
            return results;

        } else if (typeEntrer == int[].class) {
            int[] results = new int[values.length];
            int indice = 0;
            for (String value : values) {
                results[indice] = Integer.valueOf(value);
                indice++;
            }
            return results;

        } else if (typeEntrer == Double[].class) {
            Double[] results = new Double[values.length];
            int indice = 0;
            for (String value : values) {
                results[indice] = Double.valueOf(value);
                indice++;
            }
            return results;

        } else if (typeEntrer == double[].class) {
            double[] results = new double[values.length];
            int indice = 0;
            for (String value : values) {
                results[indice] = Double.valueOf(value);
                indice++;
            }
            return results;

        } else if (typeEntrer == Date[].class) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date[] results = new Date[values.length];
            int indice = 0;
            for (String value : values) {
                results[indice] = formatter.parse(value);
                indice++;
            }
            return results;

        } else {
            return values;
        }
    }

    // Caster des valeurs , si la valeur est null on retourne la valeur par défaut
    public static Object castValue(Class typeEntrer, String value) throws Exception {
        if (typeEntrer == Integer.class) {
            if (value == null) {
                return null;
            }
            return Integer.valueOf(value);
        } else if (typeEntrer == int.class) {
            if (value == null) {
                return 0;
            }
            return Integer.valueOf(value);
        } else if (typeEntrer == Double.class) {
            if (value == null) {
                return null;
            }
            return Double.valueOf(value);
        } else if (typeEntrer == double.class) {
            if (value == null) {
                return 0;
            }
            return Double.valueOf(value);
        } else if (typeEntrer == Date.class) {
            if (value == null) {
                return null;
            }
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.parse(value);
        } else {
            return value;
        }
    }

    // Trouve un method à partir de son nom
    public static Method findMethod(String nomFonction, Object objet) {
        Method[] methods = objet.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(nomFonction)) {
                return method;
            }
        }
        return null;
    }

    public static Object[] prepareFunctionParameter(Method function, HashMap<String, String> functionParameters) throws Exception {
        Parameter[] params = function.getParameters();
        Object[] arguments = new Object[params.length];     // Tableau contenant l'arguments
        Set<Map.Entry<String, String>> elements = functionParameters.entrySet();

        int indice = 0;
        for (Parameter param : params) {
            boolean exist = false;
            for (Map.Entry<String, String> element : elements) {
                if (element.getKey().equals(param.getName())) {
                    arguments[indice] = FrameworkUtility.castValue(param.getType(), element.getValue());
                    exist = true;
                    indice++;
                }
            }

            if (exist == false) {
                arguments[indice] = null;
                indice++;
            }
        }
        return arguments;
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
