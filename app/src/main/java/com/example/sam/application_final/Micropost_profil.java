package com.example.sam.application_final;

/**
 * Created by sam on 10/03/2018.
 */

public class Micropost_profil {
    //private String id;
    private String avatar;



    private String pseudo;
    private String contenu;
    private String created_at;
    //private String like_count;




    public Micropost_profil(String avatar, String pseudo, String contenu, String created_at) {
        //this.id = id;
        this.avatar = avatar;
        this.pseudo = pseudo;
        this.contenu = contenu;
        this.created_at = created_at;
        //this.like_count = like_count;
    }

    /*public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
*/
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

 /*   public String getLike_count() {
        return like_count;
    }

    public void setLike_count(String like_count) {
        this.like_count = like_count;
    }*/

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
}
