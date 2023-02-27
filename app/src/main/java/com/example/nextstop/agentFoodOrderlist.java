package com.example.nextstop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.nextstop.models.BookingInfo;
import com.example.nextstop.models.FoodOrderInfo;
import com.example.nextstop.models.adapters.BookingAdapter;
import com.example.nextstop.models.adapters.FoodOrderAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class agentFoodOrderlist extends AppCompatActivity {
    RecyclerView recyclerView;
    FoodOrderAdapter foodOrderAdapter;
    ImageView backbutton;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_food_orderlist);

        //backbutton er kaj
        backbutton=findViewById(R.id.backButton1);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(agentFoodOrderlist.this,agent_order_hostels.class);
                startActivity(intent);
                finish();
            }
        });
        //backbutton er kaj sesh

        recyclerView = (RecyclerView) findViewById(R.id.food_order_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<FoodOrderInfo> options =
                new FirebaseRecyclerOptions.Builder<FoodOrderInfo>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("FoodBookingInfo"),FoodOrderInfo.class)
                        .build();

        foodOrderAdapter = new FoodOrderAdapter(options);
        recyclerView.setAdapter(foodOrderAdapter);

    }

    public agentFoodOrderlist() {
        super();
    }

    @Override
    protected void onStart() {
        super.onStart();
        foodOrderAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        foodOrderAdapter.startListening();
    }


}