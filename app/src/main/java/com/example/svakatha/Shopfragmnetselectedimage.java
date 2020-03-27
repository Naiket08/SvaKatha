package com.example.svakatha;

import android.content.Intent;
import android.net.Uri;
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
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Shopfragmnetselectedimage  extends Fragment {

    private TextView  textViewTshirt,textViewAboutDesign,textViewAboutDesignInfo,textviewTransparentPricing,textViewTransparentPricingInfo,textViewTotalCost;
    private ImageView imageViewPersonImgae;
    private ImageButton buttonAddtoCart,buttonBuyNow;
     private ImageButton imagebuttonBackWard,imagebuttonForWard;
    int angle = 0;
    String img;
    String img1,img2,img3,img4,img5,img6,img7,img8,img9,img10,img11,img12,img13,img14,img15,img16,img17,img18,img19,img20;
    //String ClothDetails="Bolo";
    int count=1;
    List<String> urlList=new ArrayList<>();
    FirebaseFirestore mDB;
<<<<<<< Updated upstream
    String index;


=======
    String img1,img2,img3,img4,img5,img6,img7,img8,img9,img10,img11,img12,img13,img14,img15,img16,img17,img18,img19,img20;
    String receiveIndex;
    List<String> urlList=new ArrayList<>();
>>>>>>> Stashed changes

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

        view= inflater.inflate(R.layout.shopfragmenttwo,container,false);
<<<<<<< Updated upstream
        String receivedIndex = getArguments().getString("IndexValue");
        int Index=Integer.parseInt(receivedIndex);

=======
        //receiveIndex=getArguments().getString("IndexValue");
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
        mDB.collection("Images").document(receivedIndex)
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                img1=documentSnapshot.getString("imgM1");
                img2=documentSnapshot.getString("imgM2");
                img3=documentSnapshot.getString("imgM3");
                img4=documentSnapshot.getString("imgM4");
                img5=documentSnapshot.getString("imgM5");
                img6=documentSnapshot.getString("imgM5");
                img7=documentSnapshot.getString("imgM5");
                img8=documentSnapshot.getString("imgM5");
                img9=documentSnapshot.getString("imgM5");
                img10=documentSnapshot.getString("imgM5");
                img11=documentSnapshot.getString("imgM5");
                img12=documentSnapshot.getString("imgM5");
                img13=documentSnapshot.getString("imgM5");
                img14=documentSnapshot.getString("imgM5");
                img15=documentSnapshot.getString("imgM5");
                img16=documentSnapshot.getString("imgM5");
                img17=documentSnapshot.getString("imgM5");
                img18=documentSnapshot.getString("imgM5");
                img19=documentSnapshot.getString("imgM5");
                img20=documentSnapshot.getString("imgM5");
                urlList.add(img1);
                urlList.add(img2);
                urlList.add(img3);
                urlList.add(img4);
                urlList.add(img5);
                urlList.add(img6);
                urlList.add(img7);
                urlList.add(img8);
                urlList.add(img9);
                urlList.add(img10);
                urlList.add(img11);
                urlList.add(img12);
                urlList.add(img13);
                urlList.add(img14);
                urlList.add(img15);
                urlList.add(img16);
                urlList.add(img17);
                urlList.add(img18);
                urlList.add(img19);
                urlList.add(img20);
               // ClothDetails=documentSnapshot.getString("details");
                //Log.i("Data",ClothDetails);
                //textViewAboutDesignInfo.setText(ClothDetails);
            }
        });


        buttonBuyNow.setOnClickListener(new View.OnClickListener() {
=======

        Bundle bundle = this.getArguments();
        receiveIndex=bundle.getString("IndexValue");


        mDB.collection("Images").document("receiveIndex").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                img1=documentSnapshot.getString("imgF1");
                img2=documentSnapshot.getString("imgF2");
                img3=documentSnapshot.getString("imgF3");
                img4=documentSnapshot.getString("imgF4");
                img5=documentSnapshot.getString("imgF5");
                img6=documentSnapshot.getString("imgF6");
                img7=documentSnapshot.getString("imgF7");
                img8=documentSnapshot.getString("imgF8");
                img9=documentSnapshot.getString("imgF9");
                img10=documentSnapshot.getString("imgF10");
                img11=documentSnapshot.getString("imgF11");
                img12=documentSnapshot.getString("imgF12");
                img13=documentSnapshot.getString("imgF13");
                img14=documentSnapshot.getString("imgF14");
                img15=documentSnapshot.getString("imgF15");
                img16=documentSnapshot.getString("imgF16");
                img17=documentSnapshot.getString("imgF17");
                img18=documentSnapshot.getString("imgF18");
                img19=documentSnapshot.getString("imgF19");
                img20=documentSnapshot.getString("imgF20");

                urlList.add("img1");
                urlList.add("img2");
                urlList.add("img3");
                urlList.add("img4");
                urlList.add("img5");
                urlList.add("img6");
                urlList.add("img7");
                urlList.add("img8");
                urlList.add("img9");
                urlList.add("img10");
                urlList.add("img11");
                urlList.add("img12");
                urlList.add("img13");
                urlList.add("img14");
                urlList.add("img15");
                urlList.add("img16");
                urlList.add("img17");
                urlList.add("img18");
                urlList.add("img19");
                urlList.add("img20");
            }
        });

        //Picasso.get().load(Uri.parse(receiveIndex)).into(imageViewPersonImgae);



           if (img1 == bundle.getString(receiveIndex)) {

               Picasso.get().load(Uri.parse(receiveIndex)).into(imageViewPersonImgae);
               mDB.collection("Images").document("femaleimages")
                       .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                           @Override
                           public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                               String received = documentSnapshot.getString("details");
                               textViewAboutDesignInfo.setText(received.replace("\\n", "\n"));
                               Log.i("ALL", received);
                           }
                       });
               mDB.collection("Images").document("femaleimages")
                       .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                           @Override
                           public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                               String received = documentSnapshot.getString("imgF1Price");
                               textViewTransparentPricingInfo.setText(received.replace("\\n", "\n"));
                               Log.i("ALL", received);
                           }
                       });
           }
           if(img2==bundle.getString(receiveIndex)){
               Picasso.get().load(Uri.parse(receiveIndex)).into(imageViewPersonImgae);
               mDB.collection("Images").document("femaleimages").addSnapshotListener(new EventListener<DocumentSnapshot>() {
                   @Override
                   public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                       String received = documentSnapshot.getString("details");
                       textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                       Log.i("ALL",received);
                   }
               });
               mDB.collection("Images").document("femaleimages").addSnapshotListener(new EventListener<DocumentSnapshot>() {
                   @Override
                   public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                       String received = documentSnapshot.getString("imgF2Price");
                       textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                       Log.i("ALL",received);
                   }
               });
           }
           if(img3==bundle.getString(receiveIndex)){
               Picasso.get().load(Uri.parse(receiveIndex)).into(imageViewPersonImgae);
               mDB.collection("Images").document("femaleimages")
                       .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                           @Override
                           public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                               String received=documentSnapshot.getString("details");
                               textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                               Log.i("ALL",received);
                           }
                       });
               mDB.collection("Images").document("femaleimages")
                       .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                           @Override
                           public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                               String received=documentSnapshot.getString("imgF3Price");
                               textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                               Log.i("ALL",received);
                           }
                       });
           }
        if(img4==bundle.getString(receiveIndex)){
            Picasso.get().load(Uri.parse(receiveIndex)).into(imageViewPersonImgae);
            mDB.collection("Images").document("femaleimages")
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            String received=documentSnapshot.getString("details");
                            textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                            Log.i("ALL",received);
                        }
                    });
            mDB.collection("Images").document("femaleimages")
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            String received=documentSnapshot.getString("imgF4Price");
                            textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                            Log.i("ALL",received);
                        }
                    });
        }
        if(img5==bundle.getString(receiveIndex)){
            Picasso.get().load(Uri.parse(receiveIndex)).into(imageViewPersonImgae);
            mDB.collection("Images").document("femaleimages")
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            String received=documentSnapshot.getString("details");
                            textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                            Log.i("ALL",received);
                        }
                    });
            mDB.collection("Images").document("femaleimages")
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            String received=documentSnapshot.getString("imgF5Price");
                            textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                            Log.i("ALL",received);
                        }
                    });
        }
        if(img6==bundle.getString(receiveIndex)){
            Picasso.get().load(Uri.parse(receiveIndex)).into(imageViewPersonImgae);
            mDB.collection("Images").document("femaleimages")
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            String received=documentSnapshot.getString("details");
                            textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                            Log.i("ALL",received);
                        }
                    });
            mDB.collection("Images").document("femaleimages")
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            String received=documentSnapshot.getString("imgF6Price");
                            textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                            Log.i("ALL",received);
                        }
                    });
        }
        if(img7==bundle.getString(receiveIndex)){
            Picasso.get().load(Uri.parse(receiveIndex)).into(imageViewPersonImgae);
            mDB.collection("Images").document("femaleimages")
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            String received=documentSnapshot.getString("details");
                            textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                            Log.i("ALL",received);
                        }
                    });
            mDB.collection("Images").document("femaleimages")
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            String received=documentSnapshot.getString("imgF7Price");
                            textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                            Log.i("ALL",received);
                        }
                    });
        }
        if(img8==bundle.getString(receiveIndex)){
            Picasso.get().load(Uri.parse(receiveIndex)).into(imageViewPersonImgae);
            mDB.collection("Images").document("femaleimages")
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            String received=documentSnapshot.getString("details");
                            textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                            Log.i("ALL",received);
                        }
                    });
            mDB.collection("Images").document("femaleimages")
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            String received=documentSnapshot.getString("imgF8Price");
                            textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                            Log.i("ALL",received);
                        }
                    });
        }
        if(img9==bundle.getString(receiveIndex)){
            Picasso.get().load(Uri.parse(receiveIndex)).into(imageViewPersonImgae);
            mDB.collection("Images").document("femaleimages")
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            String received=documentSnapshot.getString("details");
                            textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                            Log.i("ALL",received);
                        }
                    });
            mDB.collection("Images").document("femaleimages")
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            String received=documentSnapshot.getString("imgF9Price");
                            textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                            Log.i("ALL",received);
                        }
                    });
        }
        if(img10==bundle.getString(receiveIndex)){
            Picasso.get().load(Uri.parse(receiveIndex)).into(imageViewPersonImgae);
            mDB.collection("Images").document("femaleimages")
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            String received=documentSnapshot.getString("details");
                            textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                            Log.i("ALL",received);
                        }
                    });
            mDB.collection("Images").document("femaleimages")
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            String received=documentSnapshot.getString("imgF10Price");
                            textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                            Log.i("ALL",received);
                        }
                    });
        }
        if(img11==bundle.getString(receiveIndex)){
            Picasso.get().load(Uri.parse(receiveIndex)).into(imageViewPersonImgae);
            mDB.collection("Images").document("femaleimages")
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            String received=documentSnapshot.getString("details");
                            textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                            Log.i("ALL",received);
                        }
                    });
            mDB.collection("Images").document("femaleimages")
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            String received=documentSnapshot.getString("imgF11Price");
                            textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                            Log.i("ALL",received);
                        }
                    });
        }
        if(img12==bundle.getString(receiveIndex)){
            Picasso.get().load(Uri.parse(receiveIndex)).into(imageViewPersonImgae);
            mDB.collection("Images").document("femaleimages")
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            String received=documentSnapshot.getString("details");
                            textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                            Log.i("ALL",received);
                        }
                    });
            mDB.collection("Images").document("femaleimages")
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            String received=documentSnapshot.getString("imgF12Price");
                            textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                            Log.i("ALL",received);
                        }
                    });
        }
        if(img13==bundle.getString(receiveIndex)){
            Picasso.get().load(Uri.parse(receiveIndex)).into(imageViewPersonImgae);
            mDB.collection("Images").document("femaleimages")
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            String received=documentSnapshot.getString("details");
                            textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                            Log.i("ALL",received);
                        }
                    });
            mDB.collection("Images").document("femaleimages")
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            String received=documentSnapshot.getString("imgF13Price");
                            textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                            Log.i("ALL",received);
                        }
                    });
        }
        if(img14==bundle.getString(receiveIndex)){
            Picasso.get().load(Uri.parse(receiveIndex)).into(imageViewPersonImgae);
            mDB.collection("Images").document("femaleimages")
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            String received=documentSnapshot.getString("details");
                            textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                            Log.i("ALL",received);
                        }
                    });
            mDB.collection("Images").document("femaleimages")
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            String received=documentSnapshot.getString("imgF14Price");
                            textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                            Log.i("ALL",received);
                        }
                    });
        }
        if(img15==bundle.getString(receiveIndex)){
            Picasso.get().load(Uri.parse(receiveIndex)).into(imageViewPersonImgae);
            mDB.collection("Images").document("femaleimages")
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            String received=documentSnapshot.getString("details");
                            textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                            Log.i("ALL",received);
                        }
                    });
            mDB.collection("Images").document("femaleimages")
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            String received=documentSnapshot.getString("imgF15Price");
                            textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                            Log.i("ALL",received);
                        }
                    });
        }
        if(img16==bundle.getString(receiveIndex)){
            Picasso.get().load(Uri.parse(receiveIndex)).into(imageViewPersonImgae);
            mDB.collection("Images").document("femaleimages")
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            String received=documentSnapshot.getString("details");
                            textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                            Log.i("ALL",received);
                        }
                    });
            mDB.collection("Images").document("femaleimages")
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            String received=documentSnapshot.getString("imgF16Price");
                            textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                            Log.i("ALL",received);
                        }
                    });
        }
        if(img17==bundle.getString(receiveIndex)){
            Picasso.get().load(Uri.parse(receiveIndex)).into(imageViewPersonImgae);
            mDB.collection("Images").document("femaleimages")
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            String received=documentSnapshot.getString("details");
                            textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                            Log.i("ALL",received);
                        }
                    });
            mDB.collection("Images").document("femaleimages")
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            String received=documentSnapshot.getString("imgF17Price");
                            textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                            Log.i("ALL",received);
                        }
                    });
        }
        if(img18==bundle.getString(receiveIndex)){
            Picasso.get().load(Uri.parse(receiveIndex)).into(imageViewPersonImgae);
            mDB.collection("Images").document("femaleimages")
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            String received=documentSnapshot.getString("details");
                            textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                            Log.i("ALL",received);
                        }
                    });
            mDB.collection("Images").document("femaleimages")
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            String received=documentSnapshot.getString("imgF18Price");
                            textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                            Log.i("ALL",received);
                        }
                    });
        }
        if(img19==bundle.getString(receiveIndex)){
            Picasso.get().load(Uri.parse(receiveIndex)).into(imageViewPersonImgae);
            mDB.collection("Images").document("femaleimages")
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            String received=documentSnapshot.getString("details");
                            textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                            Log.i("ALL",received);
                        }
                    });
            mDB.collection("Images").document("femaleimages")
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            String received=documentSnapshot.getString("imgF19Price");
                            textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                            Log.i("ALL",received);
                        }
                    });
        }
        if(img20==bundle.getString(receiveIndex)){
            Picasso.get().load(Uri.parse(receiveIndex)).into(imageViewPersonImgae);
            mDB.collection("Images").document("femaleimages")
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            String received=documentSnapshot.getString("details");
                            textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                            Log.i("ALL",received);
                        }
                    });
            mDB.collection("Images").document("femaleimages")
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            String received=documentSnapshot.getString("imgF20Price");
                            textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                            Log.i("ALL",received);
                        }
                    });
        }






           buttonBuyNow.setOnClickListener(new View.OnClickListener() {
>>>>>>> Stashed changes
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),RazorPay.class);
                startActivity(intent);
            }
        });


        mDB.collection("Images").document("maleimages")
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                        String received=documentSnapshot.getString("details");
                        textViewAboutDesignInfo.setText(received.replace("\\n","\n"));
                        Log.i("ALL",received);
                    }
                });
        mDB.collection("Images").document("maleimages")
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                        String received=documentSnapshot.getString("imgM1Price");
                        textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                        Log.i("ALL",received);
                    }
                });
        //imageViewPersonImgae.setImageResource();


        if(img == img1){
            Picasso.get().load(img).into(imageViewPersonImgae);
           // Picasso.
           // Picasso.get().load(urlList.toString(img).)
            mDB.collection("Images").document("maleimages").addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    String received = documentSnapshot.getString("imgM1Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }
       /* if(img == img2){
            imageViewPersonImgae.setImageResource(Integer.parseInt((String) img));
            mDB.collection("Images").document("maleimages").addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    String received = documentSnapshot.getString("imgM1Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }
        if(img == img3){
            imageViewPersonImgae.setImageResource(Integer.parseInt((String) img));
            mDB.collection("Images").document("maleimages").addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    String received = documentSnapshot.getString("imgM1Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }
        if(img == img4){
            imageViewPersonImgae.setImageResource(Integer.parseInt((String) img));
            mDB.collection("Images").document("maleimages").addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    String received = documentSnapshot.getString("imgM1Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }
        if(img == img5){
            imageViewPersonImgae.setImageResource(Integer.parseInt((String) img));
            mDB.collection("Images").document("maleimages").addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    String received = documentSnapshot.getString("imgM1Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }
        if(img == img6){
            imageViewPersonImgae.setImageResource(Integer.parseInt((String) img));
            mDB.collection("Images").document("maleimages").addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    String received = documentSnapshot.getString("imgM1Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }
        if(img == img7){
            imageViewPersonImgae.setImageResource(Integer.parseInt((String) img));
            mDB.collection("Images").document("maleimages").addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    String received = documentSnapshot.getString("imgM1Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }
        if(img == img8){
            imageViewPersonImgae.setImageResource(Integer.parseInt((String) img));
            mDB.collection("Images").document("maleimages").addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    String received = documentSnapshot.getString("imgM1Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }
        if(img == img9){
            imageViewPersonImgae.setImageResource(Integer.parseInt((String) img));
            mDB.collection("Images").document("maleimages").addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    String received = documentSnapshot.getString("imgM1Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }
        if(img == img10){
            imageViewPersonImgae.setImageResource(Integer.parseInt((String) img));
            mDB.collection("Images").document("maleimages").addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    String received = documentSnapshot.getString("imgM1Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }
        if(img == img11){
            imageViewPersonImgae.setImageResource(Integer.parseInt((String) img));
            mDB.collection("Images").document("maleimages").addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    String received = documentSnapshot.getString("imgM1Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }
        if(img == img12){
            imageViewPersonImgae.setImageResource(Integer.parseInt((String) img));
            mDB.collection("Images").document("maleimages").addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    String received = documentSnapshot.getString("imgM1Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }
        if(img == img13){
            imageViewPersonImgae.setImageResource(Integer.parseInt((String) img));
            mDB.collection("Images").document("maleimages").addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    String received = documentSnapshot.getString("imgM1Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }
        if(img == img14){
            imageViewPersonImgae.setImageResource(Integer.parseInt((String) img));
            mDB.collection("Images").document("maleimages").addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    String received = documentSnapshot.getString("imgM1Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }
        if(img == img15){
            imageViewPersonImgae.setImageResource(Integer.parseInt((String) img));
            mDB.collection("Images").document("maleimages").addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    String received = documentSnapshot.getString("imgM1Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }
        if(img == img16){
            imageViewPersonImgae.setImageResource(Integer.parseInt((String) img));
            mDB.collection("Images").document("maleimages").addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    String received = documentSnapshot.getString("imgM1Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }
        if(img == img17){
            imageViewPersonImgae.setImageResource(Integer.parseInt((String) img));
            mDB.collection("Images").document("maleimages").addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    String received = documentSnapshot.getString("imgM1Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }
        if(img == img18){
            imageViewPersonImgae.setImageResource(Integer.parseInt((String) img));
            mDB.collection("Images").document("maleimages").addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    String received = documentSnapshot.getString("imgM1Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }
        if(img == img19){
            imageViewPersonImgae.setImageResource(Integer.parseInt((String) img));
            mDB.collection("Images").document("maleimages").addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    String received = documentSnapshot.getString("imgM1Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }
        if(img == img20){
            imageViewPersonImgae.setImageResource(Integer.parseInt((String) img));
            mDB.collection("Images").document("maleimages").addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    String received = documentSnapshot.getString("imgM1Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }
*/


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
