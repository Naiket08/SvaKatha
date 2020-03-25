package com.example.svakatha;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShopClothes extends Fragment {
    
    private TextView textViewClosetSugestions,textViewAddDesign,textViewSeason;
    private ImageView imageViewShopScreenGreet;
    private ImageButton imageButtonPersonClothesSelection,imageButtonForward,imagebuttonbackward;
    private Context mContext;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private View view;
    ArrayList<UserDataModel> userDataModelArrayList;
    private static int index = 0;
    Map<String, String> data = new HashMap<>();
    String imageCode;
    int i = 0;

    public ShopClothes() {
        // Required empty public constructor
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userDataModelArrayList = new ArrayList<>();
        mAuth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();
    }


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = null;
        mContext = getContext();
        view = inflater.inflate(R.layout.shopfragment,container,false);
        textViewClosetSugestions=(TextView)view.findViewById(R.id.textViewClosetSugestion_1);
        textViewAddDesign=(TextView)view.findViewById(R.id.textViewAddDesign_1);
        textViewSeason=(TextView)view.findViewById(R.id.textViewSeason_1);
        imageButtonForward=(ImageButton)view.findViewById(R.id.imageButtonForward);
        imagebuttonbackward=(ImageButton)view.findViewById(R.id.imageButtonBackward);

        
        imageViewShopScreenGreet=(ImageView)view.findViewById(R.id.imageViewShopScreenGreet_1);
        
        imageButtonPersonClothesSelection=(ImageButton)view.findViewById(R.id.imageButtonPersonClothesSelection_1);
        getArrayData();
        db.collection("users").document(mAuth.getCurrentUser().getUid()).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String finalProfileText = documentSnapshot.getString("FirstName");
                        textViewAddDesign.setText(finalProfileText+", add these design \n" +
                                "to your Closet.");
                        textViewAddDesign.setTypeface(textViewAddDesign.getTypeface(), Typeface.BOLD);
                    }
                });
        imageButtonPersonClothesSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swapFragment();
            }
        });
        imageButtonForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(mContext, "", Toast.LENGTH_SHORT).show();
                removeParentView(index);
                if (index < 10) {
                    //saveUserChoiceToDb(index);
                }
                if (index == 10) {
                    Toast.makeText(mContext, "Reached End", Toast.LENGTH_SHORT).show();
                    index = 10;
                    Picasso.get().load(userDataModelArrayList.get(index).getUrl()).into(imageButtonPersonClothesSelection);
                }
                if(index == 0) {
                    Toast.makeText(mContext, "forward", Toast.LENGTH_SHORT).show();
                }
                    else
                 {
                    index = index - 1;
                    Picasso.get().load(userDataModelArrayList.get(index).getUrl()).into(imageButtonPersonClothesSelection);
                }



            }
        });
        imagebuttonbackward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(mContext, "", Toast.LENGTH_SHORT).show();
                removeParentView(index);
                if (index < 10) {
                   // saveUserChoiceToDb(index);
                }
                if (index == 10) {
                    Toast.makeText(mContext, "Reached End", Toast.LENGTH_SHORT).show();
                    index = 10;
                    Picasso.get().load(userDataModelArrayList.get(index).getUrl()).into(imageButtonPersonClothesSelection);
                } else {
                    index = index + 1;
                    Picasso.get().load(userDataModelArrayList.get(index).getUrl()).into(imageButtonPersonClothesSelection);
                }


            }
        });


        return view;
    }
    private void swapFragment(){
        Shopfragmnetselectedimage fragment1 = new Shopfragmnetselectedimage();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.host_fragment, fragment1);
        fragmentTransaction.commit();
    }
    private void getArrayData() {

        UserDataModel model = new UserDataModel();
        model.setName("Cloth 1 ");
        model.setImageCode("1");
        settingURL(model, 1);
        userDataModelArrayList.add(model);


        UserDataModel model2 = new UserDataModel();
        model2.setName("Cloth 2 ");
        model2.setImageCode("2");
        settingURL(model2, 2);
        userDataModelArrayList.add(model2);

        UserDataModel model3 = new UserDataModel();
        model3.setName("Cloth 3 ");
        model3.setImageCode("3");
        settingURL(model3, 3);
        userDataModelArrayList.add(model3);


        UserDataModel model4 = new UserDataModel();
        model4.setName("Cloth 4 ");
        model4.setImageCode("4");
        settingURL(model4, 4);
        userDataModelArrayList.add(model4);


        UserDataModel model5 = new UserDataModel();
        model5.setName("Cloth 5 ");
        model5.setImageCode("5");
        settingURL(model5, 5);
        userDataModelArrayList.add(model5);

        UserDataModel model6 = new UserDataModel();
        model6.setName("Cloth 6 ");
        model6.setImageCode("6");
        settingURL(model6, 6);
        userDataModelArrayList.add(model6);


        UserDataModel model7 = new UserDataModel();
        model7.setName("Cloth 7 ");
        model7.setImageCode("7");
        settingURL(model7, 7);
        userDataModelArrayList.add(model7);


        UserDataModel model8 = new UserDataModel();
        model8.setName("Cloth 8 ");
        model8.setImageCode("8");
        settingURL(model8, 8);
        userDataModelArrayList.add(model8);


        UserDataModel model9 = new UserDataModel();
        model9.setName("Cloth 9 ");
        model9.setImageCode("9");
        settingURL(model9, 9);
        userDataModelArrayList.add(model9);

        UserDataModel model10 = new UserDataModel();
        model9.setName("Cloth 10 ");
        model9.setImageCode("10");
        settingURL(model10, 10);
        userDataModelArrayList.add(model10);

        UserDataModel model11 = new UserDataModel();
        model9.setName("Cloth 11 ");
        model9.setImageCode("11");
        settingURL(model11, 11);
        userDataModelArrayList.add(model11);

    }

    public void settingURL(final UserDataModel model, final int i) {

        db.collection("Images").document("2").get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String durl = documentSnapshot.getString("img" + i);
                        model.setUrl(durl);
                        if(i==1){
                            onFirstUrlSet();
                        }
                        // Log.i("Hi", durl);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(mContext, e.toString(), Toast.LENGTH_SHORT).show();
                        Log.i("hi", e.toString());
                    }
                });
    }


    private void removeParentView(int index) {

    }

    public void saveUserChoiceToDb(int index) {
        String uId = mAuth.getCurrentUser().getUid();
        imageCode = userDataModelArrayList.get(index).getImageCode();
        // Log.i("hi",imageCode);
        ChoiceModel choiceModel = new ChoiceModel();
        choiceModel.setChoice(imageCode);
        //data.put("userchoice",imageCode);
        //db.collection("users").document(uId).set(data, SetOptions.merge() );
        db.collection("users").document(uId).collection("Choices").document().set(choiceModel);
        //db.collection("users").document(uId).set(choiceModel, SetOptions.merge() );
    }



    public void onFirstUrlSet() {
        Picasso.get().load(userDataModelArrayList.get(index).getUrl()).into(imageButtonPersonClothesSelection);
    }

}
