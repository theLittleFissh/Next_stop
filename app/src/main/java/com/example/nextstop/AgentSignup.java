package com.example.nextstop;

import static android.text.TextUtils.isEmpty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class AgentSignup extends AppCompatActivity {

    EditText userName,userEmail,password,phoneNumber;
    Button reg;
    TextView login;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_signup);

        userName=findViewById(R.id.signname);
        userEmail=findViewById(R.id.signmail);
        password=findViewById(R.id.signpass);
        phoneNumber=findViewById(R.id.phone);

        reg=findViewById(R.id.registerbtn);
        login=findViewById(R.id.signupThekeLogin);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Fullname=userName.getText().toString();
                String Email=userEmail.getText().toString().trim();
                String Pass=password.getText().toString();
                String Phone=phoneNumber.getText().toString();

                if(isEmpty(Fullname))
                {
                    userName.setError("Cannot be empty");
                    userName.requestFocus();
                }
                else if (isEmpty(Email))
                {
                    userEmail.setError("Cannot be empty");
                    userEmail.requestFocus();
                }
                else if (isEmpty(Pass))
                {
                    password.setError("Cannot be empty");
                    password.requestFocus();
                }
                else if(isEmpty(Phone)) {
                    password.setError("Cannot be empty");
                    password.requestFocus();

                }
                else{

                    firebaseAuth.createUserWithEmailAndPassword(Email,Pass)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    firebaseFirestore.collection("Users").document(FirebaseAuth.getInstance().getUid())
                                            .set(new agentModel(Fullname,Email,Phone));

                                    Intent myintent= new Intent(AgentSignup.this,MainActivity.class);
                                    startActivity(myintent);
                                    finish();
                                    Toast.makeText(AgentSignup.this, "Registration Successful",Toast.LENGTH_LONG).show();



                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(AgentSignup.this, "Registration Failed",Toast.LENGTH_LONG).show();

                                }
                            });

                }


            }




        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent=new Intent(AgentSignup.this, MainActivity.class);
                startActivity(myintent);
                finish();
            }
        });
    }
}