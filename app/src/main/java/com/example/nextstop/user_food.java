package com.example.nextstop;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class user_food extends AppCompatActivity {

    LinearLayout foodbutton1,morebutton1,homebutton1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_food);


        morebutton1=findViewById(R.id.usermoremore);
        homebutton1=findViewById(R.id.userhomehome);

        morebutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(user_food.this,user_profileAND_more.class);
                startActivity(intent);
            }
        });

        homebutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(user_food.this,Home.class);
                startActivity(intent);
            }
        });



    }
}