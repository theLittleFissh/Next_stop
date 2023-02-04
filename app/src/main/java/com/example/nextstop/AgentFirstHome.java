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

        hostelCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AgentFirstHome.this,AgentHome.class);
                startActivity(intent);
            }
        });



    }
}