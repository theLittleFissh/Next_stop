package com.example.nextstop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class UserDetails_show extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details_show);
        ImageView img = findViewById(R.id.details_img);
        TextView Name = findViewById(R.id.details_Name);
        TextView Room = findViewById(R.id.details_Room);
//        TextView sfa = findViewById(R.id.fetchAofSrudy);
//        TextView  Email = findViewById(R.id.festhEmail);
        TextView  Mobile = findViewById(R.id.details_mobile);
        TextView Address = findViewById(R.id.details_Address);

        String name = getIntent().getExtras().getString("name","defaultKey");
        String room = getIntent().getExtras().getString("rooms","defaultKey");
//        String aera_of_study = getIntent().getExtras().getString("area_of_study","defaultKey");
//        String email = getIntent().getExtras().getString("email","defaultKey");
        String mobile = getIntent().getExtras().getString("mobile","defaultKey");
        String address = getIntent().getExtras().getString("address","defaultKey");
        String picurl = getIntent().getExtras().getString("pic","defaultKey");





        Name.setText(name);
        Room.setText(room);
//        sfa.setText(aera_of_study);
//        Email.setText(email);
        Mobile.setText(mobile);
        Address.setText(address);
        Glide.with(UserDetails_show.this).load(picurl).into(img);
    }
}