/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.microblog_degiorgi.servlet;

import com.mycompany.microblog_degiorgi.controller.CommentoJpaController;
import com.mycompany.microblog_degiorgi.controller.PostJpaController;
import com.mycompany.microblog_degiorgi.controller.UtenteJpaController;
import com.mycompany.microblog_degiorgi.entity.Commento;
import com.mycompany.microblog_degiorgi.entity.Post;
import com.mycompany.microblog_degiorgi.entity.Utente;
import java.io.IOException;
import java.util.Date;
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
public class createComment extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String testo = request.getParameter("text");

        Date date = new Date();

        Commento c = new Commento();
        c.setTesto(testo);
        c.setDataOra(date);

        HttpSession session = request.getSession(false);

        Utente u = UtenteJpaController.findUtentebyUsername((String) session.getAttribute("username"));

        c.setUtente(u);

        Post post = PostJpaController.findPost(Long.parseLong(request.getParameter("postId")));
        c.setPost(post);
        
        
        CommentoJpaController.create(c);

        List<Post> ls = PostJpaController.findPostEntities();

        request.setAttribute("ListaPost", ls);
        request.setCharacterEncoding("UTF-8");

        request.getRequestDispatcher("listaPost.jsp").forward(request, response);
    }
}