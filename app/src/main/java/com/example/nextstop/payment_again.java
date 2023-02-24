package com.example.nextstop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class payment_again extends AppCompatActivity {

    EditText enterMobileNumber,verifyotpCode;
    Button sentOtptoUser,verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_again);


        enterMobileNumber=(EditText)findViewById(R.id.enterNumber);
        verifyotpCode=(EditText)findViewById(R.id.verifyOTP);

        sentOtptoUser=(Button)findViewById(R.id.sendOTP);
        verify=(Button)findViewById(R.id.verifyDone);

        initiateotp();


    }

    private void initiateotp() {



    }


}