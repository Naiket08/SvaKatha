package com.example.svakatha;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ImageSelection extends AppCompatActivity {

    private ImageView  imageViewImageSelectionScreenHeader,imageViewPerson;
    private TextView textViewImageSelectionText,textViewImageSelectionText2,textViewImageSelectionText3,textViewImageSelectionHate,textViewImageSelectionNotSure,textViewImaegSelectionLove;
    private ImageButton imageButtonImageSelectionHate,imageButtonImageSelectionNotSure,imageButtonImageSelectionLove;
    private ProgressBar progressBarImageSelectionScreen;
    private Button buttonFinish;
    private static ImageButton btn1, btn2, btn3,btn4;
    int windowwidth;
    int screenCenter;
    public RelativeLayout parentView;
    private Context context;
    ArrayList<UserDataModel> userDataModelArrayList;
    private static int index = 0;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();
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
        //casting of ImageView
        imageViewImageSelectionScreenHeader=(ImageView)findViewById(R.id.imageViewImageSelectionScreenHeader_1);
        imageViewPerson=(ImageView)findViewById(R.id.imageofPerson);
        //casting of TextView
        textViewImageSelectionText=(TextView)findViewById(R.id.textViewImageSelectionText_1);
        textViewImageSelectionText2=(TextView)findViewById(R.id.textViewImageSelectionText2_1);
        textViewImageSelectionText3=(TextView)findViewById(R.id.textViewImageSelectionText3_1);
        textViewImageSelectionHate=(TextView)findViewById(R.id.textViewHateIt);
        textViewImageSelectionNotSure=(TextView)findViewById(R.id.textViewNotSure);
        textViewImaegSelectionLove=(TextView)findViewById(R.id.textViewLovedIt);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        //casting of ImageButton
        imageButtonImageSelectionHate=(ImageButton)findViewById(R.id.imageButtonHateIt);
        imageButtonImageSelectionNotSure=(ImageButton)findViewById(R.id.imageButtonNotSure);
        imageButtonImageSelectionLove=(ImageButton)findViewById(R.id.imageButtonLovedIt);
        buttonFinish=(Button)findViewById(R.id.buttonFinish);
        //casting of ProgressBar
        progressBarImageSelectionScreen=(ProgressBar)findViewById(R.id.progressBarImageSelectionScreen_1);


      /*  imageButtonImageSelectionScreenForward.setOnClickListener(new View.OnClickListener() {

        context = ImageSelection.this;

        parentView = findViewById(R.id.relative_layout);

        windowwidth = getWindowManager().getDefaultDisplay().getWidth();

        screenCenter = windowwidth / 2;

        userDataModelArrayList = new ArrayList<>();

        btn1 = findViewById(R.id.imagebuttonimageselectionHate_1);
        btn2 = findViewById(R.id.imagebuttonimageselectionNotSure_1);
        btn3 = findViewById(R.id.imagebuttonimageselectionLove_1);
        btn4 = findViewById(R.id.imageButtonimageSelectionScreenForward_1);
        imageView = (ImageView) findViewById(R.id.userIMG);
        imageView.setImageResource(R.drawable.imagelast);

        getArrayData();

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
            public void onClick(View view) {
            }
        });
        */
      imageButtonImageSelectionHate.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Toast.makeText(ImageSelection.this, "Image is deleted", Toast.LENGTH_SHORT).show();
              imageButtonImageSelectionHate.isPressed();
          }
      });
      imageButtonImageSelectionNotSure.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Toast.makeText(ImageSelection.this, "not sure about this image", Toast.LENGTH_SHORT).show();
              imageButtonImageSelectionNotSure.isPressed();
          }
      });
      imageButtonImageSelectionLove.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Toast.makeText(ImageSelection.this, "image is inserted", Toast.LENGTH_SHORT).show();
          }
      });
      buttonFinish.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Toast.makeText(ImageSelection.this, "Next Page", Toast.LENGTH_SHORT).show();
          }
      });

        DatabaseReference databaseReference = firebaseDatabase.getReference(auth.getCurrentUser().getUid());
        String currentID = auth.getCurrentUser().getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final DocumentReference documentReference = db.collection("users").document(currentID);
        documentReference.get();

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ImageSelection.this, "Not Sure About This One", Toast.LENGTH_SHORT).show();
                if (index == 8) {
                    index = 0;
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
                    index = 0;
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
                        String finalProfileText = documentSnapshot.getString("FirstName");
                        textViewImageSelectionText.setText("Hi "+finalProfileText);

                    }

                });

    }
}
