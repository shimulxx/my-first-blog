package com.example.jobproject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AvatarAdapter extends RecyclerView.Adapter<AvatarAdapter.ViewHolder> {
    private static final String TAG = "AvatarAdapter";

    private ArrayList<DataValue> dataValues;
    private Context context;
    boolean isImageFitToScreen;

    public AvatarAdapter(Context context) {
        this.context = context;
        isImageFitToScreen = false;
        dataValues = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.avatar_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: started");
        holder.id.setText(String.valueOf(dataValues.get(position).getId()));
        String text = dataValues.get(position).getFirst_name() + " " + dataValues.get(position).getLast_name();
        holder.name.setText(text);
        holder.email.setText(dataValues.get(position).getEmail());
        Glide.with(context)
                .asBitmap()
                .load(dataValues.get(position).getAvatar())
                .into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, String.valueOf(dataValues.get(position).getId()), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, PhotoActivity.class);
                intent.putExtra("image_url", dataValues.get(position).getAvatar());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataValues.size();
    }

    public void setDataValues(ArrayList<DataValue> dataValues) {
        this.dataValues = dataValues;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView id, name, email;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.Id);
            name = (TextView) itemView.findViewById(R.id.name);
            email = (TextView) itemView.findViewById(R.id.email);
            imageView = (ImageView) itemView.findViewById(R.id.itemImage);
        }
    }
}
