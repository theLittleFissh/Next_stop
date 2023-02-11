package com.example.nextstop.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nextstop.R;
import com.example.nextstop.UserDetails_show;
import com.example.nextstop.models.HomeModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeAdapter extends FirebaseRecyclerAdapter<HomeModel,HomeAdapter.myViewHolder> {
//public class HomeAdapter extends RecyclerView.Adapter<MyViewHolder> {

////    private Context context;
////    private List<HomeModel>datalist;
//
////    public HomeAdapter(Context context, List<HomeModel> datalist) {
////        this.context = context;
////        this.datalist = datalist;
////    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.best_hostels,parent,false);
//        return new MyViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//
//        Glide.with(context).load(datalist.get(position).getImg_link()).into(holder.img);
//        holder.name.setText(datalist.get(position).getApp());
//        holder.price.setText(datalist.get(position).getCost());
//        holder.room.setText(datalist.get(position).getRoom());
//        holder.address.setText(datalist.get(position).getAddress());
//        holder.mobileNumber.setText(datalist.get(position).getMobile());
//
//
//        holder.clickCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(context, UserDetails_show.class);
//                intent.putExtra("Image",datalist.get(holder.getAdapterPosition()).getImg_link());
//                intent.putExtra("Title",datalist.get(holder.getAdapterPosition()).getApp());
//                intent.putExtra("Room",datalist.get(holder.getAdapterPosition()).getRoom());
//                intent.putExtra("Address",datalist.get(holder.getAdapterPosition()).getAddress());
//
//                context.startActivity(intent);
//            }
//        });
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return datalist.size();
//    }

        public HomeAdapter(@NonNull FirebaseRecyclerOptions<HomeModel> options) {

        super(options);
    }
   // test git

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull HomeModel model) {
        holder.name.setText(model.getApp());
        holder.price.setText(model.getCost());
        holder.rooms.setText(model.getRoom());
        holder.address.setText(model.getAddress());
        holder.mobile.setText(model.getMobile());


        Glide.with(holder.img.getContext())
                .load(model.getImg_link())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);


        holder.viewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), UserDetails_show.class);;
                intent.putExtra("name", model.getApp());
                intent.putExtra("price", model.getCost());
                intent.putExtra("rooms", model.getRoom());
                intent.putExtra("address", model.getAddress());
                intent.putExtra("mobile", model.getMobile());
                intent.putExtra("pic", model.getImg_link());
                view.getContext().startActivity(intent);

            }
        });


    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.best_hostels,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        RoundedImageView img;
        TextView name,address,price,rooms,mobile;
        CardView click_card;
        View viewLayout;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img=(RoundedImageView) itemView.findViewById(R.id.best_hostels_img);
            name=(TextView) itemView.findViewById(R.id.best_hostels_name);
            price=(TextView) itemView.findViewById(R.id.best_hostels_price);
            rooms=(TextView) itemView.findViewById(R.id.best_hostels_room);
            address=(TextView) itemView.findViewById(R.id.best_hostels_address);
            mobile=(TextView) itemView.findViewById(R.id.best_hostels_contact);
            click_card=itemView.findViewById(R.id.UserHostelCardclick);

            viewLayout=(View)itemView.findViewById(R.id.clickable_recyclerView);

        }
    } //
}


///bal
//class MyViewHolder extends RecyclerView.ViewHolder{
//
//    ImageView img;
//    TextView name,price,room,address,mobileNumber;
//    CardView clickCard;
//
//
//    public MyViewHolder(@NonNull View itemView) {
//        super(itemView);
//
//        img=itemView.findViewById(R.id.best_hostels_img);
//        name=itemView.findViewById(R.id.best_hostels_name);
//        price=itemView.findViewById(R.id.best_hostels_price);
//        room=itemView.findViewById(R.id.best_hostels_room);
//        address=itemView.findViewById(R.id.best_hostels_address);
//        mobileNumber=itemView.findViewById(R.id.best_hostels_contact);
//        clickCard=itemView.findViewById(R.id.UserHostelCardclick);
//
//    }
//}
