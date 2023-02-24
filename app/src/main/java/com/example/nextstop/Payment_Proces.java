package com.example.nextstop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Payment_Proces extends AppCompatActivity {
    private boolean otpSent=false;
    private String countryCode="+880";
    private String id="";



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_proces);
        final EditText mobileNumber=findViewById(R.id.bkash_Number);
        final EditText otp=findViewById(R.id.bkash_otp);
        final Button sendOtp=findViewById(R.id.bkash_sendotp);


        FirebaseApp.initializeApp(this);
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();


        sendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(otpSent){
                    final String getOTP=otp.getText().toString();

                    if(id.isEmpty()){

                        Toast.makeText(Payment_Proces.this, "Unable to Payment", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        PhoneAuthCredential credential= PhoneAuthProvider.getCredential(id,getOTP);
                        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    FirebaseUser userDetails=task.getResult().getUser();
                                    Toast.makeText(Payment_Proces.this, "Payment Done", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(Payment_Proces.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }

                }
                else{
                    final String getMobile=mobileNumber.getText().toString();

                    PhoneAuthOptions options= PhoneAuthOptions.newBuilder(firebaseAuth)
                            .setPhoneNumber(countryCode+""+getMobile)
                            .setTimeout(60L, TimeUnit.SECONDS)
                            .setActivity(Payment_Proces.this)
                            .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    Toast.makeText(Payment_Proces.this, "OTP SENT", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    Toast.makeText(Payment_Proces.this, "Something Went Worng"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    super.onCodeSent(s, forceResendingToken);
                                    otp.setVisibility(View.VISIBLE);
                                    sendOtp.setText("Verify OTP");
                                    id=s;
                                    otpSent=true;
                                }
                            }).build();

                    PhoneAuthProvider.verifyPhoneNumber(options);
                }

            }
        });

    }
}