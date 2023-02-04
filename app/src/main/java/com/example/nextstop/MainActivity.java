package com.example.nextstop;

import static android.text.TextUtils.isEmpty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    TextView user,agent,usersignuplink,agentErsignup,agentforgetpassword,userforgetpassword;
    LinearLayout protom,ditio,userlay,agentlay;
    TextInputEditText email,password,emailA,passwordA;
    Button login,loginA;

    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=findViewById(R.id.loginas_user2);
        agent= findViewById(R.id.loginas_agent);
        protom=findViewById(R.id.protomlinear);
        ditio=findViewById(R.id.duinumberlinear);
        usersignuplink=findViewById(R.id.signuplink);
        agentErsignup=findViewById(R.id.agentersignup);
        userlay=findViewById(R.id.userlayout);
        agentlay=findViewById(R.id.agent_ersignupid);



        //ekane declare korteci

        password=(TextInputEditText) findViewById(R.id.password1);
        email=(TextInputEditText) findViewById(R.id.email1);
        login=(Button) findViewById(R.id.sign_in);
        emailA=findViewById(R.id.email2);
        passwordA=findViewById(R.id.password2);
        loginA=findViewById(R.id.sign_in2);
        agentforgetpassword=findViewById(R.id.agentForgetPassword);
        userforgetpassword=findViewById(R.id.userForgetPassword);

        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog= new ProgressDialog(this);//loading sign




        //
        //shuru hoise code ekan theke
        agent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            protom.setVisibility(View.GONE);
            ditio.setVisibility(View.VISIBLE);
             emailA.setText("");
             passwordA.setText("");

            }
        });



        //sesh ek daf
        //ditio daf
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                protom.setVisibility(View.VISIBLE);
                ditio.setVisibility(View.GONE);
                email.setText("");
                password.setText("");

            }
        });

        usersignuplink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SignUp.class);
                startActivity(intent);


            }
        });

        agentErsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Agent_signup.class);
                startActivity(intent);
            }
        });










            //login button click korle ja hobe
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    progressDialog.show();

                    String mail = email.getText().toString().trim();
                    String pass = password.getText().toString();

                    if(isEmpty(mail))
                    {
                        email.setError("Cannot be empty");
                        email.requestFocus();
                    }
                    else if (isEmpty(pass))
                    {
                        password.setError("Cannot be empty");
                        password.requestFocus();
                    }


                    else {
                        progressDialog.show();
                        firebaseAuth.signInWithEmailAndPassword(mail, pass)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        progressDialog.cancel();
                                        Intent myintent = new Intent(MainActivity.this, Home.class);
                                        startActivity(myintent);
                                        Toast.makeText(MainActivity.this, "Login Succesful", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        progressDialog.cancel();
                                        Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                }
            });

            //login agent

        loginA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                    progressDialog.show();

                String mail = emailA.getText().toString().trim();
                String pass = passwordA.getText().toString();

                if(isEmpty(mail))
                {
                    emailA.setError("Cannot be empty");
                    emailA.requestFocus();
                }
                else if (isEmpty(pass))
                {
                    passwordA.setError("Cannot be empty");
                    passwordA.requestFocus();
                }


                else {
                    progressDialog.show();
                    firebaseAuth.signInWithEmailAndPassword(mail, pass)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    progressDialog.cancel();
                                    Intent myintent = new Intent(MainActivity.this, AgentFirstHome.class);
                                    startActivity(myintent);
                                    Toast.makeText(MainActivity.this, "Login Succesful", Toast.LENGTH_SHORT).show();

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressDialog.cancel();
                                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });

        //user er forget password er jonno
        userforgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = email.getText().toString();
                progressDialog.setTitle("Sending...");
                progressDialog.show();


                firebaseAuth.sendPasswordResetEmail(mail)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                                progressDialog.cancel();
                                Toast.makeText(MainActivity.this, "Check Your Email", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.cancel();
                                Toast.makeText(MainActivity.this, "Sending Failed", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });


        //agent er forget password

        agentforgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=emailA.getText().toString();

                firebaseAuth.sendPasswordResetEmail(email)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(MainActivity.this, "Check Your Email", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, "Sending Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });




        }

    }



