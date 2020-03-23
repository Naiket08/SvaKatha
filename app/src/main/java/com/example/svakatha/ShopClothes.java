package com.example.svakatha;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ShopClothes extends Fragment {
    
    private TextView textViewClosetSugestions,textViewRohanAddDesign,textViewSeason;
    private ImageView imageViewShopScreenGreet;
    private ImageButton imageButtonPersonClothesSelection;
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        mContext = getContext();
        view = inflater.inflate(R.layout.shopfragment,container,false);
        textViewClosetSugestions=(TextView)view.findViewById(R.id.textViewClosetSugestion_1);
        textViewRohanAddDesign=(TextView)view.findViewById(R.id.textViewAddDesign_1);
        textViewSeason=(TextView)view.findViewById(R.id.textViewSeason_1);
        
        imageViewShopScreenGreet=(ImageView)view.findViewById(R.id.imageViewShopScreenGreet_1);
        
        imageButtonPersonClothesSelection=(ImageButton)view.findViewById(R.id.imageButtonPersonClothesSelection_1);
     
        return view;
    }
}
