package com.nkdroid.firstaid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class Tip1Activity extends ActionBarActivity {
    private Toolbar toolbar;
    private TextView txtBtnTip,descTitleHeart,txtDescHeart;
    private ImageView imgTipOne;
    String title,description;
    int imagePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip1);


        Intent i=getIntent();
        title=i.getStringExtra("title");
        description=i.getStringExtra("description");
        imagePath=i.getIntExtra("image", 0);
        txtBtnTip = (TextView) findViewById(R.id.txtBtnTip);
        descTitleHeart = (TextView) findViewById(R.id.descTitleHeart);
        txtDescHeart = (TextView) findViewById(R.id.txtDescHeart);
        imgTipOne= (ImageView) findViewById(R.id.imgTipOne);
        Glide.with(Tip1Activity.this)
                .load(imagePath)
                .centerCrop()
                .into(imgTipOne);
        descTitleHeart.setText(title);
        txtDescHeart.setText(description);
        txtBtnTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Tip1Activity.this,descTipOne.class);
                startActivity(intent2);
            }
        });

        setToolbar();
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("Heart Attack");
            setSupportActionBar(toolbar);
        }
    }

}
