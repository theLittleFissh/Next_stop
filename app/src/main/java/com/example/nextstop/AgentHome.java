package com.example.nextstop;

import static android.text.TextUtils.isEmpty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class AgentHome extends AppCompatActivity {

    EditText apartment,mobileNumber,address,room,cost;
    Button submit;

    FirebaseDatabase firebaseDatabase;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_home);

        apartment = findViewById(R.id.apartName);
        mobileNumber = findViewById(R.id.contactNumber);
        address = findViewById(R.id.address);
        cost = findViewById(R.id.price);
        submit = findViewById(R.id.submitbtn);
        room=findViewById(R.id.roomavailable);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String App = apartment.getText().toString();
                String Mobile = mobileNumber.getText().toString();
                String Address = address.getText().toString();
                String Room = room.getText().toString();
                String Cost = cost.getText().toString();


                if (isEmpty(App)) {
                    apartment.setError("This Field Cannot be Empty");
                    apartment.requestFocus();
                } else if (isEmpty(Mobile)) {
                    mobileNumber.setError("This Field Cannot be Empty");
                    mobileNumber.requestFocus();
                } else if (isEmpty(Address)) {
                    address.setError("This Field Cannot be Empty");
                    address.requestFocus();
                } else if (isEmpty(Room)) {
                    room.setError("This Field Cannot be Empty");
                    room.requestFocus();
                } else if (isEmpty(Cost)) {
                    cost.setError("This Field Cannot be Empty");
                    cost.requestFocus();
                }
                else
                {
                    firebaseDatabase=FirebaseDatabase.getInstance();
                    DatabaseReference root=firebaseDatabase.getReference("User");
                    userHelper helper=new userHelper(App,Mobile,Address,Room,Cost);
                    root.child(App).setValue(helper);
                    Toast.makeText(AgentHome.this, "Succefully Inserted", Toast.LENGTH_SHORT).show();

                }


            }


        });
    }
    }