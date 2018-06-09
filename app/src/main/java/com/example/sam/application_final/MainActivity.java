package com.example.sam.application_final;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.sam.application_final.myrequest.MyRequest;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;
    private Button btn_send;
    private TextInputLayout til_identifiant_log, til_password_log;
    private ProgressBar pb_loader;
    private RequestQueue queue;
    private MyRequest request;
    private Handler handler;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        tabLayout.getTabAt(0).setIcon(R.drawable.ic_assignment);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_autorenew);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_attach_file);*/

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar_first);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_login:

                        break;

                    case R.id.ic_register:
                        Intent intent1 = new Intent(MainActivity.this, Register.class);
                        startActivity(intent1);
                        break;

                    /*case R.id.ic_books:
                        Intent intent2 = new Intent(MainActivity.this, ActivityTwo.class);
                        startActivity(intent2);
                        break;

                    case R.id.ic_center_focus:
                        Intent intent3 = new Intent(MainActivity.this, ActivityThree.class);
                        startActivity(intent3);
                        break;

                    case R.id.ic_backup:
                        Intent intent4 = new Intent(MainActivity.this, Register.class);
                        startActivity(intent4);
                        break;*/
                }


                return false;
            }
        });

        sessionManager= new SessionManager(MainActivity.this);
        if(sessionManager.isLogged()){
            Intent intent = new Intent(MainActivity.this, ActivityOne.class);
            startActivity(intent);
            finish();
        }

        Intent intent = getIntent();
        if (intent.hasExtra("REGISTER")){
            Toast.makeText(this, intent.getStringExtra("REGISTER"), Toast.LENGTH_LONG).show();
        }

        btn_send = (Button) findViewById(R.id.btn_send);
        pb_loader = (ProgressBar) findViewById(R.id.pb_loader);
        til_identifiant_log = (TextInputLayout) findViewById(R.id.til_identifiant_log);
        til_password_log = (TextInputLayout) findViewById(R.id.til_password_log);

        queue = VolleySingleton.getInstance(this).getRequestQueue();
        request= new MyRequest(this, queue);
        handler= new Handler();
        sessionManager = new SessionManager(this);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pb_loader.setVisibility(View.VISIBLE);
                final String identifiant = til_identifiant_log.getEditText().getText().toString().trim();
                final String password = til_password_log.getEditText().getText().toString().trim();
                if(identifiant.length() > 0 && password.length() > 0) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            request.login(identifiant, password, new MyRequest.LoginCallBack() {
                                @Override
                                public void onSuccess(String id, String pseudo, String avatar, String email, String nom, String prenom, String date_naissance, String ville, String pays) {
                                    pb_loader.setVisibility(View.GONE);
                                    sessionManager.insertUser(id, pseudo,avatar,email, nom, prenom, date_naissance, ville, pays);
                                    Intent intent = new Intent(getApplicationContext(), ActivityOne.class);
                                    startActivity(intent);
                                    finish();
                                }

                                @Override
                                public void onError(String message) {
                                    pb_loader.setVisibility(View.GONE);
                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    },1000);

                }else {
                    Toast.makeText(getApplicationContext(), "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

  /*  private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment());
        adapter.addFragment(new Tab2Fragment());
        adapter.addFragment(new Tab3Fragment());
        viewPager.setAdapter(adapter);
    }*/

}
