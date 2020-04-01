package com.example.svakatha;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.SetOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shopfragmnetselectedimage  extends Fragment {

    private TextView  textViewTshirt,textViewAboutDesign,textViewAboutDesignInfo,textviewTransparentPricing, textViewCounter,textViewTransparentPricingInfo,textViewTotalCost;
    private ImageView imageViewPersonImgae;
    private ImageButton buttonAddtoCart,buttonBuyNow,imagebuttonBackWard,imagebuttonForWard,cart_increment,cart_decrement;
    int angle = 0;
    private Context mContext;
    FirebaseFirestore mDB;
    List<String> urlList=new ArrayList<>();
    public String Totalprice,Totalprice1,J;
    private FirebaseAuth mAuth;
    private int TP,AD,I;



    public Shopfragmnetselectedimage(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth= FirebaseAuth.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view;
        mDB=FirebaseFirestore.getInstance();
        view= inflater.inflate(R.layout.shopfragmenttwo,container,false);
        mContext=getContext();
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
        //testing
        cart_increment=(ImageButton) view.findViewById(R.id.cart_increment);
        cart_decrement=(ImageButton) view.findViewById(R.id.cart_decrement);
        textViewCounter=(TextView)view.findViewById(R.id.textViewCounter);

            Bundle bundle = this.getArguments();
            String receivedIndex = bundle.getString("IndexValue");
            String receivedIn = bundle.getString("Index");
            String receivedI = bundle.getString("IndexV");
            boolean b = Boolean.parseBoolean(receivedI);
            Picasso.get().load(Uri.parse(receivedIn)).into(imageViewPersonImgae);

        String uId = mAuth.getCurrentUser().getUid();
//my logic



           mDB.collection("users").document(uId).addSnapshotListener(new EventListener<DocumentSnapshot>() {
               @Override
               public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                   String received = documentSnapshot.getString("AddCart");
                   String received2 = documentSnapshot.getString("Counter");
                   Log.i("ALL", received);
                   Log.i("ALL", received2);
                   Totalprice1 = received;
                   J=received2;
                   AD = Integer.parseInt(Totalprice1);
                   I= Integer.parseInt(J);
                   textViewCounter.setText(""+I);
               }
           });





        mDB.collection("users").document(mAuth.getCurrentUser().getUid()).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String finalProfileText2 = documentSnapshot.getString("Gender");



                    }
                });

        if(receivedI=="false"){
            mDB.collection("Images").document("maleimages")
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            String received=documentSnapshot.getString("details");
                            textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                            Log.i("ALL",received);
                        }
                    });
            mDB.collection("Images").document("maleimages").addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    String received = documentSnapshot.getString(receivedIndex+"Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
            mDB.collection("Images").document("maleimageprice").addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    String received = documentSnapshot.getString(receivedIndex+"TP");
                    textViewTotalCost.setText(received.replace("\\n","\n"));
                    textViewTotalCost.setText("Total Price"+"\n"+received);
                    Log.i("ALL",received);
                    Totalprice=received;
                    TP=Integer.parseInt(Totalprice);
                }
            });


        }

        else
        {
            mDB.collection("Images").document("femaleimages")
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            String received=documentSnapshot.getString("details");
                            textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                            Log.i("ALL",received);
                        }
                    });
            mDB.collection("Images").document("femaleimages").addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    String received = documentSnapshot.getString(receivedIndex+"Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
            mDB.collection("Images").document("femalePrice").addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    String received = documentSnapshot.getString(receivedIndex+"TP");
                    textViewTotalCost.setText(received.replace("\\n","\n"));
                    textViewTotalCost.setText("Total Price"+"\n"+received);
                    Totalprice=received;
                    TP=Integer.parseInt(Totalprice);
                    Log.i("ALL",received);
                }
            });


        }
//testing
        cart_increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                I=I+1;
                AD=AD+TP;
                textViewCounter.setText(""+I);
                saveADToDb(AD,I);
            }
        });

        cart_decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(I<=1){
                    AD=0;
                    I=0;
                    Toast.makeText(mContext, "Empty Cart", Toast.LENGTH_SHORT).show();
                    saveADToDb(AD,I);
                }
                else {
                    I--;
                        AD = AD - TP;
                    textViewCounter.setText(""+I);
                    saveADToDb(AD,I);
                }
            }
        });

//

        buttonAddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(I==0){
                    Toast.makeText(mContext, "Select Item", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(mContext, "Added To Cart", Toast.LENGTH_SHORT).show();

                }
            }
        });




        buttonBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity().getBaseContext(),RazorPay.class);
                if(AD==0){
                    intent.putExtra("YourInteger",TP);
                }
                else{
                    intent.putExtra("YourInteger",AD);
                }
                getActivity().startActivity(intent);

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
    public void saveADToDb(int AD,int I) {
        String uId = mAuth.getCurrentUser().getUid();
        DocumentReference documentReference = mDB.collection("users").document(uId);
        Map<String, Object> user = new HashMap<>();
        String X=String.valueOf(AD);
        user.put("AddCart",X);
        mDB.collection("users").document(uId).set(user, SetOptions.merge());
        String Y=String.valueOf(I);
        user.put("Counter",Y);
        mDB.collection("users").document(uId).set(user, SetOptions.merge());

    }

}
