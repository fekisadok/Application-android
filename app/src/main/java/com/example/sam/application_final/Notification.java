package com.example.sam.application_final;

/**
 * Created by sam on 10/03/2018.
 */

public class Notification {
    private String pseudo;
    private String avatar;
    private String email;
    private String subject_id;
    private String name;
    private String seen;
    private String user_id;
    private String created_at;


    public Notification(String pseudo, String avatar, String email, String subject_id, String name, String seen, String user_id, String created_at) {
        this.pseudo = pseudo;
        this.avatar = avatar;
        this.email = email;
        this.subject_id = subject_id;
        this.name = name;
        this.seen = seen;
        this.user_id = user_id;
        this.created_at = created_at;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeen() {
        return seen;
    }

    public void setSeen(String seen) {
        this.seen = seen;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }


}
