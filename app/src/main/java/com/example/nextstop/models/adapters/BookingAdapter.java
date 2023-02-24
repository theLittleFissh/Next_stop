package com.example.nextstop.models.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nextstop.R;
import com.example.nextstop.models.BookingInfo;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class BookingAdapter extends FirebaseRecyclerAdapter<BookingInfo,BookingAdapter.myViewHolder> {



    public BookingAdapter(@NonNull FirebaseRecyclerOptions<BookingInfo> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull BookingAdapter.myViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull BookingInfo model) {
        holder.name.setText(model.getName());
        holder.email.setText(model.getEmail());
        holder.mobile.setText(model.getPhone());



        //Data Delete korar kaj
        holder.confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.name.getContext());
                builder.setTitle("Do You Want To Confirm This Order?");
                builder.setMessage("Confirmed Data will be disappeared");


                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("BookingInfo")
                                .child(getRef(position).getKey()).removeValue();

                        Toast.makeText(holder.name.getContext(), "Order Confirmed", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.name.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();

            }
        });
        //delete buton er kaj sesh



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
        Button confirmbtn;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            name=(TextView) itemView.findViewById(R.id.order_customer_name);
            email=(TextView) itemView.findViewById(R.id.order_customer_email);
            mobile=(TextView) itemView.findViewById(R.id.order_customer_mobile);

            confirmbtn=(Button) itemView.findViewById(R.id.order_confirm_button);


        }
    }
}
