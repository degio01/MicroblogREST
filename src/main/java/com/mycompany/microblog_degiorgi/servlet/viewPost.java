/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.microblog_degiorgi.servlet;

import com.mycompany.microblog_degiorgi.controller.PostJpaController;
import com.mycompany.microblog_degiorgi.entity.Post;
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
public class viewPost extends HttpServlet{
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Post> ls = PostJpaController.findPostEntities();
        
        HttpSession s = request.getSession(false);
        
        request.setAttribute("ListaPost", ls);
        request.setCharacterEncoding("UTF-8");
        
        request.getRequestDispatcher("listaPost.jsp").forward(request, response);
    }
}
