package com.epam.dao;

import com.epam.model.Mail;
import java.util.ArrayList;
import java.util.Iterator;

public class MailDAO {
    private static ArrayList<Mail> messages;
    public MailDAO() {
        init();
    }
    private void init(){
        messages = new ArrayList<>();
        messages.add(new Mail(1, "bilokura@gmail.com", "Subject1","content1"));
        messages.add(new Mail(2, "hnatko2@gmail.com", "Subject2","content2"));
        messages.add(new Mail(3, "kristinabilokura@gmail.com", "Subject3","content3"));
        messages.add(new Mail(4, "test4@gmail.com", "Subject4","content4"));
        messages.add(new Mail(5, "test5@gmail.com", "Subject5","content5"));
        messages.add(new Mail(6, "test6@gmail.com", "Subject6","content6"));
        messages.add(new Mail(7, "test7@gmail.com", "Subject7","content7"));
        messages.add(new Mail(8, "test8@gmail.com", "Subject8","content8"));
        messages.add(new Mail(9, "test9@gmail.com", "Subject9","content9"));
        messages.add(new Mail(10, "test10@gmail.com", "Subject10","content10"));
    }
    public void add(Mail mail){
        messages.add(mail);
    }


    public boolean delete(Integer id){
        Iterator<Mail> iter = messages.iterator();
        while (iter.hasNext()) {
            Mail mail= iter.next();
            if(mail.getId().equals(id)){
                iter.remove();
                return true;
            }
        }
        return false;
    }
    public void update(Mail oldMail,Mail newMail){
        Iterator<Mail> iter = messages.iterator();
        boolean oldMailDeleted = false;
        while (iter.hasNext()) {
            if(iter.next().getId().equals(oldMail.getId())){
                iter.remove();
                oldMailDeleted = true;
            }
        }
        if(oldMailDeleted){
            messages.add(newMail);
        }


    }
    public ArrayList<Mail> getAll(){
        return messages;
    }
    public Mail get(Integer id){
        for (Mail mail : messages) {
            if(mail.getId().equals(id)){
                return mail;
            }
        }
        return null;
    }
    public ArrayList<Mail> getAllByEmail(String email){
        ArrayList<Mail> list = new ArrayList<>();
        for (Mail mail : messages) {
            if(mail.getEmail().equals(email)){
                list.add(mail);
            }
        }
        return list;
    }
    public ArrayList<Mail> getAllBySubject(String subject){
        ArrayList<Mail> list = new ArrayList<>();
        for (Mail mail : messages) {
            if(mail.getSubject().equals(subject)){
                list.add(mail);
            }
        }
        return list;
    }
}
