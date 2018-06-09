package com.example.sam.application_final;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.sam.application_final.myrequest.MyRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


public class ActivityFive extends AppCompatActivity {

    private Button btn_logout;
    private SessionManager sessionManager;
    private ProfilManager profilManager;
    ArrayList<Micropost_profil_public> arrayList;
    ListView lv;
    //private Button btn_logout;
    //private Button btn_publish;
    private Button btn_friend;
    private Button btn_friend2;
    private RequestQueue queue;
    private MyRequest request;
    private ProfilTest profilTest;
    private String id_user;
    private String id_friend;
    private EditText til_micropost;
    ImageView Avatar_User;
    ImageView Avatar;
    private Handler handler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);

        /*TextView title = (TextView) findViewById(R.id.activityTitle2);
        title.setText("This is ActivityTwo");*/

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar_back);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_back:
                        ActivityFive.this.finish();
                        break;

                    case R.id.ic_home:
                        Intent intent1 = new Intent(ActivityFive.this, ActivityOne.class);
                        startActivity(intent1);
                        break;


                }


                return false;
            }
        });
       // final Intent intent1 = getIntent();
        //final String pseudo=intent1.getStringExtra(EXTRA_PSEUDO);
        //btn_logout= (Button) findViewById(R.id.btn_logout);
        sessionManager= new SessionManager(this);
        profilManager= new ProfilManager(this);

      /*  btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.logout();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });*/
        request= new MyRequest(this, queue);
        btn_friend= (Button) findViewById(R.id.btn_friend);
        //sessionManager= new SessionManager(this);

        btn_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(ActivityFive.this, ActivityFour.class);
                startActivity(intent3);
            }
        });
        arrayList = new ArrayList<>();
        //btn_publish = (Button) findViewById(R.id.btn_publish);
        //til_micropost= (TextInputLayout) findViewById(R.id.til_micropost);
        //til_micropost= (EditText) findViewById(R.id.publish_micropost);
        //btn_logout= (Button) findViewById(R.id.btn_logout);

        queue = VolleySingleton.getInstance(this).getRequestQueue();
        request= new MyRequest(this, queue);

        sessionManager= new SessionManager(this);
        if(sessionManager.isLogged()){
            String id = sessionManager.getId();
            String avatar_user = sessionManager.getAvatar();
        }else{
            Intent intent = new Intent(getApplicationContext(), ActivityFive.class);
            startActivity(intent);
            finish();
        }
        final String id = sessionManager.getId();
        Avatar = (ImageView) findViewById(R.id.Avatar);
        final String AvatarProfil=profilManager.getAvatarProfil();
        final String IdProfil=profilManager.getIdProfil();
        final String PseudoProfil=profilManager.getPseudoProfil();
        final String NomProfil=profilManager.getNomProfil();
        final String PrenomProfil=profilManager.getPrenomProfil();
        final String DateNaissanceProfil=profilManager.getDateNaissanceProfil();


        final String VilleProfil=profilManager.getVilleProfil();
        final String PaysProfil=profilManager.getPaysProfil();
        final String RelationProfil=profilManager.getRelationProfil();
        /*final String AvatarProfil=sessionManager.getAvatarProfil();
        final String IdProfil=sessionManager.getIdProfil();
        final String PseudoProfil=sessionManager.getPseudoProfil();
        final String NomProfil=sessionManager.getNomProfil();
        final String PrenomProfil=sessionManager.getPrenomProfil();
        final String DateNaissanceProfil=sessionManager.getDateNaissanceProfil();


        final String VilleProfil=sessionManager.getVilleProfil();
        final String PaysProfil=sessionManager.getPaysProfil();
        final String RelationProfil=sessionManager.getRelationProfil();*/
        Picasso.with(getBaseContext()).load("http://192.168.43.194"+AvatarProfil).into(Avatar);
        TextView Nom = (TextView) findViewById(R.id.nom);
        Nom.setText(NomProfil+' '+PrenomProfil);
        TextView Date_Naissance = (TextView) findViewById(R.id.date_naissance);
        Date_Naissance.setText("Date de naissance : "+DateNaissanceProfil);
        TextView Ville = (TextView) findViewById(R.id.ville);
        if (!VilleProfil.equals("null")){
            Ville.setText("Habite à : "+VilleProfil);
        }else{
            Ville.setText("Habite à : A dénifir");
        }

        String Relation= RelationProfil;
        btn_friend = (Button) findViewById(R.id.btn_friend);
        btn_friend2 = (Button) findViewById(R.id.btn_friend2);
        if (Relation.equals("cancel_link")){
            btn_friend.setText("Annuler la demande");
            btn_friend2.setVisibility(View.GONE);
        }else if (Relation.equals("delete_link")){
            btn_friend.setText("Retirer de ma liste d'amis");
            btn_friend2.setVisibility(View.GONE);
        }else if (Relation.equals("add_link")){
            btn_friend.setText("Ajouter comme ami");
            btn_friend2.setVisibility(View.GONE);
        }else {
            btn_friend.setText("Accepter");
            btn_friend2.setText("Refuser");
            btn_friend2.setVisibility(View.VISIBLE);

        }

        final String avatar_user = sessionManager.getAvatar();
        /*final String nom = sessionManager.getNom();
        final String prenom = sessionManager.getPrenom();
        final String date_naissance = sessionManager.getDateNaissance();
        final String ville = sessionManager.getVille();*/
        Avatar_User = (ImageView) findViewById(R.id.Avatar_User);
        handler= new Handler();
        btn_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String action = btn_friend.getText().toString().trim();
                if(action.equals("Ajouter comme ami")) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            request.Add_Friend(id, IdProfil, new MyRequest.Add_FriendCallBack() {
                                @Override
                                public void onSuccess(String message) {
                                    btn_friend.setText("Annuler la demande");
                                    /*Intent intent = new Intent(getApplicationContext(), ActivityFive.class);
                                    intent.putExtra("SEND", message);
                                    startActivity(intent);
                                    finish();*/
                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onError(String message) {

                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    },500);
                }else if (action.equals("Retirer de ma liste d'amis")){
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            request.Delete_Friend(id, IdProfil, new MyRequest.Delete_FriendCallBack() {
                                @Override
                                public void onSuccess(String message) {
                                    btn_friend.setText("Ajouter comme ami");
                                   /* Intent intent = new Intent(getApplicationContext(), ActivityFive.class);
                                    intent.putExtra("SEND", message);
                                    startActivity(intent);
                                    finish();*/
                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onError(String message) {

                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    },500);
                }else if (action.equals("Annuler la demande")){
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                        request.Delete_Friend(id, IdProfil, new MyRequest.Delete_FriendCallBack() {
                            @Override
                            public void onSuccess(String message) {
                                btn_friend.setText("Ajouter comme ami");
                               /* Intent intent = new Intent(getApplicationContext(), ActivityFive.class);
                                intent.putExtra("SEND", message);
                                startActivity(intent);
                                finish();*/
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(String message) {

                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                },500);
                }else{
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            request.Accept_Friend(id, IdProfil, new MyRequest.Accept_FriendCallBack() {
                                @Override
                                public void onSuccess(String message) {
                                    btn_friend.setText("Retirer de ma liste d'amis");
                                    btn_friend2.setVisibility(View.GONE);
                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onError(String message) {

                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    },500);
                }
                //((EditText) findViewById(R.id.publish_micropost)).setText("");
            }
        });
        btn_friend2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  handler.postDelayed(new Runnable() {
                      @Override
                      public void run() {
                          request.Delete_Friend(id, IdProfil, new MyRequest.Delete_FriendCallBack() {
                              @Override
                              public void onSuccess(String message) {
                                  btn_friend.setText("Ajouter comme ami");
                                  btn_friend2.setVisibility(View.GONE);
                                   /* Intent intent = new Intent(getApplicationContext(), ActivityFive.class);
                                    intent.putExtra("SEND", message);
                                    startActivity(intent);
                                    finish();*/
                                  Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                              }

                              @Override
                              public void onError(String message) {

                                  Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                              }
                          });
                      }
                  }, 500);
                //((EditText) findViewById(R.id.publish_micropost)).setText("");
            }
        });
        //Picasso.with(getBaseContext()).load("http://172.30.120.8"+avatar_user).into(Avatar_User);

        lv = (ListView) findViewById(R.id.listMicroposts);


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ActivityFive.ReadJSON().execute("http://192.168.43.194/Appli_PPE/profil_friend.php?pseudo="+PseudoProfil+"&id="+id);
            }
        });


    }

    class ReadJSON extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            return readURL(params[0]);
        }

        @Override
        protected void onPostExecute(String content) {
           try {
                JSONObject jsonObject = new JSONObject(content);
                JSONArray jsonArray =  jsonObject.getJSONArray("micropost");

              /*  for(int i =0;i<jsonArray.length(); i++){
                    JSONObject MicropostObjectPublic = jsonArray.getJSONObject(i);
                    Picasso.with(getBaseContext()).load("http://192.168.43.194"+MicropostObjectPublic.getString("avatar")).into(Avatar);
                    TextView Nom = (TextView) findViewById(R.id.nom);
                    String ville = MicropostObjectPublic.getString("ville");
                    Nom.setText(MicropostObjectPublic.getString("prenom")+' '+MicropostObjectPublic.getString("nom"));
                    //String ville = MicropostObjectPublic.getString("ville");
                    String id_friend=MicropostObjectPublic.getString("id");
                    TextView Date_Naissance = (TextView) findViewById(R.id.date_naissance);
                    Date_Naissance.setText("Date de naissance : "+MicropostObjectPublic.getString("date_naissance"));
                    TextView Ville = (TextView) findViewById(R.id.ville);
                    if (!MicropostObjectPublic.getString("ville").equals("null")){
                        Ville.setText("Habite à : "+MicropostObjectPublic.getString("ville"));
                    }else{
                        Ville.setText("Habite à : A dénifir");
                    }

                    String Relation= MicropostObjectPublic.getString("relation");
                    btn_friend = (Button) findViewById(R.id.btn_friend);
                    if (Relation.equals("cancel_link")){
                        btn_friend.setText("Annuler la demande");
                    }else if (Relation.equals("delete_link")){
                        btn_friend.setText("Retirer de ma liste d'amis");
                    }else if (Relation.equals("add_link")){
                        btn_friend.setText("Ajouter comme ami");
                    }else {
                        btn_friend.setText("test");
                    }
                }*/

               for(int i =0;i<jsonArray.length(); i++){
                   JSONObject MicropostObjectPublic = jsonArray.getJSONObject(i);

                         arrayList.add(new Micropost_profil_public(
                                 MicropostObjectPublic.getString("avatar"),
                                 MicropostObjectPublic.getString("pseudo"),
                                 MicropostObjectPublic.getString("nom"),
                                 MicropostObjectPublic.getString("prenom"),
                                 MicropostObjectPublic.getString("date_naissance"),
                                 MicropostObjectPublic.getString("ville"),
                                 MicropostObjectPublic.getString("pays"),
                                 MicropostObjectPublic.getString("contenu"),
                                 MicropostObjectPublic.getString("created_at")
                         ));

               }
            } catch (JSONException e) {
                e.printStackTrace();
            }
           /*  try {
                JSONObject jsonObject = new JSONObject(content);
                JSONArray jsonArray =  jsonObject.getJSONArray("friend");

                JSONObject FriendObjectPublic = jsonArray.getJSONObject(0);
                String Relation= FriendObjectPublic.getString("relation");
                btn_friend = (Button) findViewById(R.id.btn_friend);
                if (Relation.equals("cancel_link")){
                    btn_friend.setText("Annuler la demande");
                }else if (Relation.equals("delete_link")){
                    btn_friend.setText("Retirer de ma liste d'amis");
                }else if (Relation.equals("add_link")){
                    btn_friend.setText("Ajouter comme ami");
                }else {
                    btn_friend.setText("test");
                }
               for(int i =0;i<jsonArray.length(); i++){
                    JSONObject FriendObjectPublic = jsonArray.getJSONObject(i);
                    String Relation= FriendObjectPublic.getString("relation");
                    btn_friend = (Button) findViewById(R.id.btn_friend);
                    if (Relation.equals("cancel_link")){
                        btn_friend.setText("Annuler la demande");
                    }else if (Relation.equals("delete_link")){
                        btn_friend.setText("Retirer de ma liste d'amis");
                    }else if (Relation.equals("add_link")){
                        btn_friend.setText("Ajouter comme ami");
                    }else {
                        btn_friend.setText("test");
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }*/

            //Picasso.with(getBaseContext()).load("http://172.30.120.8"+micropost_profil_public.getAvatar()).into(Avatar);
            CustomListViewProfilPublic adapter = new CustomListViewProfilPublic(
                    getApplicationContext(), R.layout.micropost, arrayList
            );
            lv.setAdapter(adapter);

        }
    }


    private static String readURL(String theUrl) {
        StringBuilder content = new StringBuilder();
        try {
            // create a url object
            URL url = new URL(theUrl);
            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();
            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
