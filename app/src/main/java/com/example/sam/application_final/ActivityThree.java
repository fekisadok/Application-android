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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.sam.application_final.myrequest.MyRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by User on 4/15/2017.
 */

public class ActivityThree extends AppCompatActivity {

    ArrayList<Friend> arrayList;
    ListView lv;
    private SessionManager sessionManager;
    private ProfilManager profilManager;
    private Handler handler;
    private RequestQueue queue;
    private MyRequest request;
    final String EXTRA_PSEUDO = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        /*TextView title = (TextView) findViewById(R.id.activityTitle3);
        title.setText("This is ActivityThree");*/

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_profil:
                        Intent intent0 = new Intent(ActivityThree.this, ActivityTwo.class);
                        startActivity(intent0);
                        break;

                    case R.id.ic_home:
                        Intent intent1 = new Intent(ActivityThree.this, ActivityOne.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_friend:

                        break;
                    case R.id.ic_search:
                        Intent intent2 = new Intent(ActivityThree.this, ActivitySeven.class);
                        startActivity(intent2);
                        break;
                    case R.id.ic_notification:
                        Intent intent3 = new Intent(ActivityThree.this, ActivitySix.class);
                        startActivity(intent3);
                        break;
                }


                return false;
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
        profilManager= new ProfilManager(this);
        handler= new Handler();
        if(sessionManager.isLogged()){
            String id = sessionManager.getId();
            String avatar_user = sessionManager.getAvatar();
        }else{
            Intent intent = new Intent(getApplicationContext(), ActivityTwo.class);
            startActivity(intent);
            finish();
        }
        final String id = sessionManager.getId();
        final String avatar_user = sessionManager.getAvatar();
        //Avatar_User = (ImageView) findViewById(R.id.Avatar_User);
        final TextView pseudo = (TextView) findViewById(R.id.Pseudo);

        //Picasso.with(getBaseContext()).load("http://192.168.1.16"+avatar_user).into(Avatar_User);
        lv = (ListView) findViewById(R.id.listFriends);
        /*btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.logout();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });*/

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ActivityThree.ReadJSON().execute("http://192.168.43.194/Appli_PPE/friends.php?id="+id);
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
                JSONArray jsonArray =  jsonObject.getJSONArray("friend");

                for(int i =0;i<jsonArray.length(); i++){
                    JSONObject FriendObject = jsonArray.getJSONObject(i);
                    arrayList.add(new Friend(
                            FriendObject.getString("id"),
                            FriendObject.getString("pseudo"),
                            FriendObject.getString("avatar")
                    ));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            CustomListViewFriend adapter = new CustomListViewFriend(
                    getApplicationContext(), R.layout.friend, arrayList
            );
            lv.setAdapter(adapter);
            //final TextView pseudo = (TextView) findViewById(R.id.Pseudo);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, final long id) {

                    final Friend friend= arrayList.get(position);
                    final String Pseudo=friend.getPseudo();
                    final String id_user = sessionManager.getId();
                    /*Snackbar.make(view, friend.getPseudo(), Snackbar.LENGTH_LONG)
                            .setAction("No action", null).show();*/

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            request.loadprofil(Pseudo, id_user, new MyRequest.LoadProfilCallBack() {
                                @Override
                                public void onSuccess( String pseudo, String id, String avatar, String nom, String prenom, String date_naissance, String ville, String pays, String relation)
                                {

                                    profilManager.LoadProfil(pseudo, id, avatar, nom, prenom, date_naissance, ville, pays,relation);
                                    Intent intent = new Intent(ActivityThree.this, ActivityFive.class);
                                    startActivity(intent);
                                }
                                @Override
                                public void onError(String message) {
                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    },500);

                }
            });
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
