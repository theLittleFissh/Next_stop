package com.example.nextstop.models.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nextstop.Payment_Proces;
import com.example.nextstop.R;
import com.example.nextstop.models.FoodModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;

public class FoodAdapter extends FirebaseRecyclerAdapter<FoodModel,FoodAdapter.myViewHolder> {

    public FoodAdapter(@NonNull FirebaseRecyclerOptions<FoodModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull FoodAdapter.myViewHolder holder, int position, @NonNull FoodModel model) {

        holder.food_name.setText(model.getFood_name());
        holder.food_price.setText(model.getFood_price());

        Glide.with(holder.food_img.getContext())
                .load(model.getFood_img_link())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.food_img);

        //payment er kaj sob ekante
        holder.food_orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth mAuth;
                LinearLayout firstL,scndL,thridL;
                EditText fammount,editTextCountryCode,editTextPhone,OTP;
                Button buttonVerify,buttonContinue,EndButton;
                ProgressBar progressBar;



                Dialog myDialog = new Dialog(v.getContext());

                myDialog.setContentView(R.layout.activity_payment_method);
                TextView txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
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

            private void sendVerificationCode(String phoneNumber)

            {

//                progressBar.setVisibility(View.VISIBLE);
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        number,
                        60L,
                        TimeUnit.SECONDS,
                        Context,
                        mCallBack
                );

//                progressBar.setVisibility(View.GONE);

            }
        });

        //payment er kaj sesh bhai

    }

    @NonNull
    @Override
    public FoodAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item,parent,false);
        return new FoodAdapter.myViewHolder(view);

    }



    class myViewHolder extends RecyclerView.ViewHolder{

        CircleImageView food_img;
        View viewLayout;
        TextView food_name,food_price;
        Button food_orderButton;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);


            food_img= (CircleImageView) itemView.findViewById(R.id.food_img);
            food_name=(TextView) itemView.findViewById(R.id.user_food_name_);
            food_price=(TextView) itemView.findViewById(R.id.user_food_price);
            food_orderButton=(Button) itemView.findViewById(R.id.user_food_order);


            viewLayout=(View)itemView.findViewById(R.id.clickable_recyclerView);




        }
    }
}
