/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.microblog_degiorgi.servizi;

import com.mycompany.microblog_degiorgi.controller.PostJpaController;
import com.mycompany.microblog_degiorgi.entity.Post;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Nicolò
 */
@Path("/posts")
public class PostServizi {

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Post> getPosts() {
        List<Post> ls = PostJpaController.findPostEntities();
        return ls;
    }
    
    

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Post getPost(@PathParam("id") String postId) {
        Post post = PostJpaController.findPost(Long.parseLong(postId));
        return post;

    }
    
    
    
    
    @POST
    @Produces({MediaType.TEXT_HTML})
    @Consumes({MediaType.APPLICATION_JSON})
    public void createPost(Post p){
        PostJpaController.create(p);
    }
}