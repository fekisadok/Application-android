package com.example.sam.application_final;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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


public class ActivityOne extends AppCompatActivity {

    ArrayList<Micropost> arrayList;
    ListView lv;
    private SessionManager sessionManager;
    //private Button btn_logout;
    private Button btn_publish;
    private RequestQueue queue;
    private MyRequest request;
    //private TextInputLayout til_micropost;
    private EditText til_micropost;
    ImageView Avatar_User;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

       /* TextView title = (TextView) findViewById(R.id.activityTitle1);
        title.setText("This is ActivityOne");*/

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_profil:
                        Intent intent0 = new Intent(ActivityOne.this, ActivityTwo.class);
                        startActivity(intent0);
                        break;

                    case R.id.ic_home:

                        break;

                    case R.id.ic_friend:
                        Intent intent1 = new Intent(ActivityOne.this, ActivityThree.class);
                        startActivity(intent1);
                        break;
                    case R.id.ic_search:
                        Intent intent2 = new Intent(ActivityOne.this, ActivitySeven.class);
                        startActivity(intent2);
                        break;
                    case R.id.ic_notification:
                        Intent intent3 = new Intent(ActivityOne.this, ActivitySix.class);
                        startActivity(intent3);
                        break;
                }


                return false;
            }
        });
        arrayList = new ArrayList<>();
        btn_publish = (Button) findViewById(R.id.btn_publish);
        //til_micropost= (TextInputLayout) findViewById(R.id.til_micropost);
        til_micropost= (EditText) findViewById(R.id.publish_micropost);
        //btn_logout= (Button) findViewById(R.id.btn_logout);

        queue = VolleySingleton.getInstance(this).getRequestQueue();
        request= new MyRequest(this, queue);

        sessionManager= new SessionManager(this);
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
        Avatar_User = (ImageView) findViewById(R.id.Avatar_User);

        Picasso.with(getBaseContext()).load("http://192.168.43.194"+avatar_user).into(Avatar_User);
        lv = (ListView) findViewById(R.id.listMicroposts);
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
                new ReadJSON().execute("http://192.168.43.194/Appli_PPE/wall.php?id="+id);
            }
        });
        btn_publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String micropost_send = til_micropost.getText().toString().trim();
                if(micropost_send.length() > 0) {

                    request.micropost(id, micropost_send, new MyRequest.MicropostCallBack() {
                        @Override
                        public void onSuccess(String message) {
                            Intent intent = new Intent(getApplicationContext(), ActivityOne.class);
                            intent.putExtra("SEND", message);
                            startActivity(intent);
                            finish();
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(String message) {

                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                        }
                    });

                }else {
                    Toast.makeText(getApplicationContext(), "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                }
                ((EditText) findViewById(R.id.publish_micropost)).setText("");
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

                for(int i =0;i<jsonArray.length(); i++){
                    JSONObject MicropostObject = jsonArray.getJSONObject(i);
                    arrayList.add(new Micropost(
                            MicropostObject.getString("avatar"),
                            MicropostObject.getString("pseudo"),
                            MicropostObject.getString("contenu"),
                            MicropostObject.getString("created_at")
                    ));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            CustomListView adapter = new CustomListView(
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
