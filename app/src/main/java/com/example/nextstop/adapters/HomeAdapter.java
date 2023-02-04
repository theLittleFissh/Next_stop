package com.example.nextstop.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nextstop.R;
import com.example.nextstop.models.HomeModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.makeramen.roundedimageview.RoundedImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeAdapter extends FirebaseRecyclerAdapter<HomeModel,HomeAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public HomeAdapter(@NonNull FirebaseRecyclerOptions<HomeModel> options) {

        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull HomeModel model) {
        holder.name.setText(model.getApp());
        holder.price.setText(model.getCost());
        holder.rooms.setText(model.getRoom());

        Glide.with(holder.img.getContext())
                .load(model.getImg_link())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);


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

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img=(RoundedImageView) itemView.findViewById(R.id.best_hostels_img);
            name=(TextView) itemView.findViewById(R.id.best_hostels_name);
            price=(TextView) itemView.findViewById(R.id.best_hostels_price);
            rooms=(TextView) itemView.findViewById(R.id.best_hostels_room);
        }
    }
}
