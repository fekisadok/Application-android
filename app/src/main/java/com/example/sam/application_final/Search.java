package com.example.sam.application_final;

/**
 * Created by sam on 10/03/2018.
 */

public class Search {
    private String avatar;
    private String pseudo;



    public Search( String pseudo, String avatar) {
        this.avatar = avatar;
        this.pseudo = pseudo;
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

    //public int length() {
    //    return this.length();
   // }

}
