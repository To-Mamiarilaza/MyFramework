/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu1964.framework.util;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author to
 */
public class FrameworkUtility {
    
    /// Pour avoir tous les .class dans un package
    public static List getClassesIn(String packageName) throws Exception {
        String path = packageName.replaceAll("[.]", "/");
        URL packageURL = Thread.currentThread().getContextClassLoader().getResource(path);
        File packageDirectory = new File(packageURL.toURI());
        File[] inside = packageDirectory.listFiles();
        List classes = new ArrayList<Class>();
        String className;
        for (File file : inside) {
            className = packageName + "." + file.getName();
            classes.add(FrameworkUtility.getClass(className));
        }
        return classes;
    }
    
    public static Class getClass(String className) throws Exception {
        className = className.replaceAll(".class", "");
        Class result = Class.forName(className);
        return result;
    }
}
