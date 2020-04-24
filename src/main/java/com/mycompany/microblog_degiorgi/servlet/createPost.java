/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.microblog_degiorgi.servlet;

import com.mycompany.microblog_degiorgi.controller.PostJpaController;
import com.mycompany.microblog_degiorgi.controller.UtenteJpaController;
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
public class createPost extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String title = request.getParameter("title");
        String text = request.getParameter("text");

        Date date = new Date();

        Post post = new Post();
        post.setTitolo(title);
        post.setTesto(text);
        post.setDataOra(date);

        HttpSession session = request.getSession(false);

        Utente u = UtenteJpaController.findUtentebyUsername((String) session.getAttribute("Username"));

        post.setUtente(u);

        PostJpaController.create(post);

        List<Post> ls = PostJpaController.findPostEntities();

        request.setAttribute("listaPost", ls);
        request.setCharacterEncoding("UTF-8");

        request.getRequestDispatcher("listaPost.jsp").forward(request, response);

    }
}
