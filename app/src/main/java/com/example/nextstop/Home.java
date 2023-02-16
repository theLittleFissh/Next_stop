package com.example.nextstop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.nextstop.adapters.HomeAdapter;
import com.example.nextstop.models.HomeModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Home extends AppCompatActivity {
    RecyclerView recyclerView;
    HomeAdapter homeAdapter;
    LinearLayout foodButton,moreButton,homebutton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        foodButton=findViewById(R.id.userfoodbutton);
        moreButton=findViewById(R.id.usermorebutton);
        homebutton=findViewById(R.id.userhomebutton);

        //switching er kaj

        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Home.this,user_food.class);
                startActivity(intent);
                finish();
            }
        });

        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Home.this,user_profileAND_more.class);
                startActivity(intent);
                finish();
            }
        });

        //swithcing er kaj sesh


        recyclerView =(RecyclerView) findViewById(R.id.rv1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<HomeModel> options =
                new FirebaseRecyclerOptions.Builder<HomeModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("User"),HomeModel.class)
                        .build();

        homeAdapter=new HomeAdapter(options);

        recyclerView.setAdapter(homeAdapter);
    }

    public Home() {
        super();
    }

    @Override
    protected void onStart() {
        super.onStart();
        homeAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        homeAdapter.startListening();
    }
}