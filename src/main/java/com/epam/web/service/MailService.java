package com.epam.web.service;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
public interface MailService {
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMessages();

    @GET
    @Path("/paramsEmail")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessagesByParamsEmail(@QueryParam("from") String email);

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookById(@PathParam("id") Integer id);
    @GET
    @Path("/paramsSubject")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessagesByParamsSubject(@QueryParam("subject") String subject);

    @POST
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addMessage(@PathParam("id") Integer id,
                                       @FormParam("email") String email,
                                       @FormParam("subject") String subject,
                                       @FormParam("content") String content);

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMessage(@PathParam("id") Integer id);

}
