package com.example.sam.application_final;

/**
 * Created by sam on 10/03/2018.
 */

public class Micropost {
    //private int M_Id;
    private String avatar;
    private String contenu;
    private String pseudo;
    //private int User_Id;
    //private String Email;

    //private String Like_Count;
    private String created_at;
/*
    public int getM_Id() {
        return M_Id;
    }

    public void setM_Id(int m_Id) {
        M_Id = m_Id;
    }*/
    public Micropost(String avatar, String pseudo, String contenu, String created_at) {
        this.avatar = avatar;
        this.pseudo = pseudo;
        this.contenu = contenu;
        this.created_at = created_at;
    }
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

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /*public int getUser_Id() {
        return User_Id;
    }

    public void setUser_Id(int user_Id) {
        User_Id = user_Id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
*/


    /*public String getLike_Count() {
        return Like_Count;
    }

    public void setLike_Count(String like_Count) {
        Like_Count = like_Count;
    }*/

    public String getCreated_At() {
        return created_at;
    }

    public void setCreated_At(String created_at) {
        this.created_at = created_at;
    }
}
