package com.example.nextstop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    TextView user,agent,signuplink;
    LinearLayout protom,ditio;
    TextInputEditText email,password;
    Button login;

    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=findViewById(R.id.loginas_user2);
        agent= findViewById(R.id.loginas_agent);
        protom=findViewById(R.id.protomlinear);
        ditio=findViewById(R.id.duinumberlinear);
        signuplink=findViewById(R.id.signuplink);

        //ekane declare korteci

        password=(TextInputEditText) findViewById(R.id.password1);
        email=(TextInputEditText) findViewById(R.id.email1);
        login=(Button) findViewById(R.id.sign_in);

        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog= new ProgressDialog(this);




        //
        //shuru hoise code ekan theke
        agent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            protom.setVisibility(View.GONE);
            ditio.setVisibility(View.VISIBLE);

            }
        });
        //sesh ek daf
        //ditio daf
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                protom.setVisibility(View.VISIBLE);
                ditio.setVisibility(View.GONE);
            }
        });

        signuplink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SignUp.class);
                startActivity(intent);
            }
        });


        //login button click korle ja hobe
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.show();

                String mail=email.getText().toString().trim();
                String pass=password.getText().toString();

                firebaseAuth.signInWithEmailAndPassword(mail,pass)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                progressDialog.cancel();
                                Toast.makeText(MainActivity.this, "Login Succesful",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.cancel();
                                Toast.makeText(MainActivity.this, "Login Failed",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });



    }






}