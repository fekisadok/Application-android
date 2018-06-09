package com.example.sam.application_final.myrequest;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sam on 05/03/2018.
 */

public class MyRequest {

    private Context context;
    private RequestQueue queue;

    public MyRequest(Context context, RequestQueue queue){
        this.context= context;
        this.queue=queue;
    }
    public void register(final String pseudo, final String email, final String password, final String password2, final RegisterCallBack callBack){
        String url="http://192.168.43.194/Appli_PPE/register.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            public void onResponse(String response) {
                Map<String, String> errors = new HashMap<>();


                try {
                    JSONObject json = new JSONObject(response);
                    Boolean error = json.getBoolean("error");

                    if (!error){
                        callBack.onSuccess("Vous êtes bien inscrit");
                    }else {

                        JSONObject messages = json.getJSONObject("message");
                        if (messages.has("pseudo")){
                                errors.put("pseudo", messages.getString("pseudo"));
                        }
                        if (messages.has("email")){
                                errors.put("email", messages.getString("email"));
                        }
                        if (messages.has("password")){
                                errors.put("password", messages.getString("password"));
                        }
                        if (messages.has("password2")){
                            errors.put("password2", messages.getString("password2"));
                        }
                        callBack.inputErrors(errors);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NetworkError){
                    callBack.onError("Impossible de se connecter");
                }else if (error instanceof VolleyError){
                    callBack.onError("Une erreur s'est produite");
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map= new HashMap<>();
                map.put("pseudo", pseudo);
                map.put("email", email);
                map.put("password", password);
                map.put("password2", password2);
                return map;
            }
        };

        queue.add(request);
    }

    public interface RegisterCallBack{
        void onSuccess(String message);
        void inputErrors(Map<String, String> errors);
        void onError(String message);
    }

