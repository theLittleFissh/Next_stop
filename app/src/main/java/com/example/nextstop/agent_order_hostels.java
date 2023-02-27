package com.example.nextstop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class agent_order_hostels extends AppCompatActivity {

    CardView hostelbooking,foodOrder;
    ImageView backbutton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_order_hostels);

        //backbutton er kaj
        backbutton=findViewById(R.id.backButton1);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(agent_order_hostels.this,AgentFirstHome.class);
                startActivity(intent);
                finish();
            }
        });
        //backbutton er kaj sesh


        foodOrder=findViewById(R.id.food_order_cardclick);
        hostelbooking=findViewById(R.id.hostel_order_cardclick);
        hostelbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(agent_order_hostels.this,agent_order_list.class);
                startActivity(intent);
                finish();
            }
        });

        foodOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(agent_order_hostels.this,agentFoodOrderlist.class);
                startActivity(intent);
                finish();
            }
        });



    }
}