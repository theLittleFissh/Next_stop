package com.example.nextstop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;

public class payment_Method extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_payment_method);
//    }
//}


//public class TransportDetailsInfo extends AppCompatActivity {

    LinearLayout firstL, scndL,thridL;
    private String verificationId;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;
    EditText editTextCountryCode, editTextPhone,fammount,OTP;
    Button buttonContinue,buttonVerify,EndButton;
    //dialog

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        payment = findViewById(R.id.PAYMENT);






        //Payment Dialog
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog = new Dialog(TransportDetailsInfo.this);
                myDialog.setContentView(R.layout.activity_paymentsystem);
                txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
                txtclose.setText("X");
                mAuth = FirebaseAuth.getInstance();
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });
                // new code

                firstL =(LinearLayout) myDialog.findViewById(R.id.firstpase);
                scndL = (LinearLayout) myDialog.findViewById(R.id.scndphase);
                thridL =  (LinearLayout)myDialog.findViewById(R.id.thridpase);

                //first step part
                fammount =  (EditText) myDialog.findViewById(R.id.ammount);
                editTextCountryCode = (EditText) myDialog.findViewById(R.id.editTextCountryCode);
                editTextPhone = (EditText) myDialog.findViewById(R.id.editTextPhone);


                buttonContinue =(Button) myDialog.findViewById(R.id.buttonContinue);
                buttonVerify =  (Button)myDialog.findViewById(R.id.buttonVerify);
                EndButton = (Button) myDialog.findViewById(R.id.done);
                //otp part
                progressBar = (ProgressBar) myDialog.findViewById(R.id.progressbar);
                OTP =(EditText) myDialog.findViewById(R.id.editTextCode);

                buttonContinue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String code = editTextCountryCode.getText().toString().trim();
                        String number = editTextPhone.getText().toString().trim();

                        if (number.isEmpty() || number.length() < 10) {
                            editTextPhone.setError("Valid number is required");
                            editTextPhone.requestFocus();
                            return;
                        }

                        String phoneNumber = code + number;
                        sendVerificationCode(phoneNumber);

                        firstL.setVisibility(View.GONE);
                        scndL.setVisibility(View.VISIBLE);
                    }
                });

                buttonVerify.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String code = OTP.getText().toString().trim();

                        if (code.isEmpty() || code.length() < 6) {

                            OTP.setError("Enter code...");
                            OTP.requestFocus();
                            return;
                        }
                        verifyCode(code);
                    }
                });


                EndButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });


                //new code end
                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialog.show();
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String PhoneNumber = number.getText().toString();
                callNumber(PhoneNumber);
            }
        });


    });



}



    // FIREBASE PHONE AUTHENTICATION
    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            scndL.setVisibility(View.GONE);
                            thridL.setVisibility(View.VISIBLE);
//                            Intent intent = new Intent(VerifyPhoneActivity.this, ProfileActivity.class);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//
//                            startActivity(intent);

                        } else {
                            Toast.makeText(TransportDetailsInfo.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }

    private void sendVerificationCode(String number) {
        progressBar.setVisibility(View.VISIBLE);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60L,
                TimeUnit.SECONDS,
                TransportDetailsInfo.this,
                mCallBack
        );

        progressBar.setVisibility(View.GONE);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                OTP.setText(code);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(TransportDetailsInfo.this, e.getMessage(), Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.GONE);
        }
    };
}