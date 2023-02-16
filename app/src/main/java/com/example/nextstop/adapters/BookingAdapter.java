package com.example.nextstop.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nextstop.R;
import com.example.nextstop.models.BookingInfo;
import com.example.nextstop.models.HomeModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.makeramen.roundedimageview.RoundedImageView;

public class BookingAdapter extends FirebaseRecyclerAdapter<BookingInfo,BookingAdapter.myViewHolder> {



    public BookingAdapter(@NonNull FirebaseRecyclerOptions<BookingInfo> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull BookingAdapter.myViewHolder holder, int position, @NonNull BookingInfo model) {
        holder.name.setText(model.getName());
        holder.email.setText(model.getEmail());
        holder.mobile.setText(model.getPhone());


    }

    @NonNull
    @Override
    public BookingAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.agent_order_list_design,parent,false);
        //return new HomeAdapter.myViewHolder(view);
        return new myViewHolder(view);
    }

    public class myViewHolder extends RecyclerView.ViewHolder {


        TextView name,email,mobile;
        View viewLayout;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            name=(TextView) itemView.findViewById(R.id.order_customer_name);
            email=(TextView) itemView.findViewById(R.id.order_customer_email);
            mobile=(TextView) itemView.findViewById(R.id.order_customer_mobile);


        }
    }
}
