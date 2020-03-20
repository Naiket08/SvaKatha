package com.example.svakatha.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.svakatha.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ClosetGridAdapter extends BaseAdapter {

    private Context mContext;
    private List<DocumentSnapshot> imageList = new ArrayList<>();

    public ClosetGridAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        if (imageList != null ) {
            return imageList.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return imageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView ==  null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.closet_grid_item, parent, false);
        }
        ImageView imageView;
        imageView = convertView.findViewById(R.id.iv_closet_grid);
        Picasso.get().load(imageList.get(position).getString("downloadUrl")).into(imageView);
//        imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.image1));

        return imageView;
    }

    public void setImageList(List<DocumentSnapshot> imageList) {
        this.imageList = imageList;
    }

    public void addImage(DocumentSnapshot image) {

        imageList.add(image);
        notifyDataSetChanged();
    }
}
