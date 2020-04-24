/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.microblog_degiorgi.servlet;

import com.mycompany.microblog_degiorgi.controller.UtenteJpaController;
import com.mycompany.microblog_degiorgi.entity.Utente;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nicolò
 */
public class controlloUtente extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("username") != null) {

            String username = (String) session.getAttribute("username");

            Utente u = UtenteJpaController.findUtentebyUsername(username);

            String permesso = u.getPermissions();

            if (permesso.equals("admin")) {
                request.getRequestDispatcher("Post.html").forward(request, response);
            } else {
                request.getRequestDispatcher("permesso_negato.html").forward(request, response);
            } 
        }else{

            request.getRequestDispatcher("permesso_negato.html").forward(request, response);
        }

    }
}
