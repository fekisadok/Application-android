package com.example.sam.application_final;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sam on 09/03/2018.
 */

public class ProfilManager {

    private SharedPreferences prefs1;
    private SharedPreferences.Editor editor1;
    private final static String PREFS_NAME= "pref";
    private final static int PRIVATE_MODE = 0;
    private final static String PSEUDO_PROFIL = "pseudo";
    private final static String AVATAR_PROFIL="avatar";
    private final static String EMAIL_PROFIL="email";
    private final static String NOM_PROFIL="nom";
    private final static String PRENOM_PROFIL="prenom";
    private final static String DATE_NAISSANCE_PROFIL="date_naissance";
    private final static String VILLE_PROFIL="ville";
    private final static String PAYS_PROFIL="pays";
    private final static String RELATION_PROFIL="relation";
    private final static String ID_PROFIL="id";

    private Context context;

    public ProfilManager(Context context){
        this.context = context;
        prefs1= context.getSharedPreferences(PREFS_NAME, PRIVATE_MODE);
        editor1= prefs1.edit();
    }

    public String getPseudoProfil(){
        return prefs1.getString(PSEUDO_PROFIL, null);
    }
    public String getAvatarProfil(){
        return prefs1.getString(AVATAR_PROFIL, null);
    }
    public String getNomProfil(){
        return prefs1.getString(NOM_PROFIL, null);
    }
    public String getPrenomProfil(){
        return prefs1.getString(PRENOM_PROFIL, null);
    }
    public String getDateNaissanceProfil(){
        return prefs1.getString(DATE_NAISSANCE_PROFIL, null);
    }
    public String getVilleProfil(){
        return prefs1.getString(VILLE_PROFIL, null);
    }
    public String getPaysProfil(){
        return prefs1.getString(PAYS_PROFIL, null);
    }
    public String getIdProfil(){
        return prefs1.getString(ID_PROFIL, null);
    }
    public String getRelationProfil(){
        return prefs1.getString(RELATION_PROFIL, null);
    }

    public void LoadProfil( String pseudo, String id, String avatar, String nom, String prenom, String date_naissance, String ville, String pays, String relation){
        editor1.putString(PSEUDO_PROFIL, pseudo);
        editor1.putString(ID_PROFIL, id);
        editor1.putString(AVATAR_PROFIL, avatar);
        editor1.putString(NOM_PROFIL, nom);
        editor1.putString(PRENOM_PROFIL, prenom);
        editor1.putString(DATE_NAISSANCE_PROFIL, date_naissance);
        editor1.putString(VILLE_PROFIL, ville);
        editor1.putString(PAYS_PROFIL, pays);
        editor1.putString(RELATION_PROFIL, relation);
        editor1.commit();
    }
    public void logout1(){
        editor1.clear().commit();
    }
}
