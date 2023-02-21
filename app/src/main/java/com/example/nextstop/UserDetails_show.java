package com.example.nextstop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nextstop.models.BookingInfo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

public class UserDetails_show extends AppCompatActivity {

    Button bookingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details_show);
        ImageView img = findViewById(R.id.details_img);
        TextView Name = findViewById(R.id.details_Name);
        TextView Room = findViewById(R.id.details_Room);
        TextView Description=findViewById(R.id.details_des);
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
        String description = getIntent().getExtras().getString("description","defaultKey");





        Name.setText(name);
        Room.setText(room);
//        sfa.setText(aera_of_study);
//        Email.setText(email);
        Mobile.setText(mobile);
        Address.setText(address);
        Description.setText(description);
        Glide.with(UserDetails_show.this).load(picurl).into(img);

        //booking korar kaj


        bookingButton=findViewById(R.id.bookBtn);
        bookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                String uid = mAuth.getCurrentUser().getUid();

                db.collection("Users")
                        .document(uid)
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {
                                    String Email = documentSnapshot.getString("email");
                                    String Name = documentSnapshot.getString("fullname");
                                    String Phone = documentSnapshot.getString("phone");

                                    System.out.println("CheckT: "+Email);
                                    System.out.println("CheckP: "+Name);
                                    System.out.println("CheckR: "+Phone);


                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    DatabaseReference dbroot = database.getReference("BookingInfo");
                                    BookingInfo book = new BookingInfo(Email,Name,Phone);
                                    String key = dbroot.push().getKey();
                                    dbroot.child(key).setValue(book);

                                    finish();


                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Handle the error
                            }
                        });


            }
        });

        //booking strt end

    }
}