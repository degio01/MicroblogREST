<%@page import="com.mycompany.microblog_degiorgi.controller.CommentoJpaController"%>
<%@page import="com.mycompany.microblog_degiorgi.entity.Commento"%>
<%@page import="com.mycompany.microblog_degiorgi.entity.Commento"%>
<%@page import="com.mycompany.microblog_degiorgi.entity.Post"%>



<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Post</title>
</head>

<body>

    <div>
        <div>
            <form action="CheckUser">
                <h1>Microblog</h1>
                <button type="submit">Crea un nuovo Post</button>
            </form>
            <br>
            <p>or</p>
            <button type="submit" onclick="location.href = '#firstComment';">Vedi Post</button>
        </div>

    </div>

    <a id="firstComment"></a>
    <div>
        
        <%@ page import="com.mycompany.microblog_degiorgi.entity.*" %> 
        <%@ page import="com.mycompany.microblog_degiorgi.controller.*" %> 
        <%@ page import="java.util.List" %>
        
        <% List<Post> listaPost = (List<Post>) request.getAttribute("ListaPost"); %>
        <% for (int i = 0; i<listaPost.size(); i++) {%>
        <% Post post = listaPost.get(i); %>
        
        <div>
            <h1><% out.print(post.getTitolo()); %></h1>
            <p><% out.print(post.getUtente().getUsername()); %></p>
            <p><% out.print(post.getDataOra().toString()); %></p>
            <p><% out.print(post.getTesto()); %></p>
            <hr>
            <br>
            <h4>Comments</h4>
            <hr>
            <div>
                <% List<Commento> listaCommenti = (List<Commento>) CommentoJpaController.findCommentoByPost(post);%>    
                <%for (int c = 0; c<listaCommenti.size(); c++) {%>
                <% Commento commento = listaCommenti.get(c);%>
                
                <div>
                    <p><% out.print(commento.getUtente().getUsername());%></p>
                    <p><% out.print(commento.getDataOra().toString());%></p>
                    <p><% out.print(commento.getTesto());%></p>
                    <br>
                    <hr>
                </div>
                  <%};%>
            </div>
            <form action="CheckUserComment">
                <% long postId = post.getId();%>
                <input type="hidden" name="postId" value="<%=postId%>">
                <button type="submit">Leave a comment</button>
            </form>
            
        </div>
        
        <%};%>
    </div>
</body>

</html>
