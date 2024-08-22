/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package com.bigcorp.booking.servlet;


import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Autre exemple de Servlet, utilisant un paramètre de requête
 */
@WebServlet("/response")
public class ResponseServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            String username = request.getParameter("username");

            //recupere session -> c'est un objet java
            HttpSession session = request.getSession();
            session.getAttributeNames(); //on recupere parametres & valeurs (ensemble clé-valeur)

            Integer maCle = (Integer) session.getAttribute("maCle");
            if(session.getAttribute("maCle") == null) {
                maCle = 1;
            } else {
                maCle++;
            }
            session.setAttribute("maCle", maCle); //cle, valeur

            //String laValeurQuiVientDeLaSession = (String) session.getAttribute("maCle");

            //if (username != null && username.length() > 0) {
                out.println("<h2>Salut, " + username + "!</h2>");
                out.println(("<div>ma clé : " + maCle + "</div>"));
            //}
        }
    }

    @Override
    public String getServletInfo() {
        return "The Response servlet says hello.";

    }
}
