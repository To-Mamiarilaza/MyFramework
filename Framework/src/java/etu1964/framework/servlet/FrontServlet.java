/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package etu1964.framework.servlet;

import etu1964.framework.Mapping;
import etu1964.framework.ModelView;
import etu1964.framework.annotations.Url;
import etu1964.framework.util.FrameworkUtility;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

/**
 *
 * @author to
 */
public class FrontServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
/// Attributs MappingUrls
    HashMap<String, Mapping> mappingUrls = new HashMap<>();

/// Encapsulations
    public HashMap getMappingUrls() {
        return this.mappingUrls;
    }

    public void addInMappingUrls(String url, Mapping mapping) {
        mappingUrls.put(url, mapping);
    }

/// Constructeur
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        try {
            String rootPackage = config.getInitParameter("rootPackage");
            loadMappingUrls(rootPackage);  // Remplisse le mappingUrls
            detailMappingUrls();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

/// Fonctions du classe
    // Detail des elements du Mapping Url
    public void detailMappingUrls() {
        Set<Map.Entry<String, Mapping>> entries = getMappingUrls().entrySet();
        for (Map.Entry<String, Mapping> entry : entries) {
            System.out.println("Url : " + entry.getKey() + " ClassName : " + entry.getValue().getClassName() + " Method : " + entry.getValue().getMethod());
        }
    }

    // Trouve les fonction et leur mapping dans un class
    public void scanClass(Class target) {
        Method[] methods = target.getDeclaredMethods();
        String className = target.getName();
        Url url;
        for (Method method : methods) {
            url = method.getAnnotation(Url.class);
            if (url != null) {
                addInMappingUrls(url.path(), new Mapping(className, method.getName()));
            }
        }
    }

    // Chargement du mappingUrls
    public void loadMappingUrls(String rootPackage) throws Exception {
        List<Class> classes = FrameworkUtility.getClassesIn(rootPackage);
        for (Class classe : classes) {
            scanClass(classe);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String functionUrl = getFunctionUrl(request.getRequestURI(), request.getContextPath());
            Object result = callFunction(functionUrl);
            if (result instanceof ModelView) {
                ModelView model = (ModelView) result;
                RequestDispatcher dispat = request.getRequestDispatcher("/View/" + model.getView());
                dispat.forward(request, response);
            }
            else {
                affichageFonction(response);
            }
        } catch (Exception e) {
            affichageErreur(response, e);
        }
    }
    
    // Prendre le nom du fonction a appeler
    protected String getFunctionUrl(String URI, String contextePath) {
        return URI.substring(contextePath.length(), URI.length());
    }
    
    // Texte a afficher si on appelle fonction sans chargement modelView
    protected void affichageFonction(HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) { 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FrontServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p> Fonction appelée</p>");
            out.println("</body>");
            out.println("</html>");
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Texte a afficher si on appelle fonction sans chargement modelView
    protected void affichageErreur(HttpServletResponse response, Exception ex) {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) { 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FrontServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>" + ex.getMessage() + "</p>");
            out.println("</body>");
            out.println("</html>");
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Pour avoir l'url entrant
    protected String getURL(HttpServletRequest request) {
        String url = request.getRequestURI();
        if (request.getQueryString() != null) {
            url = url + "?" + request.getQueryString();
        }
        return url;
    }
    
    // Appele la fonction concerné avec l'URL
    protected Object callFunction(String url) throws Exception {
        Mapping map = getMapping(url);
        Class classInstance = Class.forName(map.getClassName());
        Object objet = classInstance.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        Method function = classInstance.getDeclaredMethod(map.getMethod(), new Class[0]);
        Object result = function.invoke(objet, new Object[0]);
        return result;
    }
    
    public Mapping getMapping(String url) throws Exception {
        if(url == null) {
            url = "";
        }
        else {
            url = url.substring(1, url.length());
        }
        
        Mapping map = (Mapping) getMappingUrls().get(url);
        if (map == null) {
            throw new Exception("L'url " + url + " que vous avez entrer n'existe pas dans le projet.");
        }
        return map;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
