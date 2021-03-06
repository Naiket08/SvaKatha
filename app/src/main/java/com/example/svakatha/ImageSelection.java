package com.example.svakatha;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

public class ImageSelection extends AppCompatActivity {

    private static ImageButton btn1, btn2, btn3, btn4;
    private TextView textViewImageSelectionText2;
    int windowwidth;
    int screenCenter;
    public RelativeLayout parentView;
    private Context context;
    ArrayList<UserDataModel> userDataModelArrayList = new ArrayList<>();
    private static int index = 0;
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    Map<String, String> data = new HashMap<>();
    String imageCode;
    ImageView imageView;
    int i = 0;
    String currentID;

    @SuppressWarnings("deprecation")
    @SuppressLint({"NewApi", "ClickableViewAccessibility"})


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_selection);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        currentID = mAuth.getCurrentUser().getUid();
        getArrayData();
        context = getApplicationContext();
//test
        //progressbar animation
        ProgressBar mProgressBar = (ProgressBar) findViewById(R.id.progressBarImageSelection);
         ObjectAnimator progressAnimator = ObjectAnimator.ofInt(mProgressBar, "secondaryProgress", 70,100);
         progressAnimator.setDuration(500);
         progressAnimator.setInterpolator(new LinearInterpolator());
         progressAnimator.start();
//context = ImageSelection.this;
        parentView = findViewById(R.id.main_layoutview);
        windowwidth = getWindowManager().getDefaultDisplay().getWidth();
        screenCenter = windowwidth / 2;

        imageView = findViewById(R.id.userIMG);
        textViewImageSelectionText2 = (TextView) findViewById(R.id.textViewStyleGreet2);
        btn1 = findViewById(R.id.imagebuttonimageselectionHate_1);
        btn2 = findViewById(R.id.imagebuttonimageselectionNotSure_1);
        btn3 = findViewById(R.id.imagebuttonimageselectionLove_1);
        btn4 = findViewById(R.id.imageButtonimageSelectionScreenForward_1);


        Intent intent = getIntent();
        textViewImageSelectionText2.setTypeface(textViewImageSelectionText2.getTypeface(), Typeface.BOLD);
        final String name_image = intent.getStringExtra("Name_bodyshape");
        textViewImageSelectionText2.setText("Hi" + " " + name_image);

        /*db.collection("users").document(mAuth.getCurrentUser().getUid()).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String finalProfileText = documentSnapshot.getString("FirstName");
                        textViewImageSelectionText2.setText("Hi "+finalProfileText);
                        textViewImageSelectionText2.setTypeface(textViewImageSelectionText2.getTypeface(), Typeface.BOLD);
                    }
                });*/
        db.collection("users").document(currentID).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        final String gender = documentSnapshot.getString("Gender");
                        //Toast.makeText(getApplicationContext(),""+currentID,Toast.LENGTH_SHORT).show();
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

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Image Deleted", Toast.LENGTH_SHORT).show();
                if (index < 8) {
                    saveUserChoiceToDb(index);
                }
                if (index == 8) {
                    Toast.makeText(context, "Reached End", Toast.LENGTH_SHORT).show();
                    index = 8;
                    addParentView(index);
                    //Picasso.get().load(userDataModelArrayList.get(index).getUrl()).into(imageView);

                } else {
                    index = index + 1;
                    addParentView(index);
                    //Picasso.get().load(userDataModelArrayList.get(index).getUrl()).into(imageView);

                }


            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Not Sure About This One", Toast.LENGTH_SHORT).show();
                removeParentView(index);
                if (index < 8) {
                    saveUserChoiceToDb(index);
                }
                if (index == 8) {
                    Toast.makeText(context, "Reached End", Toast.LENGTH_SHORT).show();
                    index = 8;
                    addParentView(index);
                    //Picasso.get().load(userDataModelArrayList.get(index).getUrl()).into(imageView);

                } else {
                    index = index + 1;
                    addParentView(index);
                    //Picasso.get().load(userDataModelArrayList.get(index).getUrl()).into(imageView);

                }


            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
                removeParentView(index);
                if (index < 8) {
                    saveUserChoiceToDb(index);
                }
                if (index == 8) {
                    Toast.makeText(context, "Reached End", Toast.LENGTH_SHORT).show();
                    index = 8;
                    addParentView(index);
                    //Picasso.get().load(userDataModelArrayList.get(index).getUrl()).into(imageView);

                } else {
                    index = index + 1;
                    addParentView(index);
                    //Picasso.get().load(userDataModelArrayList.get(index).getUrl()).into(imageView);

                }


            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
////////////////////////
                showImage();
////////////////////////
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(ImageSelection.this, HostActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        //overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    }
                },2000);
                //Toast.makeText(ImageSelection.this, "Next Page To My Closet", Toast.LENGTH_SHORT).show();
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
        // currentID = mAuth.getCurrentUser().getUid();
        db.collection("users").document(currentID).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        final String gender = documentSnapshot.getString("Gender");
                        if (gender.equals("MALE")) {
                            db.collection("Images").document("ImageURLs").get()
                                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                        @Override
                                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                                            String durl = documentSnapshot.getString("url" + i);
                                            model.setUrl(durl);
                                            if (i == 1) {
                                                onFirstUrlSet();
                                            }

                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                                            Log.i("hi", e.toString());
                                        }
                                    });
                        } else {
                            db.collection("Images").document("femaleimageselection").get()
                                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                        @Override
                                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                                            String durl = documentSnapshot.getString("url" + i);
                                            model.setUrl(durl);
                                            if (i == 1) {
                                                onFirstUrlSet();
                                            }
                                            // Log.i("Hi", durl);
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
                    }

                });
    }

    private void addParentView(int index) {
        imageView=findViewById(R.id.userIMG);
        //new ImageLoadAsyncTask(userDataModelArrayList.get(index).getUrl(),imageView).execute();
        //Picasso.get().load(userDataModelArrayList.get(index).getUrl()).into(imageView);
        Glide.with(this).load(userDataModelArrayList.get(index).getUrl()).skipMemoryCache(true).into(imageView);
    }

    public class ImageLoadAsyncTask extends AsyncTask<Void, Void, Bitmap> {
        private String url;
           private ImageView imageView;

        public ImageLoadAsyncTask(String url,ImageView imageView) {
            this.url = url;
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                URL urlConnection = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlConnection.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
           // Picasso.get().load(url).placeholder(R.drawable.progress_animation).into(imageView);
           imageView.setImageBitmap(result);
        }
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
        addParentView(index);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //important to popup image
    public void showImage() {
        Dialog builder = new Dialog(this,R.style.AppBaseTheme);
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                //nothing;
            }
        });

        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        builder.setContentView(R.layout.background);;
        builder.show();
        final Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                builder.dismiss(); // when the task active then close the dialog
                t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
            }
        }, 3000); // after 2 second (or 2000 miliseconds), the task will be active.


    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////


}
