package com.example.sam.application_final;

import android.content.Intent;
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
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.sam.application_final.myrequest.MyRequest;

import java.util.Map;

/**
 * Created by User on 4/15/2017.
 */

public class ActivityFour extends AppCompatActivity {

    private SessionManager sessionManager;
    private Button btn_update;
    private RequestQueue queue;
    private MyRequest request;
    private EditText til_new_pseudo;
    private EditText til_new_email;
    private EditText til_new_email_confirm;
    private EditText til_old_password;
    private EditText til_new_password;
    private EditText til_new_password_confirm;
    private EditText til_new_ville;
    private EditText til_new_pays;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);

        /*TextView title = (TextView) findViewById(R.id.activityTitle3);
        title.setText("This is ActivityThree");*/

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
                        ActivityFour.this.finish();
                        break;

                    case R.id.ic_home:
                        Intent intent1 = new Intent(ActivityFour.this, ActivityOne.class);
                        startActivity(intent1);
                        break;
                }


                return false;
            }
        });
        sessionManager= new SessionManager(this);
        if(sessionManager.isLogged()){
            String id = sessionManager.getId();
            String avatar_user = sessionManager.getAvatar();
        }
        final String id = sessionManager.getId();
        btn_update = (Button) findViewById(R.id.btn_update);
        queue = VolleySingleton.getInstance(this).getRequestQueue();
        request= new MyRequest(this, queue);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String new_pseudo = til_new_pseudo.getText().toString().trim();
                final String new_ville = til_new_ville.getText().toString().trim();
                final String new_pays = til_new_pays.getText().toString().trim();
                final String new_email  = til_new_email.getText().toString().trim();
                final String new_email_confirm  = til_new_email_confirm.getText().toString().trim();
                final String old_password  = til_old_password.getText().toString().trim();
                final String new_password = til_new_password.getText().toString().trim();
                final String new_password_confirm  = til_new_password_confirm.getText().toString().trim();
                if(new_pseudo.length() > 0 || new_email.length() > 0 || new_email_confirm.length() > 0|| old_password.length() > 0
                        || new_password.length() > 0 || new_password_confirm.length() > 0 || new_ville.length() > 0 || new_pays.length() > 0) {

                    request.update(id, new_pseudo, new_ville, new_pays, new_email, new_email_confirm, old_password, new_password, new_password_confirm, new MyRequest.UpdateCallBack() {
                        @Override
                        public void onSuccess(String message) {
                            Intent intent = new Intent(getApplicationContext(), ActivityTwo.class);
                            intent.putExtra("SEND", message);
                            startActivity(intent);
                            finish();
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void inputErrors(Map<String, String> errors) {
                            if (errors.get("pseudo") != null) {
                                til_new_pseudo.setError(errors.get("pseudo"));
                            }
                            if (errors.get("email") != null) {
                                til_new_email.setError(errors.get("email"));
                            }
                            if (errors.get("password") != null) {
                                til_new_password.setError(errors.get("password"));
                            }
                        }
                        @Override
                        public void onError(String message) {

                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                        }
                    });

                }else {
                    Toast.makeText(getApplicationContext(), "Au moins un champ doit être rempli", Toast.LENGTH_SHORT).show();
                }
                //((EditText) findViewById(R.id.publish_micropost)).setText("");
            }
        });
    }
}
