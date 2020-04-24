/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.microblog_degiorgi.servlet;

import com.google.common.base.Charsets;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import com.mycompany.microblog_degiorgi.controller.UtenteJpaController;
import com.mycompany.microblog_degiorgi.entity.Utente;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Nicolò
 */
public class registrazione extends HttpServlet {
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        
            String Username = request.getParameter("Username");
            String Password = request.getParameter("Password");
            
            Random random = new SecureRandom();
                
            
            
            byte[] SaltGeneration = new byte [16];
            random.nextBytes(SaltGeneration);
        
            String S = DatatypeConverter.printBase64Binary(SaltGeneration);
            
         
            String PasswordEncrypted = Password + S;
        
            Hasher hasher = Hashing.sha256().newHasher();
            
            hasher.putString(PasswordEncrypted, Charsets.UTF_8);
            
            String sha256 = hasher.hash().toString();
            
            
            Utente U = new Utente();
            U.setUsername(Username);
            U.setPassword(sha256);
            U.setSalt(S);
            U.setPermissions("User");
            
            UtenteJpaController.create(U);
            
            request.getRequestDispatcher("index.html").forward(request, response);
                    

            
            
            
        
    }
}
