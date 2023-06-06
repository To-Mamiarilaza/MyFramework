/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package etu1964.framework.servlet;

import etu1964.framework.Mapping;
import etu1964.framework.ModelView;
import etu1964.framework.annotations.Url;
import etu1964.framework.util.FileUpload;
import etu1964.framework.util.FrameworkUtility;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import etu1964.framework.annotations.Singleton;

/**
 *
 * @author to
 */
@MultipartConfig
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
    HashMap<String, Mapping> mappingUrls = new HashMap<>();     // Contenant les mappings
    HashMap<String, Object> instanceList = new HashMap<>();     // Contenant les singletons

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
        // Si singleton ajouter dans le hashmap avec valeur null
        if (target.getAnnotation(Singleton.class) != null) {
            this.instanceList.put(className, null);
        }
        Url url;
        for (Method method : methods) {
            url = method.getAnnotation(Url.class);
            if (url != null) {
                addInMappingUrls(url.value(), new Mapping(className, method.getName()));
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

    // Depaquetage des données à envoyer vers le view
    protected void prepareRequest(HttpServletRequest request, ModelView view) {
        HashMap<String, Object> data = view.getData();
        Set<Map.Entry<String, Object>> elements = data.entrySet();
        for (Map.Entry<String, Object> element : elements) {
            request.setAttribute(element.getKey(), element.getValue());
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String functionUrl = getFunctionUrl(request.getRequestURI(), request.getContextPath());
            Object result = callFunction(functionUrl, request);
            if (result != null && result instanceof ModelView) {
                ModelView model = (ModelView) result;
                prepareRequest(request, model);

                RequestDispatcher dispat = request.getRequestDispatcher("/View/" + model.getView());
                dispat.forward(request, response);
            } else {
                affichageFonction(response);
            }
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite !");
            affichageErreur(response, e);
        }
    }

    // Prendre les arguments passé par l'url
    protected HashMap<String, String> getAllURLParameter(HttpServletRequest request) {
        HashMap<String, String> parameters = new HashMap<>();
        String queryString = request.getQueryString();
        if (queryString != null) {
            String[] division = queryString.split("&");
            for (String string : division) {
                parameters.put(string.split("=")[0], string.split("=")[1]);
            }
            Set<Map.Entry<String, String>> elements = parameters.entrySet();
            for (Map.Entry<String, String> element : elements) {
                System.out.println("Name : " + element.getKey() + " --> " + element.getValue());
            }
        }
        return parameters;
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
        } catch (Exception e) {
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
            out.println("<p> Erreur : " + ex.getMessage() + "</p>");
            ex.printStackTrace();
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
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

    // Pour remplir les attributes de l'objet à partir des parametres de l'URL
    protected void loadURLAttribute(Object objet, HttpServletRequest request) throws Exception {
        HashMap<String, String> parameters = getAllURLParameter(request);
        Field[] attributs = objet.getClass().getDeclaredFields();
        Set<Map.Entry<String, String>> elements = parameters.entrySet();

        for (Map.Entry<String, String> element : elements) {
            for (Field attr : attributs) {
                if (element.getKey().equals(attr.getName())) {
                    FrameworkUtility.affectAttribute(objet, attr, element.getValue());
                }
            }
        }

    }

    // Trouver l'attributs concernée
    public Field findFileUploadField(Field[] attributs, String parameter) {
        for (Field attribut : attributs) {
            if (attribut.getType() == FileUpload.class && parameter.equals(attribut.getName())) {
                return attribut;
            }
        }
        return null;
    }

    // Charge le fichier uploader dans le class FileUpload
    protected void loadUploadedFile(Object objet, Part partFile) throws Exception {
        Field[] attributs = objet.getClass().getDeclaredFields();
        Field uploadField = findFileUploadField(attributs, partFile.getName());
        if (uploadField != null) {
            byte[] bytes = partFile.getInputStream().readAllBytes();
            FileUpload result = new FileUpload(partFile.getSubmittedFileName(), bytes);
            FrameworkUtility.affectFileUploadAttribute(objet, uploadField, result);
        }
    }

    // Remplisse les attributs de l'objet si des donnees provenant d'un formulaire existe
    // Valable pour attribut de meme nom et pour un seul valeur ou un tableau
    protected void loadObjectAttribute(Object objet, HttpServletRequest request) throws Exception {
        Map<String, String[]> parameters = request.getParameterMap();
        Field[] attributs = objet.getClass().getDeclaredFields();
        for (String parameter : parameters.keySet()) {
            for (Field attr : attributs) {
                if (parameter.equals(attr.getName()) && parameters.get(parameter).length == 1) {
                    FrameworkUtility.affectAttribute(objet, attr, parameters.get(parameter)[0]);
                } else if (parameter.equals(attr.getName() + "[]")) {
                    String[] values = parameters.get(parameter);
                    FrameworkUtility.affectArrayAttribute(objet, attr, parameters.get(parameter));
                }
            }
        }

        // Si un ou plusieur fichier est envoyé
        if (request.getContentType() != null && request.getContentType().toLowerCase().contains("multipart/form-data")) {
            for (Part part : request.getParts()) {
                if (part != null && part.getSubmittedFileName() != null && !part.getSubmittedFileName().equals("")) {
                    loadUploadedFile(objet, part);
                }
            }
        }
    }
    
    // Vérifie si une classe appartient au liste des singletons
    protected boolean isSingleton(String className) {
        for (Map.Entry<String, Object> entry : instanceList.entrySet()) {
            if (entry.getKey().equals(className)) {
                return true;
            }
        }
        return false;
    }

    // Prends l'objet contenue dans le Mapping et Joue avec les singletons
    protected Object getConcernedObject(Mapping map) throws Exception {
        if (isSingleton(map.getClassName())) {      // Si singleton
            if (instanceList.get(map.getClassName()) == null) {
                Class classInstance = Class.forName(map.getClassName());
                Object objet = classInstance.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                instanceList.put(map.getClassName(), objet);
                return objet;
            } 
            Object objet = instanceList.get(map.getClassName());
            FrameworkUtility.resetObjectAttribute(objet);
            return objet;
        } else {
            Class classInstance = Class.forName(map.getClassName());
            Object objet = classInstance.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            return objet;
        }
    }

    // Appele la fonction concerné avec l'URL
    protected Object callFunction(String url, HttpServletRequest request) throws Exception {
        Mapping map = getMapping(url);
        Object objet = getConcernedObject(map);
        loadURLAttribute(objet, request);       // Charge les attributs de classe par URL
        loadObjectAttribute(objet, request);    // Charge les attributs de classe par method post
        HashMap<String, String> parameters = getAllURLParameter(request);       // Les parametres en URL
        Method function = FrameworkUtility.findMethod(map.getMethod(), objet);
        Object[] parametres = FrameworkUtility.prepareFunctionParameter(function, parameters);      // Trouve les valeurs des arguments 
        Object result = function.invoke(objet, parametres);
        return result;
    }

    public Mapping getMapping(String url) throws Exception {
        if (url == null) {
            url = "";
        } else {
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
