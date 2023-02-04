package com.example.nextstop.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nextstop.R;
import com.example.nextstop.models.best_hostels_model;

import java.util.List;

public class best_hostels_adapter extends RecyclerView.Adapter<best_hostels_adapter.ViewHolder> {

    private Context context;
    private List<best_hostels_model>best_hostels_modelList;

    public best_hostels_adapter(Context context, List<best_hostels_model> best_hostels_modelList) {
        this.context = context;
        this.best_hostels_modelList = best_hostels_modelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.best_hostels,parent,false)); //important thing
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //img change houar jonno
        Glide.with(context).load(best_hostels_modelList.get(position).getImg_url()).into(holder.best_hostel_Img);
        holder.name.setText(best_hostels_modelList.get(position).getName());
        holder.price.setText(best_hostels_modelList.get(position).getPrice());
        holder.rooms.setText(best_hostels_modelList.get(position).getRooms());
    }

    @Override
    public int getItemCount() {
        return best_hostels_modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView best_hostel_Img;
        TextView name,price,rooms;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            best_hostel_Img=itemView.findViewById(R.id.best_hostels_img);
            name=itemView.findViewById(R.id.best_hostels_name);
            price=itemView.findViewById(R.id.best_hostels_price);
            rooms=itemView.findViewById(R.id.best_hostels_room);

        }
    }
}
