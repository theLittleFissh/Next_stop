package com.example.nextstop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.nextstop.adapters.BookingAdapter;
import com.example.nextstop.adapters.HomeAdapter;
import com.example.nextstop.models.BookingInfo;
import com.example.nextstop.models.HomeModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class agent_order_list extends AppCompatActivity {
    RecyclerView recyclerView;
    BookingAdapter bookingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_order_list);


        recyclerView =(RecyclerView) findViewById(R.id.order_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<BookingInfo> options =
                new FirebaseRecyclerOptions.Builder<BookingInfo>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("BookingInfo"),BookingInfo.class)
                        .build();

        bookingAdapter=new BookingAdapter(options);
        recyclerView.setAdapter(bookingAdapter);
    }

    public agent_order_list() {
        super();
    }

    @Override
    protected void onStart() {
        super.onStart();
        bookingAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
       bookingAdapter.startListening();
    }


}