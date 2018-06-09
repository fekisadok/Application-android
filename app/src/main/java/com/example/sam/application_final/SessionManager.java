package com.example.sam.application_final;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sam on 09/03/2018.
 */

public class SessionManager {

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private final static String PREFS_NAME= "app_prefs";
    private final static int PRIVATE_MODE = 0;
    private final static String IS_LOGGED = "isLogged";
    private final static String PSEUDO = "pseudo";
    private final static String ID="id";
    private final static String AVATAR="avatar";
    private final static String EMAIL="email";
    private final static String NOM="nom";
    private final static String PRENOM="prenom";
    private final static String DATE_NAISSANCE="date_naissance";
    private final static String VILLE="ville";
    private final static String PAYS="pays";

 /*   private final static String PSEUDO_PROFIL = "pseudo";
    private final static String AVATAR_PROFIL="avatar";
    private final static String EMAIL_PROFIL="email";
    private final static String NOM_PROFIL="nom";
    private final static String PRENOM_PROFIL="prenom";
    private final static String DATE_NAISSANCE_PROFIL="date_naissance";
    private final static String VILLE_PROFIL="ville";
    private final static String PAYS_PROFIL="pays";
    private final static String RELATION_PROFIL="relation";
    private final static String ID_PROFIL="id";*/


    private Context context;

    public SessionManager(Context context){
        this.context = context;
        prefs= context.getSharedPreferences(PREFS_NAME, PRIVATE_MODE);
        editor= prefs.edit();
    }

    public boolean isLogged(){
        return prefs.getBoolean(IS_LOGGED, false);
    }
    public String getPseudo(){
        return prefs.getString(PSEUDO, null);
    }
    public String getId(){
        return prefs.getString(ID, null);
    }
    public String getAvatar(){
        return prefs.getString(AVATAR, null);
    }
    public String getEmail(){
        return prefs.getString(EMAIL, null);
    }
    public String getNom(){
        return prefs.getString(NOM, null);
    }
    public String getPrenom(){
        return prefs.getString(PRENOM, null);
    }
    public String getDateNaissance(){
        return prefs.getString(DATE_NAISSANCE, null);
    }
    public String getVille(){
        return prefs.getString(VILLE, null);
    }
    public String getPays(){
        return prefs.getString(PAYS, null);
    }

/*
    public String getPseudoProfil(){
        return prefs.getString(PSEUDO_PROFIL, null);
    }
    public String getAvatarProfil(){
        return prefs.getString(AVATAR_PROFIL, null);
    }
    public String getNomProfil(){
        return prefs.getString(NOM_PROFIL, null);
    }
    public String getPrenomProfil(){
        return prefs.getString(PRENOM_PROFIL, null);
    }
    public String getDateNaissanceProfil(){
        return prefs.getString(DATE_NAISSANCE_PROFIL, null);
    }
    public String getVilleProfil(){
        return prefs.getString(VILLE_PROFIL, null);
    }
    public String getPaysProfil(){
        return prefs.getString(PAYS_PROFIL, null);
    }
    public String getIdProfil(){
        return prefs.getString(ID_PROFIL, null);
    }
    public String getRelationProfil(){
        return prefs.getString(RELATION_PROFIL, null);
    }*/


    public void insertUser(String id, String pseudo, String avatar, String email, String nom, String prenom, String date_naissance, String ville, String pays){
        editor.putBoolean(IS_LOGGED, true);
        editor.putString(ID, id);
        editor.putString(PSEUDO, pseudo);
        editor.putString(AVATAR, avatar);
        editor.putString(EMAIL, email);
        editor.putString(NOM, nom);
        editor.putString(PRENOM, prenom);
        editor.putString(DATE_NAISSANCE, date_naissance);
        editor.putString(VILLE, ville);
        editor.putString(PAYS, pays);
        editor.commit();
    }
 /*   public void LoadProfil( String pseudo, String id, String avatar, String nom, String prenom, String date_naissance, String ville, String pays, String relation){
        editor.putString(PSEUDO_PROFIL, pseudo);
        editor.putString(ID_PROFIL, id);
        editor.putString(AVATAR_PROFIL, avatar);
        editor.putString(NOM_PROFIL, nom);
        editor.putString(PRENOM_PROFIL, prenom);
        editor.putString(DATE_NAISSANCE_PROFIL, date_naissance);
        editor.putString(VILLE_PROFIL, ville);
        editor.putString(PAYS_PROFIL, pays);
        editor.putString(RELATION_PROFIL, relation);
        editor.apply();
    }*/
    public void logout(){
        editor.clear().commit();
    }
}
