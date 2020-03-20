package com.example.svakatha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ImageSelection extends AppCompatActivity {

    private static ImageButton btn1, btn2, btn3,btn4;
    private TextView textViewImageSelectionText2;
    int windowwidth;
    int screenCenter;
    public RelativeLayout parentView;
    private Context context;
    ArrayList<UserDataModel> userDataModelArrayList;
    private static int index = 0;
    FirebaseFirestore db;
    FirebaseAuth mAuth ;
    Map<String, String> data = new HashMap<>();
    String imageCode;
    ImageView imageView;
    int i = 0;

    @SuppressWarnings("deprecation")
    @SuppressLint({"NewApi", "ClickableViewAccessibility"})


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_selection);

        context = ImageSelection.this;

        parentView = findViewById(R.id.main_layoutview);

        windowwidth = getWindowManager().getDefaultDisplay().getWidth();

        screenCenter = windowwidth / 2;

        userDataModelArrayList = new ArrayList<>();
        mAuth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();
        textViewImageSelectionText2 = (TextView) findViewById(R.id.textViewStyleGreet2);
        btn1 = findViewById(R.id.imagebuttonimageselectionHate_1);
        btn2 = findViewById(R.id.imagebuttonimageselectionNotSure_1);
        btn3 = findViewById(R.id.imagebuttonimageselectionLove_1);
        btn4 = findViewById(R.id.imageButtonimageSelectionScreenForward_1);
        imageView = findViewById(R.id.userIMG);
        getArrayData();

        db.collection("users").document(mAuth.getCurrentUser().getUid()).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String finalProfileText = documentSnapshot.getString("FirstName");
                        textViewImageSelectionText2.setText("Hi "+finalProfileText);
                    }
                });

//        final LayoutInflater inflate = (LayoutInflater) ImageSelection.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        final View containerView = inflate.inflate(R.layout.activity_image_selection, null);
//        //RelativeLayout relativeLayoutContainer = (RelativeLayout) containerView.findViewById(R.id.relative_container);
//
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        containerView.setLayoutParams(layoutParams);
//        addParentView(containerView, index);

//        Log.i("Status",userDataModelArrayList.get(2).getUrl());
        Picasso.get().load(userDataModelArrayList.get(index).getUrl()).into(imageView);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ImageSelection.this, "ImageDeleted", Toast.LENGTH_SHORT).show();
                if (index == 8) {
                    Toast.makeText(ImageSelection.this, "Reached End Of The Suggestions.", Toast.LENGTH_LONG).show();
                    index = 8;
                } else {
                    index++;
                }
                Picasso.get().load(userDataModelArrayList.get(index).getUrl()).into(imageView);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ImageSelection.this, "Not Sure About This One", Toast.LENGTH_SHORT).show();
                if (index == 8) {
                    Toast.makeText(ImageSelection.this, "Reached End Of The Suggestions.", Toast.LENGTH_LONG).show();
                    index = 8;
                } else {
                    index++;
                }
                Picasso.get().load(userDataModelArrayList.get(index).getUrl()).into(imageView);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserChoiceToDb(i);
                Toast.makeText(ImageSelection.this, "Image Saved", Toast.LENGTH_SHORT).show();
                if (index == 8) {
                    Toast.makeText(ImageSelection.this, "Reached End Of The Suggestions.", Toast.LENGTH_LONG).show();
                    index = 8;
                } else {
                    index++;
                }
                Picasso.get().load(userDataModelArrayList.get(index).getUrl()).into(imageView);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ImageSelection.this, "Next Page To My Closet", Toast.LENGTH_SHORT).show();
            }
        });

        //  DatabaseReference databaseReference = firebaseDatabase.getReference(auth.getCurrentUser().getUid());


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

    }

    public void settingURL(final UserDataModel model, final int i) {
        db.collection("Images").document("ImageURLs").get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String durl = documentSnapshot.getString("url" + i);
                        model.setUrl(durl);
                        Log.i("Hi", durl);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                        Log.i("hi", e.toString());
                    }
                });
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

    @Override
    protected void onStart() {
        super.onStart();
    }
}