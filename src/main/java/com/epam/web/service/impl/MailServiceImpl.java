package com.epam.web.service.impl;

import com.epam.web.service.MailService;
import com.epam.web.util.ResponseHandler;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
@Path("/service/mails")
public class MailServiceImpl implements MailService {
    @Override
    public Response getAllMessages() {
        return ResponseHandler.getAllMessages();
    }
    @Override
    public Response getMessagesByParamsEmail( String email) {
        return ResponseHandler.getMessagesByEmail(email);
    }
    @Override
    public Response getMessagesByParamsSubject(String subject) {
        return ResponseHandler.getMessagesBySubject(subject);
    }
    @Override
    public Response getBookById(Integer id) {
        return ResponseHandler.getBookById(id);
    }
    @Override
    public Response addMessage(Integer id, String email, String subject, String content) {
        return ResponseHandler.addMessage(id, email, subject, content); }
    @Override
    public Response deleteMessage(Integer id) {
        return ResponseHandler.deleteMessage(id);
    }
}
