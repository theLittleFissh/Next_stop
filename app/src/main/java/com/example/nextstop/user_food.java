package com.example.nextstop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.nextstop.adapters.FoodAdapter;
import com.example.nextstop.adapters.HomeAdapter;
import com.example.nextstop.models.FoodModel;
import com.example.nextstop.models.HomeModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class user_food extends AppCompatActivity {

    LinearLayout foodbutton1,morebutton1,homebutton1;
    RecyclerView recyclerView;
    FoodAdapter foodAdapter;
    Button order;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_food);
        order=findViewById(R.id.user_food_order);



        //switching er kaj
        morebutton1=findViewById(R.id.usermoremore);
        homebutton1=findViewById(R.id.userhomehome);

        morebutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(user_food.this,user_profileAND_more.class);
                startActivity(intent);
                finish();
            }
        });

        homebutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(user_food.this,Home.class);
                startActivity(intent);
                finish();
            }
        });
        //switching er kaj sesh

        recyclerView =(RecyclerView) findViewById(R.id.food_RV3);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<FoodModel> options =
                new FirebaseRecyclerOptions.Builder<FoodModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("FoodInfo"),FoodModel.class)
                        .build();

        foodAdapter=new FoodAdapter(options);

        recyclerView.setAdapter(foodAdapter);



    }
    public user_food() {
        super();
    }

    @Override
    protected void onStart() {
        super.onStart();
        foodAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        foodAdapter.startListening();
    }



}