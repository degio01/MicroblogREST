/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.microblog_degiorgi.servlet;

import com.google.common.base.Charsets;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import com.mycompany.microblog_degiorgi.controller.PostJpaController;
import com.mycompany.microblog_degiorgi.controller.UtenteJpaController;
import com.mycompany.microblog_degiorgi.entity.Post;
import com.mycompany.microblog_degiorgi.entity.Utente;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nicolò
 */
public class login extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String Username = request.getParameter("username");
        String Password = request.getParameter("password");

        Utente u = UtenteJpaController.findUtentebyUsername(Username);
        
        String S = u.getSalt();

        String passwordEncrypted = Password + S;

        Hasher hasher = Hashing.sha256().newHasher();
        hasher.putString(passwordEncrypted, Charsets.UTF_8);
        String sha256 = hasher.hash().toString();

        if (sha256.equals(u.getPassword())) {

            HttpSession s = request.getSession();
            s.setAttribute("Username", u.getUsername());
            
            List<Post> ls = PostJpaController.findPostEntities();
            
            request.setAttribute("ListaPost", ls);
            request.setCharacterEncoding("UTF-8");
            
            request.getRequestDispatcher("listaPost.jsp").forward(request, response);

        } else {

            request.getRequestDispatcher("permesso_negato.html").forward(request, response);
        }

    }
}