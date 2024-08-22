package com.bigcorp.booking.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Autre exemple de Servlet, utilisant un paramètre de requête
 */
@WebServlet("/affichage")
public class NewServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            String username = request.getParameter("username");
            if (username != null && username.length() > 0) {
                out.println("<h2>Coucou " + username + " !</h2>");
            } else {
                out.println("<h2>Coucou Anonyme !</h2>");
            }

            String acceptLanguage = request.getHeader("Accept-Language");
            out.println("<div> Au fait, les langues acceptées sont  " + acceptLanguage + "</div>");
            String acceptEncoding = request.getHeader("Accept-Encoding");
            out.println("<div> les encodages acceptés sont " + acceptEncoding + "</div>");
            String connection = request.getHeader("Connection");
            out.println("<div>La connection est " + connection + "</div>");

            //request.getHeaderNames();
//            out.println("<h2>les en-têtes de la requête</h2>");
//            Enumeration entetes = request.getHeaderNames();
//            while (entetes.hasMoreElements()) {
//                String nomEntete = (String) entetes.nextElement();
//                out.println("<div>" + nomEntete + " : " +
//                        request.getHeader(nomEntete) + "</div>");
//            }

            //recuperer session
            HttpSession session = request.getSession();
            session.getAttributeNames();
            //compteur
            Integer compteur = (Integer) session.getAttribute("compteur");
            double nombreAleatoire = (int) (10 + Math.random()*11);

            if(session.getAttribute("compteur") == null) {
                compteur = 1;
                nombreAleatoire = 0;
            } else {
                compteur++;
                nombreAleatoire++;

            }
            session.setAttribute("compteur", compteur); //cle, valeur

            //afficher compteur
            out.println("<h2>Compteur</h2>");
            out.println("<div>" + compteur + "</div>");
            out.println(("<div>Et en bonus, un nombre aléatoire : " + nombreAleatoire + "</div>"));


//ServletContext 	Définition d'un objet pour obtenir des informations sur le contexte d'exécution de la servlet
        }

    }

    @Override
    public String getServletInfo() {
        return "The Response servlet says hello.";

    }
}