package com.example.nextstop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AgentFirstHome extends AppCompatActivity {
    CardView hostelCard,foodCard,orderCard,profileCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_first_home);

        hostelCard=findViewById(R.id.hostel_card);
        profileCard=findViewById(R.id.Profile_Card);
        orderCard=findViewById(R.id.Order_Card);
        foodCard=findViewById(R.id.Food_Card);

        hostelCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AgentFirstHome.this,AgentHome.class);
                startActivity(intent);
            }
        });

        profileCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AgentFirstHome.this,Agent_Edit_Delete.class);
                startActivity(intent);
                finish();
            }
        });

        orderCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AgentFirstHome.this,agent_order_hostels.class);
                startActivity(intent);
                finish();
            }
        });

        foodCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(AgentFirstHome.this,Agent_food_input.class);
                startActivity(intent);
                finish();

            }
        });






    }
}