package com.example.nextstop.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nextstop.Payment_Proces;
import com.example.nextstop.R;
import com.example.nextstop.models.FoodModel;
import com.example.nextstop.models.HomeModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
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


        holder.food_orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent=new Intent(context,Payment_Proces.class);
                context.startActivity(intent);
            }
        });

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
