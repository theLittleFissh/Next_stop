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
import com.example.nextstop.models.FoodOrderInfo;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class FoodOrderAdapter extends FirebaseRecyclerAdapter<FoodOrderInfo,FoodOrderAdapter.myViewHolder> {

    public FoodOrderAdapter(@NonNull FirebaseRecyclerOptions<FoodOrderInfo> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull FoodOrderAdapter.myViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull FoodOrderInfo model) {
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
                        FirebaseDatabase.getInstance().getReference().child("FoodBookingInfo")
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
    public FoodOrderAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.agent_food_order_list_design,parent,false);
        //return new HomeAdapter.myViewHolder(view);
        return new myViewHolder(view);
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        TextView name,email,mobile;
        View viewLayout;
        Button confirmbtn;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);


            name=(TextView) itemView.findViewById(R.id.order_customer_name2);
            email=(TextView) itemView.findViewById(R.id.order_customer_email2);
            mobile=(TextView) itemView.findViewById(R.id.order_customer_mobile2);

            confirmbtn=(Button) itemView.findViewById(R.id.order_confirm_button2);
        }
    }
}
