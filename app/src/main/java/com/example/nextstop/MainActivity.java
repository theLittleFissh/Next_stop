package com.example.nextstop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView user,agent,signuplink;
    LinearLayout protom,ditio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=findViewById(R.id.loginas_user2);
        agent= findViewById(R.id.loginas_agent);
        protom=findViewById(R.id.protomlinear);
        ditio=findViewById(R.id.duinumberlinear);
        signuplink=findViewById(R.id.signuplink);

        //shuru hoise code ekan theke
        agent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            protom.setVisibility(View.GONE);
            ditio.setVisibility(View.VISIBLE);

            }
        });
        //sesh ek daf
        //ditio daf
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                protom.setVisibility(View.VISIBLE);
                ditio.setVisibility(View.GONE);
            }
        });

        signuplink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SignUp.class);
                startActivity(intent);
            }
        });



    }






}