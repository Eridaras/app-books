package com.distribuida.rest;

import com.distribuida.entity.Book;
import com.distribuida.servicio.interfaces.ServicioBook;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
@Path("/books")
public class ServicioBookRest{
    // final ServicioPersona servicio = new ServicioPersona();
    @Inject
    private ServicioBook servicio;

    //Encontrar una persona
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book find(@PathParam("id") Integer id){
        Book obj = servicio.find(id);
        if(obj==null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return obj;
    }
    @GET
    @Path("author/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> findAuhor(@PathParam("id") Integer id){
        List<Book> obj = servicio.findAuthor(id);
        if(obj==null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return obj;
    }

    //Listar a todos
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> findAll(){

        return servicio.findAll();
    }

    //Borrar persona
    @DELETE @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) throws SQLException {
        servicio.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    //Ingresar persona
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insert(Book obj) throws SQLException{
        servicio.create(obj);
        return Response.status(Response.Status.CREATED).build();
    }
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Integer id, Book obj) throws SQLException{
        System.out.println("Servicio Update usado"  + obj +" - "+ id );
        servicio.update(id, obj);
        return Response.status(Response.Status.OK).build();
    }

}
