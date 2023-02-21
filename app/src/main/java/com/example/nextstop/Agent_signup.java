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

import com.example.nextstop.models.AgentModelClass;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Agent_signup extends AppCompatActivity {

    EditText agentName,agentEmail,agentPhone,agentNid,agentPassword,agentPasswordConf;
    Button register;
    TextView alreadyHaveAcc;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_signup2);

        agentName = findViewById(R.id.agentName);
        agentEmail = findViewById(R.id.agentEmail);
        agentPhone = findViewById(R.id.agentPhone);
        agentNid =findViewById(R.id.agentNidnumber);
        agentPassword = findViewById(R.id.agentPass1);
        agentPasswordConf=findViewById(R.id.agentPass2);

        register=findViewById(R.id.registerbtn);
        alreadyHaveAcc= findViewById(R.id.signupThekeLogin);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String agentname=agentName.getText().toString();
                String agentemail=agentEmail.getText().toString().trim();
                String agentphone=agentPhone.getText().toString();
                String agentnid=agentNid.getText().toString();
                String agentpassword=agentPassword.getText().toString();
                String agentpasswordconf=agentPasswordConf.getText().toString();


                //regx


                String phoneRegex = "^(?:\\+88|88)?01[3-9]\\d{8}$";
                Pattern pattern = Pattern.compile(phoneRegex);
                Matcher matcher = pattern.matcher(agentphone);




                //regx sesh





                if(isEmpty(agentname))
                {
                    agentName.setError("Cannot be empty");
                    agentName.requestFocus();
                }
                else if(isEmpty(agentemail))
                {
                   agentEmail.setError("Cannot be empty");
                   agentEmail.requestFocus();
                }
                else if(isEmpty(agentphone))
                {
                    agentPhone.setError("Cannot be empty");
                    agentPhone.requestFocus();
                }
                else if (!matcher.matches()) {
                    // Not a valid number
                    agentPhone.setError("Not a valid number");
                    agentPhone.requestFocus();
                }
                else if(isEmpty(agentnid))
                {
                    agentNid.setError("Cannot be empty");
                    agentNid.requestFocus();
                }
                else if(isEmpty(agentpassword))
                {
                    agentPassword.setError("Cannot be empty");
                    agentPassword.requestFocus();
                }
                else if(agentpasswordconf.equals(agentpassword)){



                    firebaseAuth.createUserWithEmailAndPassword(agentemail,agentpassword)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {

                                    firebaseFirestore.collection("Agents").document(FirebaseAuth.getInstance().getUid())
                                            .set(new AgentModelClass(agentname,agentphone,agentemail,agentnid));

                                    Intent myintent =new Intent(Agent_signup.this,MainActivity.class);

                                    startActivity(myintent);
                                    finish();

                                    Toast.makeText(Agent_signup.this, "Registration Completed", Toast.LENGTH_SHORT).show();

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(Agent_signup.this, "Registration Failed", Toast.LENGTH_SHORT).show();

                                }
                            });

                }





            }
        });

        alreadyHaveAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent =new Intent(Agent_signup.this,MainActivity.class);

                startActivity(myintent);
                finish();
            }
        });










    }
}