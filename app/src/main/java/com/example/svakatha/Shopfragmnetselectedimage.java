package com.example.svakatha;

import android.content.Intent;
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
    private ImageButton buttonAddtoCart,buttonBuyNow,imagebuttonBackWard,imagebuttonForWard;
    int angle = 0;
    FirebaseFirestore mDB;
    List<String> urlList=new ArrayList<>();
    String img1,img2,img3,img4,img5,img6,img7,img8,img9,img10,img11,img12,img13,img14,img15,img16,img17,img18,img19,img20;


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

        Bundle bundle = this.getArguments();
        String receivedIndex = bundle.getString("IndexValue");

        mDB.collection("Images").document("receivedIndex").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
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

        if(img1==bundle.get(receivedIndex)){
            Picasso.get().load(Uri.parse(receivedIndex)).into(imageViewPersonImgae);
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
                    String received = documentSnapshot.getString("imgF1Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }

        if(img2==bundle.get(receivedIndex)){
            Picasso.get().load(Uri.parse(receivedIndex)).into(imageViewPersonImgae);
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
                    String received = documentSnapshot.getString("imgF2Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }

        if(img3==bundle.get(receivedIndex)){
            Picasso.get().load(Uri.parse(receivedIndex)).into(imageViewPersonImgae);
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
                    String received = documentSnapshot.getString("imgF3Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }

        if(img4==bundle.get(receivedIndex)){
            Picasso.get().load(Uri.parse(receivedIndex)).into(imageViewPersonImgae);
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
                    String received = documentSnapshot.getString("imgF4Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }

        if(img5==bundle.get(receivedIndex)){
            Picasso.get().load(Uri.parse(receivedIndex)).into(imageViewPersonImgae);
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
                    String received = documentSnapshot.getString("imgF5Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }

        if(img6==bundle.get(receivedIndex)){
            Picasso.get().load(Uri.parse(receivedIndex)).into(imageViewPersonImgae);
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
                    String received = documentSnapshot.getString("imgF6Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }

        if(img7==bundle.get(receivedIndex)){
            Picasso.get().load(Uri.parse(receivedIndex)).into(imageViewPersonImgae);
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
                    String received = documentSnapshot.getString("imgF7Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }

        if(img8==bundle.get(receivedIndex)){
            Picasso.get().load(Uri.parse(receivedIndex)).into(imageViewPersonImgae);
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
                    String received = documentSnapshot.getString("imgF8Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }

        if(img9==bundle.get(receivedIndex)){
            Picasso.get().load(Uri.parse(receivedIndex)).into(imageViewPersonImgae);
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
                    String received = documentSnapshot.getString("imgF9Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }

        if(img10==bundle.get(receivedIndex)){
            Picasso.get().load(Uri.parse(receivedIndex)).into(imageViewPersonImgae);
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
                    String received = documentSnapshot.getString("imgF10Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }

        if(img11==bundle.get(receivedIndex)){
            Picasso.get().load(Uri.parse(receivedIndex)).into(imageViewPersonImgae);
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
                    String received = documentSnapshot.getString("imgF11Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }

        if(img12==bundle.get(receivedIndex)){
            Picasso.get().load(Uri.parse(receivedIndex)).into(imageViewPersonImgae);
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
                    String received = documentSnapshot.getString("imgF12Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }

        if(img13==bundle.get(receivedIndex)){
            Picasso.get().load(Uri.parse(receivedIndex)).into(imageViewPersonImgae);
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
                    String received = documentSnapshot.getString("imgF13Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }

        if(img14==bundle.get(receivedIndex)){
            Picasso.get().load(Uri.parse(receivedIndex)).into(imageViewPersonImgae);
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
                    String received = documentSnapshot.getString("imgF14Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }

        if(img15==bundle.get(receivedIndex)){
            Picasso.get().load(Uri.parse(receivedIndex)).into(imageViewPersonImgae);
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
                    String received = documentSnapshot.getString("imgF15Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }

        if(img16==bundle.get(receivedIndex)){
            Picasso.get().load(Uri.parse(receivedIndex)).into(imageViewPersonImgae);
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
                    String received = documentSnapshot.getString("imgF16Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }

        if(img17==bundle.get(receivedIndex)){
            Picasso.get().load(Uri.parse(receivedIndex)).into(imageViewPersonImgae);
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
                    String received = documentSnapshot.getString("imgF17Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }

        if(img18==bundle.get(receivedIndex)){
            Picasso.get().load(Uri.parse(receivedIndex)).into(imageViewPersonImgae);
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
                    String received = documentSnapshot.getString("imgF18Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }

        if(img19==bundle.get(receivedIndex)){
            Picasso.get().load(Uri.parse(receivedIndex)).into(imageViewPersonImgae);
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
                    String received = documentSnapshot.getString("imgF19Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }

        if(img20==bundle.get(receivedIndex)){
            Picasso.get().load(Uri.parse(receivedIndex)).into(imageViewPersonImgae);
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
                    String received = documentSnapshot.getString("imgF20Price");
                    textViewTransparentPricingInfo.setText(received.replace("\\n","\n"));
                    Log.i("ALL",received);
                }
            });
        }










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
