package com.nkdroid.firstaid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;


public class HomeActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private Button btnLogout;
    private GoogleCloudMessaging gcm;
    private String regid;
    private LinearLayout thirdItem, forthItem,sixthItem,fifthItem,secondItem;
    private String PROJECT_NUMBER = "92884720384";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        thirdItem= (LinearLayout) findViewById(R.id.thirdItem);
        thirdItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SymptomsActivity.class);
                startActivity(intent);
            }
        });

        forthItem= (LinearLayout) findViewById(R.id.forthItem);
        forthItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, DisasterActivity.class);
                startActivity(intent);
            }
        });

        secondItem= (LinearLayout) findViewById(R.id.secondItem);
        secondItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, TipsActivity.class);
                startActivity(intent);
            }
        });

        sixthItem = (LinearLayout) findViewById(R.id.sixthItem);
        sixthItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(HomeActivity.this, AnnouncementActivity.class);
                startActivity(intent1);
            }
        });

        fifthItem = (LinearLayout) findViewById(R.id.fifthItem);
        fifthItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(HomeActivity.this, HospitalActivity.class);
                startActivity(intent2);
            }
        });

        setToolbar();
        initView();
        getRegId();

    }

    private void initView() {
        btnLogout= (Button) findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //remove sharedpreference
                PrefUtils.clearLogin(HomeActivity.this);
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("24*7 First Aid");
            setSupportActionBar(toolbar);
        }
    }

    public void getRegId(){

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(HomeActivity.this);
                    }
                    regid = gcm.register(PROJECT_NUMBER);
                    Log.e("GCM ID :", regid);

                    if(regid==null || regid==""){
                        AlertDialog.Builder alert = new AlertDialog.Builder(HomeActivity.this);
                        alert.setTitle("Error");
                        alert.setMessage("Internal Server Error");
                        alert.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getRegId();
                                dialog.dismiss();
                            }
                        });
                        alert.setNegativeButton("exit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                finish();
                            }
                        });
                        alert.show();
                    } else {
                        // Store GCM ID in sharedpreference
                        SharedPreferences sharedPreferences=getSharedPreferences("GCM",MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("GCM_ID",regid);
                        editor.commit();

                        // TODO further logic

                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

            }
        }.execute();

    } // end of getRegId
}
