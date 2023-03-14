/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package etu1964.framework.servlet;

import etu1964.framework.Mapping;
import etu1964.framework.annotations.Url;
import etu1964.framework.util.FrameworkUtility;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public void init() throws ServletException {
        super.init(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        try {
            loadMappingUrls();  // Remplisse le mappingUrls
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
    public void loadMappingUrls() throws Exception {
        System.out.println("Commencement Analyse");
        List<Class> classes = FrameworkUtility.getClassesIn("etu1964.model");
        for (Class classe : classes) {
            scanClass(classe); 
        }
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FrontServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>URL Entrer : " + getURL(request) + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
