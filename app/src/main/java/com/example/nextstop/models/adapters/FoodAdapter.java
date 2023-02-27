package com.example.nextstop.models.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nextstop.Payment_Proces;
import com.example.nextstop.R;
import com.example.nextstop.models.BookingInfo;
import com.example.nextstop.models.FoodModel;
import com.example.nextstop.models.FoodOrderInfo;
import com.example.nextstop.models.HomeModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.protobuf.StringValue;
import com.makeramen.roundedimageview.RoundedImageView;

import org.w3c.dom.Text;

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


        holder.food_orderpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent=new Intent(context,Payment_Proces.class);
                context.startActivity(intent);

            }
        });

        //new
        holder.food_orderPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Context context=v.getContext();
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                String uid = mAuth.getCurrentUser().getUid();


                db.collection("Users")
                        .document(uid)
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {
                                    String Email = documentSnapshot.getString("email");
                                    String Name = documentSnapshot.getString("fullname");
                                    String Phone = documentSnapshot.getString("phone");

                                    System.out.println("CheckT: "+Email);
                                    System.out.println("CheckP: "+Name);
                                    System.out.println("CheckR: "+Phone);


                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    DatabaseReference dbroot = database.getReference("FoodBookingInfo");
                                    FoodOrderInfo book = new FoodOrderInfo(Email,Name,Phone);
                                    String key = dbroot.push().getKey();
                                    dbroot.child(key).setValue(book);


                                    holder.food_orderPlace.setVisibility(View.GONE);
                                    Toast.makeText(context, "Please Make Payment", Toast.LENGTH_SHORT).show();
                                    holder.food_orderpayment.setVisibility(View.VISIBLE);



                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });


            }
        });


        //new


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
        TextView food_name,food_price,counting;
        Button food_orderpayment,food_orderPlace;
        ImageView addbutton,removebutton;

        int totalcounting=1;
        int totalprice=0;



        public myViewHolder(@NonNull View itemView) {
            super(itemView);


            food_img= (CircleImageView) itemView.findViewById(R.id.food_img);
            food_name=(TextView) itemView.findViewById(R.id.user_food_name_);
            food_price=(TextView) itemView.findViewById(R.id.user_food_price);
            food_orderpayment=(Button) itemView.findViewById(R.id.user_food_payment);
            food_orderPlace=(Button)itemView.findViewById(R.id.user_food_place_order);

            viewLayout=(View)itemView.findViewById(R.id.clickable_recyclerView);

            counting=itemView.findViewById(R.id.counting);
            addbutton=itemView.findViewById(R.id.elegentADD);
            removebutton=itemView.findViewById(R.id.elegentRemove);




            //new code korteci

            addbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(totalcounting<10)
                    {
                        totalcounting++;
                        counting.setText(String.valueOf(totalcounting));




                    }

                }
            });

            removebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(totalcounting>1)
                    {
                        totalcounting--;
                        counting.setText(String.valueOf(totalcounting));
                    }

                }
            });





        }
    }
}
