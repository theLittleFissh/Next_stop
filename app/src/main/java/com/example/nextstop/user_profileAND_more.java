package com.example.nextstop;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class user_profileAND_more extends AppCompatActivity {
    LinearLayout foodbutton2,homebutton2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_and_more);

        foodbutton2=findViewById(R.id.foodfoodfood);
        homebutton2=findViewById(R.id.homehomehome);

        foodbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(user_profileAND_more.this,user_food.class);
                startActivity(intent);
                finish();
            }
        });

        homebutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(user_profileAND_more.this,Home.class);
                startActivity(intent);
                finish();
            }
        });


    }
}