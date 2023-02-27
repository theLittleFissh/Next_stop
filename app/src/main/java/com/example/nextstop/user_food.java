package com.example.nextstop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.nextstop.models.BookingInfo;
import com.example.nextstop.models.FoodOrderInfo;
import com.example.nextstop.models.adapters.FoodAdapter;
import com.example.nextstop.models.FoodModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

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


//
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