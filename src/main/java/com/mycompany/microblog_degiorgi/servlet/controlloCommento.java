/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.microblog_degiorgi.servlet;

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
public class controlloCommento extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            
        if(request.getSession(false).getAttribute("username") != null){
            
            String idPost = request.getParameter("postId");
            long postId = Long.parseLong(idPost);
            
            request.setAttribute("postId", postId);
            
            request.getRequestDispatcher("newComment.jsp").include(request, response);
        }else{
            request.getRequestDispatcher("permesso_negato.html").include(request, response);
        }
                
          
        
    }
}