package com.bigcorp.booking.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Une Servlet JEE
 */
//servlet : une class qui herite d'une autre class - class abstraite
    //@WebServlet traite l'url
@WebServlet("/welcome")
public class GreetingServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Traite les requêtes GET /welcome 
	 */
    //doGet : methode que le serveur JEE appelle lors d'une requete Get arrive sur le serveur
    //2 arguments : request (recupere), response (envoyer)
	@Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        response.setBufferSize(8192);
        //getWriter pour acceder a la reponse -- getMethode pour recuperer la methode --getHeader : recuperer un Header par sa cle
        //out : object pour afficher la response (juste un nom) - responseWriter
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>"
                    + "<head><title>Booking</title></head>");
            out.println("<body  bgcolor=\"#ffffff\">"
                    + "<h2>Welcome to the booking app servlet page !!!</h2>");

            String username = request.getParameter("username");
            System.out.println("username = " + username);
            out.println("<div> Au fait, le username vaut  " + username + "</div>");

            String userAgent = request.getHeader("User-Agent");
            out.println("<div> Au fait, le User-Agent vaut  " + userAgent + "</div>");
//            if (username != null && username.length() > 0) {
//                RequestDispatcher dispatcher =
//                        getServletContext().getRequestDispatcher("/response");
//
//                if (dispatcher != null) {
//                    dispatcher.include(request, response);
//                }
//            }
            out.println("</body></html>");
        }
    }

    @Override
    public String getServletInfo() {
        return "The Hello servlet says hello.";

    }
}
