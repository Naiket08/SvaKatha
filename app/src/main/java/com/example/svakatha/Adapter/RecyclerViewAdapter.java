package com.example.svakatha.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.svakatha.Model.ShapeBodyModel;
import com.example.svakatha.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    List <ShapeBodyModel>dataList;




    public RecyclerViewAdapter(List<ShapeBodyModel> dataList)
    {
        this.dataList=dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.get().load(dataList.get(position).getDownloadlink()).into(holder.imageView);
        //holder.textView.setText("Image no"+position);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        View mView;

        //TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;
            imageView=itemView.findViewById(R.id.image1);
            //textView=itemView.findViewById(R.id.text1);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        ClickListener.onItemClick(view,getAdapterPosition());
                }
            });
        }

        public MyViewHolder.ClickListener mClickListener;


        public interface ClickListener{
            static void onItemClick(View view, int position){
                FirebaseAuth mauth = FirebaseAuth.getInstance();
                final String currentID = mauth.getCurrentUser().getUid();
                final FirebaseFirestore db = FirebaseFirestore.getInstance();
                String bodyshape="Saved shape"+(position+1);
                Toast.makeText(view.getContext(), bodyshape, Toast.LENGTH_SHORT).show();
                Map<String, Object> user = new HashMap<>();
                user.put("BodyShape", bodyshape);
                db.collection("users").document(currentID).set(user, SetOptions.merge());
            }
        }

        public void setOnClickListener(MyViewHolder.ClickListener clickListener){
                mClickListener = clickListener;
        }
    }



}
