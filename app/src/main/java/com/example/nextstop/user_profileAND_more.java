package com.example.nextstop;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class user_profileAND_more extends AppCompatActivity {
    LinearLayout foodbutton2,homebutton2;
    Button logoutUser;
    TextView emailname;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_and_more);

        foodbutton2=findViewById(R.id.foodfoodfood);
        homebutton2=findViewById(R.id.homehomehome);
        logoutUser=findViewById(R.id.user_logoutButton);
        emailname=findViewById(R.id.user_id_show);







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


        //logout er kaj
        logoutUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(user_profileAND_more.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}