    public void login(final String identifiant, final String password, final LoginCallBack callBack){
        String url="http://192.168.43.194/Appli_PPE/login.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            public void onResponse(String response) {

                try {
                    JSONObject json = new JSONObject(response);
                    Boolean error = json.getBoolean("error");

                    if (!error){
                        String id=json.getString("id");
                        String pseudo= json.getString("pseudo");
                        String avatar= json.getString("avatar");
                        String email= json.getString("email");
                        String nom= json.getString("nom");
                        String prenom= json.getString("prenom");
                        String date_naissance= json.getString("date_naissance");
                        String ville= json.getString("ville");
                        String pays= json.getString("pays");
                        callBack.onSuccess(id, pseudo, avatar, email, nom, prenom, date_naissance, ville, pays);
                    }else {
                        callBack.onError(json.getString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NetworkError){
                    callBack.onError("Impossible de se connecter");
                }else if (error instanceof VolleyError){
                    callBack.onError("Une erreur s'est produite");
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map= new HashMap<>();
                map.put("identifiant", identifiant);
                map.put("password", password);
                return map;
            }
        };

        queue.add(request);
    }

    public interface LoginCallBack{
        void onSuccess(String id, String pseudo, String avatar, String email, String nom, String prenom, String date_naissance, String ville, String pays);
        void onError(String message);
    }
    public void micropost(final String id, final String contenu, final MicropostCallBack callBack){
        String url="http://192.168.43.194/Appli_PPE/microposts.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            public void onResponse(String response) {
                //Map<String, String> errors = new HashMap<>();


                try {
                    JSONObject json = new JSONObject(response);
                    Boolean error = json.getBoolean("error");

                    if (!error){
                        callBack.onSuccess(json.getString("message"));
                    }else {
                        callBack.onError(json.getString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NetworkError){
                    callBack.onError("Impossible de se connecter");
                }else if (error instanceof VolleyError){
                    callBack.onError("Une erreur s'est produite");
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map= new HashMap<>();
                map.put("id", id);
                map.put("contenu", contenu);
                return map;
            }
        };

        queue.add(request);
    }

    public interface MicropostCallBack{
        void onSuccess(String message);
        //void inputErrors(Map<String, String> errors);
        void onError(String message);
    }
    public void loadprofil(final String pseudo, final String id, final LoadProfilCallBack callBack){
        String url="http://192.168.43.194/Appli_PPE/load_profil.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            public void onResponse(String response) {

                try {
                    JSONObject json = new JSONObject(response);
                    Boolean error = json.getBoolean("error");

                    if (!error){
                        String pseudo= json.getString("pseudo");
                        String id= json.getString("id");
                        String avatar= json.getString("avatar");
                        String nom= json.getString("nom");
                        String prenom= json.getString("prenom");
                        String date_naissance= json.getString("date_naissance");
                        String ville= json.getString("ville");
                        String pays= json.getString("pays");
                        String relation= json.getString("relation");
                        callBack.onSuccess( pseudo,id, avatar, nom, prenom, date_naissance, ville, pays, relation);
                    }else {
                        callBack.onError(json.getString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NetworkError){
                    callBack.onError("Impossible de se connecter");
                }else if (error instanceof VolleyError){
                    callBack.onError("Une erreur s'est produite");
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map= new HashMap<>();
                map.put("pseudo", pseudo);
                map.put("id", id);
                return map;
            }
        };

        queue.add(request);
    }

    public interface LoadProfilCallBack{
        void onSuccess(String id, String pseudo, String avatar, String nom, String prenom, String date_naissance, String ville, String pays, String relation);
        void onError(String message);
    }
    public void Add_Friend(final String id_user, final String id_friend, final Add_FriendCallBack callBack){
        String url="http://192.168.43.194/Appli_PPE/add_friend.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            public void onResponse(String response) {
                try {
                    JSONObject json = new JSONObject(response);
                    Boolean error = json.getBoolean("error");

                    if (!error){
                        callBack.onSuccess(json.getString("message"));
                    }else {
                        callBack.onError(json.getString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NetworkError){
                    callBack.onError("Impossible de se connecter");
                }else if (error instanceof VolleyError){
                    callBack.onError("Une erreur s'est produite");
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map= new HashMap<>();
                map.put("id_user", id_user);
                map.put("id_friend", id_friend);
                return map;
            }
        };

        queue.add(request);
    }

    public interface Add_FriendCallBack{
        void onSuccess(String message);
        //void inputErrors(Map<String, String> errors);
        void onError(String message);
    }
    public void Accept_Friend(final String id_user, final String id_friend, final Accept_FriendCallBack callBack){
        String url="http://192.168.43.194/Appli_PPE/accept_friend.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            public void onResponse(String response) {
                try {
                    JSONObject json = new JSONObject(response);
                    Boolean error = json.getBoolean("error");

                    if (!error){
                        callBack.onSuccess(json.getString("message"));
                    }else {
                        callBack.onError(json.getString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NetworkError){
                    callBack.onError("Impossible de se connecter");
                }else if (error instanceof VolleyError){
                    callBack.onError("Une erreur s'est produite");
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map= new HashMap<>();
                map.put("id_user", id_user);
                map.put("id_friend", id_friend);
                return map;
            }
        };

        queue.add(request);
    }

    public interface Accept_FriendCallBack{
        void onSuccess(String message);
        //void inputErrors(Map<String, String> errors);
        void onError(String message);
    }
    public void Delete_Friend(final String id_user, final String id_friend, final Delete_FriendCallBack callBack){
        String url="http://192.168.43.194/Appli_PPE/delete_friend.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            public void onResponse(String response) {
                try {
                    JSONObject json = new JSONObject(response);
                    Boolean error = json.getBoolean("error");

                    if (!error){
                        callBack.onSuccess(json.getString("message"));
                    }else {
                        callBack.onError(json.getString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NetworkError){
                    callBack.onError("Impossible de se connecter");
                }else if (error instanceof VolleyError){
                    callBack.onError("Une erreur s'est produite");
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map= new HashMap<>();
                map.put("id_user", id_user);
                map.put("id_friend", id_friend);
                return map;
            }
        };

        queue.add(request);
    }

    public interface Delete_FriendCallBack{
        void onSuccess(String message);
        //void inputErrors(Map<String, String> errors);
        void onError(String message);
    }

    public void update(final String id, final String pseudo, final String ville, final String pays, final String new_email, final String new_email_confirm, final String old_password,
                       final String new_password, final String new_password_confirm, final UpdateCallBack callBack){
        String url="http://192.168.43.194/Appli_PPE/update_profil.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            public void onResponse(String response) {
                Map<String, String> errors = new HashMap<>();


                try {
                    JSONObject json = new JSONObject(response);
                    Boolean error = json.getBoolean("error");

                    if (!error){
                        callBack.onSuccess("Profil mis à jour");
                    }else {

                        JSONObject messages = json.getJSONObject("message");
                        if (messages.has("pseudo")){
                            errors.put("pseudo", messages.getString("pseudo"));
                        }
                        if (messages.has("email")){
                            errors.put("email", messages.getString("email"));
                        }
                        if (messages.has("password")){
                            errors.put("password", messages.getString("password"));
                        }
                        callBack.inputErrors(errors);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NetworkError){
                    callBack.onError("Impossible de se connecter");
                }else if (error instanceof VolleyError){
                    callBack.onError("Une erreur s'est produite");
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map= new HashMap<>();
                map.put("id",id);
                map.put("pseudo", pseudo);
                map.put("ville", ville);
                map.put("pays", pays);
                map.put("new_email", new_email);
                map.put("new_email_confirm", new_email_confirm);
                map.put("old_password", old_password);
                map.put("new_password", new_password);
                map.put("new_password_confirm", new_password_confirm);
                return map;
            }
        };

        queue.add(request);
    }

    public interface UpdateCallBack{
        void onSuccess(String message);
        void inputErrors(Map<String, String> errors);
        void onError(String message);
    }
    /*public void micropost_profil(final String id, final String contenu, final MicropostProfilCallBack callBack){
        String url="http://192.168.1.16/Appli_PPE/profil.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            public void onResponse(String response) {
                //Map<String, String> errors = new HashMap<>();


                try {
                    JSONObject json = new JSONObject(response);
                    Boolean error = json.getBoolean("error");

                    if (!error){
                        callBack.onSuccess(json.getString("message"));
                    }else {
                        callBack.onError(json.getString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NetworkError){
                    callBack.onError("Impossible de se connecter");
                }else if (error instanceof VolleyError){
                    callBack.onError("Une erreur s'est produite");
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map= new HashMap<>();
                map.put("id", id);
                map.put("contenu", contenu);
                return map;
            }
        };

        queue.add(request);
    }

    public interface MicropostProfilCallBack{
        void onSuccess(String message);
        //void inputErrors(Map<String, String> errors);
        void onError(String message);
    }*/

  /*  public void micropost(final String id, final MicropostCallBack callBack){
        String url="http://192.168.1.16/Appli_PPE/wall.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            public void onResponse(String response) {

            *//*    try {
                    JSONObject json = new JSONObject(response);
                    Boolean error = json.getBoolean("error");

                    if (!error){
                        String user_id=json.getString("user_id");
                        String pseudo= json.getString("pseudo");
                        String email= json.getString("email");
                        String avatar= json.getString("avatar");
                        String m_id= json.getString("m_id");
                        String contenu= json.getString("contenu");
                        String like_count= json.getString("like_count");
                        String created_at= json.getString("created_at");
                        callBack.onSuccess(user_id, pseudo, email, avatar, m_id, contenu, like_count, created_at);
                    }else {
                        callBack.onError(json.getString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }*//*
                try {
                    JSONArray jsonArray= new JSONArray(response);
                    for (int i= 0; i<jsonArray.length(); i++){

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NetworkError){
                    callBack.onError("Impossible de se connecter");
                }else if (error instanceof VolleyError){
                    callBack.onError("Une erreur s'est produite");
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String>map= new HashMap<>();
                map.put("id", id);
                return map;
            }
        };

        queue.add(request);
    }

    public interface MicropostCallBack{
        void onSuccess(String user_id, String pseudo, String email, String avatar, String m_id, String contenu, String like_count, String created_at);
        void onError(String message);
    }*/
}
