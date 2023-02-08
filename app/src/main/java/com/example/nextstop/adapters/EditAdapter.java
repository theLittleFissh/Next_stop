package com.example.nextstop.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nextstop.R;
import com.example.nextstop.models.HomeModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.makeramen.roundedimageview.RoundedImageView;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class EditAdapter extends FirebaseRecyclerAdapter<HomeModel,EditAdapter.myViewHolder2> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public EditAdapter(@NonNull FirebaseRecyclerOptions<HomeModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder2 holder, @SuppressLint("RecyclerView") final int position, @NonNull HomeModel model) {

        holder.name.setText(model.getApp());
        holder.price.setText(model.getCost());
        holder.rooms.setText(model.getRoom());

        Glide.with(holder.img.getContext())
                .load(model.getImg_link())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

        //Update er kaj ekhane
        holder.buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true,1300)
                        .create();


                //dialogPlus.show();
                View view=dialogPlus.getHolderView();
                EditText name=view.findViewById(R.id.EditNametxt);
                EditText room=view.findViewById(R.id.EditRoomtxt);
                EditText price=view.findViewById(R.id.EditPricetxt);
                EditText img_url=view.findViewById(R.id.EditImgtxt);

                Button buttonUpdate =view.findViewById(R.id.EditUpdateButton);

                name.setText(model.getApp());
                room.setText(model.getRoom());
                price.setText(model.getCost());
                img_url.setText(model.getImg_link());

                dialogPlus.show();


                buttonUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object>map=new HashMap<>();
                        map.put("app",name.getText().toString());
                        map.put("room",room.getText().toString());
                        map.put("cost",price.getText().toString());
                        map.put("img",img_url.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("User")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.name.getContext(), "Data Upadated", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.name.getContext(), "Data Upadat Failed", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });


                    }
                });

            }
        });
        //Data Delete korar kaj
        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.name.getContext());
                builder.setTitle("Are You Sure?");
                builder.setMessage("Deleted Data Can't be Undo");


                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("User")
                                        .child(getRef(position).getKey()).removeValue();

                        Toast.makeText(holder.name.getContext(), "Deleted", Toast.LENGTH_SHORT).show();
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


    }

    @NonNull
    @Override
    public myViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.agent_edit_delete_design,parent,false);

        return new myViewHolder2(view);
    }

    class myViewHolder2 extends RecyclerView.ViewHolder{
        RoundedImageView img;
        TextView name,address,price,rooms,mobile;

        Button buttonEdit,buttonDelete;


        public myViewHolder2(@NonNull View itemView) {
            super(itemView);

            img=(RoundedImageView) itemView.findViewById(R.id.best_hostels_img);
            name=(TextView) itemView.findViewById(R.id.best_hostels_name);
            price=(TextView) itemView.findViewById(R.id.best_hostels_price);
            rooms=(TextView) itemView.findViewById(R.id.best_hostels_room);

            buttonEdit=(Button)itemView.findViewById(R.id.dbEdit);
            buttonDelete=(Button)itemView.findViewById(R.id.dbDelete);

        }
    }
}
