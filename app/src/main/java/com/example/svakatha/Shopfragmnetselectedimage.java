package com.example.svakatha;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Shopfragmnetselectedimage  extends Fragment {

    private TextView  textViewTshirt,textViewAboutDesign,textViewAboutDesignInfo,textviewTransparentPricing,textViewTransparentPricingInfo,textViewTotalCost;
    private ImageView imageViewPersonImgae;
    private ImageButton buttonAddtoCart,buttonBuyNow,imagebuttonBackWard,imagebuttonForWard;
    int angle = 0;
    FirebaseFirestore mDB;


    public Shopfragmnetselectedimage(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view;
        mDB=FirebaseFirestore.getInstance();
        view= inflater.inflate(R.layout.shopfragmenttwo,container,false);

        textViewTshirt=(TextView)view.findViewById(R.id.textViewTshirt_1);
        textViewAboutDesign=(TextView)view.findViewById(R.id.textViewAboutDesign_1);
        textViewAboutDesignInfo=(TextView)view.findViewById(R.id.textViewAboutDesignInfo_1);
        textviewTransparentPricing=(TextView)view.findViewById(R.id.textViewTransparentPricing_1);
        textViewTransparentPricingInfo=(TextView)view.findViewById(R.id.textViewTransparentPricingInfo_1);
        textViewTotalCost=(TextView)view.findViewById(R.id.textViewTotalCost_1);
        imageViewPersonImgae=(ImageView)view.findViewById(R.id.imageViewPersoneImgae_1);
        buttonAddtoCart=(ImageButton) view.findViewById(R.id.imagebuttonAddtoCart_1);
        buttonBuyNow=(ImageButton) view.findViewById(R.id.imagebuttonBuyNow_1);
        imagebuttonBackWard=(ImageButton)view.findViewById(R.id.imagebuttonBackWard_1);
        imagebuttonForWard=(ImageButton)view.findViewById(R.id.imagebuttonForWard_1);
        buttonBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),RazorPay.class);
                startActivity(intent);
            }
        });

        mDB.collection("Images").document("1")
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                        String received=documentSnapshot.getString("details");
                        textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                        Log.i("ALL",received);
                    }
                });

        imagebuttonForWard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                angle = angle + 90;
                imageViewPersonImgae.setRotation(angle);
            }
        });
        imagebuttonBackWard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                angle = angle - 90;
                imageViewPersonImgae.setRotation(angle);
            }
        });

        return view;

    }
}
