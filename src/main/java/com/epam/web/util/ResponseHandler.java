package com.epam.web.util;


import com.epam.dao.MailDAO;
import com.epam.model.Mail;
import com.epam.model.MailException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

public class ResponseHandler {
    private static Gson GSON =  new GsonBuilder().setPrettyPrinting().create();
    private static MailDAO DAO = new MailDAO();
    private static String INCORRECT_INPUT_VALUE = "Incorrect input value: ";
    private static String MESSAGE_BY_ID_NOT_FOUND = "Mail was not found";
    private static String MAIL_BY_ID_NOT_FOUND = "Mail was not found";
    private static String NO_INPUT_PARAMETERS = "There are not input parameters: ";
    private static String MESSAGE_BY_EMAIL_NOT_FOUND = "There are no mails with such parametres: ";
    private static String MESSAGE_BY_SUBJECT_NOT_FOUND = "There are no mails with such parametres: ";
    private static String MESSAGE_WAS_ADDED = "Mail was added successfully";
    private static String MESSAGE_WAS_UPDATED = "Mail was updated successfully";
    private static String MESSAGE_WAS_DELETED = "Mail was deleted successfully";

    public static Response getAllMessages(){
        return Response.status(200).entity(GSON.toJson(DAO.getAll())).build();
    }
    public static Response getMessagesByEmail(String email) {
        ArrayList<Mail> result;
        if (email == null) {
            throw new MailException(NO_INPUT_PARAMETERS + "[from:" + email + "]",
                    Response.Status.BAD_REQUEST);
        }
        else{
            result = DAO.getAllByEmail(email);
        }
        if (result.size() < 1) {
            throw new MailException(MESSAGE_BY_EMAIL_NOT_FOUND
                    + "[email:" + email + "]", Response.Status.NO_CONTENT);
        }
        return Response.status(200).entity(GSON.toJson(result)).build();
    }

    public static Response getMessagesBySubject(String subject) {
        ArrayList <Mail> result;
        if(subject == null){
            throw new MailException(NO_INPUT_PARAMETERS+"[subject:"+subject+"]",
                    Response.Status.BAD_REQUEST);
        }
        else{
            result = DAO.getAllBySubject(subject);
        }
        if(result.size()<1){
            throw new MailException(MESSAGE_BY_SUBJECT_NOT_FOUND
                    +"[subject:"+subject+"]", Response.Status.NO_CONTENT);
        }

        return Response.status(200).entity(GSON.toJson(result)).build();
    }
    public static Response addMessage(Integer id, String email, String subject ,String content) {
        if(id == null ||  email == null || subject == null ||content == null || id < 0){
            throw new MailException(INCORRECT_INPUT_VALUE
                    +"[id:"+id+",email:"+email+",subject:"+subject+",content:"+content+"]", Response.Status.BAD_REQUEST);
        }

        Mail newMessage = new Mail(id , email, subject, content);
        JsonObject result = new JsonObject();
        Mail existedMessage = DAO.get(id);
        if(existedMessage != null){
            DAO.update(existedMessage, newMessage);
            result.addProperty("Message",MESSAGE_WAS_UPDATED);
        }else{
            DAO.add(newMessage);
            result.addProperty("Message",MESSAGE_WAS_ADDED);
        }
        return Response.status(200).entity(result.toString()).build();
    }
    public static Response deleteMessage(Integer id) {
        if(id < 0){
            throw new MailException(INCORRECT_INPUT_VALUE+id, Response.Status.BAD_REQUEST);
        }

        JsonObject result = new JsonObject();
        if(DAO.get(id) == null){
            throw new MailException(MESSAGE_BY_ID_NOT_FOUND, Response.Status.NO_CONTENT);
        }else{
            DAO.delete(id);
            result.addProperty("Message",MESSAGE_WAS_DELETED);
        }
        return Response.status(200).entity(result.toString()).build();
    }
    public static Response getBookById(Integer id) {
        if(id < 0){
            throw new MailException(INCORRECT_INPUT_VALUE+id, Response.Status.BAD_REQUEST);
        }
        Mail result = DAO.get(id);

        if(result == null){
            throw new MailException(MAIL_BY_ID_NOT_FOUND, Response.Status.NO_CONTENT);
        }
        return Response.status(200).entity(GSON.toJson(DAO.get(id))).build();
    }
}
