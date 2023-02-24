package com.example.nextstop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class agent_order_hostels extends AppCompatActivity {

    CardView hostelbooking,foodOrder;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_order_hostels);


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
                Intent intent=new Intent(agent_order_hostels.this,Payment_Proces.class);
                startActivity(intent);
                finish();
            }
        });

    }
}