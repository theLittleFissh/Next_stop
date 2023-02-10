package com.example.nextstop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.nextstop.adapters.EditAdapter;
import com.example.nextstop.models.HomeModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Agent_Edit_Delete extends AppCompatActivity {

    RecyclerView recyclerView;
    EditAdapter editAdapter;
    ImageView back_button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_edit_delete);
        recyclerView = (RecyclerView) findViewById(R.id.rv2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //backButton er kaj
        back_button=findViewById(R.id.backButton);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //Back Button er kaj

        FirebaseRecyclerOptions<HomeModel> options =
                new FirebaseRecyclerOptions.Builder<HomeModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("User"), HomeModel.class)
                        .build();

        editAdapter = new EditAdapter(options);
        recyclerView.setAdapter(editAdapter);
    }

    public Agent_Edit_Delete(int contentLayoutId) {
        super(contentLayoutId);
    }

    @Override
    protected void onStart() {
        super.onStart();
        editAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        editAdapter.startListening();
    }

    public Agent_Edit_Delete() {
        super();
    }
}