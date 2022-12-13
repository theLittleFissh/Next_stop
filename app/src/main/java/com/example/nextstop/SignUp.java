package com.example.nextstop;

import static android.text.TextUtils.isEmpty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class SignUp extends AppCompatActivity {
    EditText userName,userEmail,password,passwordgain;
    Button reg;
    TextView login;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userName=findViewById(R.id.signname);
        userEmail=findViewById(R.id.signmail);
        password=findViewById(R.id.signpass);
        passwordgain=findViewById(R.id.signpass2);

        reg=findViewById(R.id.registerbtn);
        login=findViewById(R.id.signupThekeLogin);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname=userName.getText().toString();
                String email=userEmail.getText().toString().trim();
                String pass=password.getText().toString();
                String conpass=passwordgain.getText().toString();

                if(isEmpty(fullname))
                {
                    userName.setError("Cannot be empty");
                    userName.requestFocus();
                }
                else if (isEmpty(email))
                {
                    userEmail.setError("Cannot be empty");
                    userEmail.requestFocus();
                }
                else if (isEmpty(pass))
                {
                    password.setError("Cannot be empty");
                    password.requestFocus();
                }
                else if(conpass.equals(pass)) {

                    firebaseAuth.createUserWithEmailAndPassword(email,pass)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    firebaseFirestore.collection("Users").document(FirebaseAuth.getInstance().getUid())
                                            .set(new userModel(fullname,email));

                                    Intent myintent= new Intent(SignUp.this,MainActivity.class);
                                    startActivity(myintent);
                                    finish();
                                    Toast.makeText(SignUp.this, "Registration Successful",Toast.LENGTH_LONG).show();



                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(SignUp.this, "Registration Failed",Toast.LENGTH_LONG).show();

                                }
                            });

                }else {
                    passwordgain.setError("Password Didn't Match");
                    passwordgain.requestFocus();
                    System.out.println("TestPass: "+pass);
                    System.out.println("TestconPass: "+conpass);


                }


                }




        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent=new Intent(SignUp.this, MainActivity.class);
                startActivity(myintent);
                finish();
            }
        });
    }
}
