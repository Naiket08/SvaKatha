package com.example.svakatha;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Shopfragmnetselectedimage  extends Fragment {

    private TextView  textViewTshirt,textViewAboutDesign,textViewAboutDesignInfo,textviewTransparentPricing,textViewTransparentPricingInfo,textViewTotalCost;
    private ImageView imageViewPersonImgae;
    private ImageButton buttonAddtoCart,buttonBuyNow,btn_imgviewfor,btn_imgviewback;
    private FirebaseFirestore mDB;
    String img1,img2,img3,img4,img5;
    String ClothDetails="Bolo";
    int count=1;
    List<String> urlList=new ArrayList<>();

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
        String receivedIndex = getArguments().getString("IndexValue");
        textViewTshirt=(TextView)view.findViewById(R.id.textViewTshirt_1);
        textViewAboutDesign=(TextView)view.findViewById(R.id.textViewAboutDesign_1);
        textViewAboutDesignInfo=(TextView)view.findViewById(R.id.textViewAboutDesignInfo_1);
        textviewTransparentPricing=(TextView)view.findViewById(R.id.textViewTransparentPricing_1);
        textViewTransparentPricingInfo=(TextView)view.findViewById(R.id.textViewTransparentPricingInfo_1);
        textViewTotalCost=(TextView)view.findViewById(R.id.textViewTotalCost_1);
        imageViewPersonImgae=view.findViewById(R.id.imageViewPersoneImgae_1);
        buttonAddtoCart= view.findViewById(R.id.imagebuttonAddtoCart_1);
        buttonBuyNow=view.findViewById(R.id.imagebuttonBuyNow_1);
        btn_imgviewback=view.findViewById(R.id.imagebuttonBackWard_1);
        btn_imgviewfor=view.findViewById(R.id.imagebuttonForWard_1) ;
        mDB.collection("Images").document(receivedIndex)
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                img1=documentSnapshot.getString("img1");
                img2=documentSnapshot.getString("img2");
                img3=documentSnapshot.getString("img3");
                img4=documentSnapshot.getString("img4");
                img5=documentSnapshot.getString("img5");
                urlList.add(img1);
                urlList.add(img2);
                urlList.add(img3);
                urlList.add(img4);
                urlList.add(img5);

                ClothDetails=documentSnapshot.getString("details");
                Log.i("Data",ClothDetails);
                textViewAboutDesignInfo.setText(ClothDetails);
            }
        });

        btn_imgviewfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count>=5){
                    count=0;
                }
                //    Log.i("TEra","Baaap"+temp.concat(String.valueOf(count)));
                Picasso.get().load(urlList.get(count)).into(imageViewPersonImgae);
                count++;
            }
        });


        buttonBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),RazorPay.class);
                startActivity(intent);
            }
        });

        return view;

    }
}
