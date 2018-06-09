package com.example.sam.application_final;

/**
 * Created by sam on 10/03/2018.
 */

public class Friend {
    private String avatar;
    private String pseudo;
    private String id;



    public Friend(String id, String pseudo, String avatar) {
        this.avatar = avatar;
        this.pseudo = pseudo;
        this.id=id;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
}
