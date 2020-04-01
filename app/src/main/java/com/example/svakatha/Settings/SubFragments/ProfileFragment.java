package com.example.svakatha.Settings.SubFragments;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.svakatha.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class ProfileFragment extends Fragment {
    private ImageView imageViewprofilefragment;
    private ImageButton imagebuttonanalysis,imagebuttonupdate;
    private TextView textViewprofilefragment2,textViewprofilefragment3;
    private TextView textViewDetail1Title,textViewDetail2Title,textViewDetail3Title,textViewDetail4Title,textViewDetail5Title,
            textViewDetail6Title,textViewDetail7Title,textViewDetail8Title,textViewDetail9Title;
    private EditText editTextdetail1,editTextdetail2,editTextdetail3,editTextdetail4,editTextdetail5,editTextdetail6,
            editTextdetail7,editTextdetail8,editTextdetail9;
    ProgressBar progressBarProfileFragment;
    private Context mContext;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private View view;
    String userId;
    Map<String, String> data = new HashMap<>();


    public ProfileFragment(){
        // Required empty public constructor
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth= FirebaseAuth.getInstance();
        db= FirebaseFirestore.getInstance();
    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = null;
        mContext = getContext();
        view = inflater.inflate(R.layout.profile_fragment, container, false);
        imageViewprofilefragment=(ImageView)view.findViewById(R.id.imageViewprofilefragment);
        imagebuttonanalysis=(ImageButton)view.findViewById(R.id.imagebuttonanalysis);
        textViewprofilefragment2=(TextView)view.findViewById(R.id.textViewprofilefragment2);
        textViewprofilefragment3=(TextView)view.findViewById(R.id.textViewprofilefragment3);
        editTextdetail1=(EditText) view.findViewById(R.id.editTextdetail1);
        editTextdetail2=(EditText)view.findViewById(R.id.editTextdetail2);
        editTextdetail3=(EditText)view.findViewById(R.id.editTextdetail3);
        editTextdetail4=(EditText)view.findViewById(R.id.editTextdetail4);
        editTextdetail5=(EditText)view.findViewById(R.id.editTextdetail5);
        editTextdetail6=(EditText)view.findViewById(R.id.editTextdetail6);
        editTextdetail7=(EditText)view.findViewById(R.id.editTextdetail7);
        editTextdetail8=(EditText)view.findViewById(R.id.editTextdetail8);
        editTextdetail9=(EditText)view.findViewById(R.id.editTextdetail9);

        textViewDetail1Title=(TextView)view.findViewById(R.id.textViewDetail1Title);
        textViewDetail2Title=(TextView)view.findViewById(R.id.textViewDetail2Title);
        textViewDetail3Title=(TextView)view.findViewById(R.id.textViewDetail3Title);
        textViewDetail4Title=(TextView)view.findViewById(R.id.textViewDetail4Title);
        textViewDetail5Title=(TextView)view.findViewById(R.id.textViewDetail5Title);
        textViewDetail6Title=(TextView)view.findViewById(R.id.textViewDetail6Title);
        textViewDetail7Title=(TextView)view.findViewById(R.id.textViewDetail7Title);
        textViewDetail8Title=(TextView)view.findViewById(R.id.textViewDetail8Title);
        textViewDetail9Title=(TextView)view.findViewById(R.id.textViewDetail9Title);

        //imagebuttonupdate=view.findViewById(R.id.imagebuttonupdate);

        //progressbar animation
        ProgressBar mProgressBar = (ProgressBar)view.findViewById(R.id.progressBarProfileFragment);
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(mProgressBar, "progress", 0,30);
        ObjectAnimator progressAnimator2 = ObjectAnimator.ofInt(mProgressBar, "secondaryProgress", 30,70);
        progressAnimator.setDuration(500);
        progressAnimator.setInterpolator(new AccelerateInterpolator());
        progressAnimator.start();
        progressAnimator2.setDuration(500);
        progressAnimator2.setInterpolator(new AccelerateInterpolator());
        progressAnimator2.start();

        db.collection("users").document(mAuth.getCurrentUser().getUid()).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String finalProfileText = documentSnapshot.getString("FirstName");
                        String gender = documentSnapshot.getString("Gender");
                        String bodyweight = documentSnapshot.getString("Weight");
                        String height = documentSnapshot.getString("Height");
                        String skintone = documentSnapshot.getString("SkinTone");
                        String email = documentSnapshot.getString("Email");
                        String style = documentSnapshot.getString("Style");

                        textViewprofilefragment2.setText(finalProfileText);
                        editTextdetail1.setText(bodyweight,TextView.BufferType.EDITABLE);
                        editTextdetail2.setText(height,TextView.BufferType.EDITABLE);
                        editTextdetail3.setText(skintone,TextView.BufferType.EDITABLE);
                        editTextdetail4.setText(finalProfileText,TextView.BufferType.EDITABLE);
                        editTextdetail5.setText(email,TextView.BufferType.EDITABLE);
                        editTextdetail6.setText(style,TextView.BufferType.EDITABLE);
                        editTextdetail7.setText(gender,TextView.BufferType.EDITABLE);
                        editTextdetail8.setText(bodyweight,TextView.BufferType.EDITABLE);
                        editTextdetail9.setText(bodyweight,TextView.BufferType.EDITABLE);



                    }
                });

        editTextdetail1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Function", Toast.LENGTH_SHORT).show();
                String Weight = editTextdetail1.getText().toString();
                update(Weight);

            }
        });


        /*
                //code to insert value from edittext to database
                userId = mAuth.getCurrentUser().getUid();
                String Weight = editTextdetail1.getText().toString();
                String Height = editTextdetail2.getText().toString();
                String skintone = editTextdetail3.getText().toString();
                String name = editTextdetail4.getText().toString();
                String email = editTextdetail5.getText().toString();
                String style = editTextdetail6.getText().toString();
                String gender = editTextdetail7.getText().toString();


                Map<String, Object> user = new HashMap<>();
                if(!TextUtils.isEmpty(Height)){
                    user.put("Height", Height);}
                if(!TextUtils.isEmpty(Weight)){
                    user.put("Weight", Weight);}
                if(!TextUtils.isEmpty(skintone)){
                    user.put("SkinTone", skintone);}
                if(!TextUtils.isEmpty(name)){
                    user.put("FirstName", name);}
                if(!TextUtils.isEmpty(email)){
                    user.put("Email", email);}
                if(style=="Style" ||style=="Comfort"){
                    user.put("Style", style);
                }
                if(gender=="MALE" || gender=="FEMALE") {
                    user.put("Gender", gender);
                }
                db.collection("users").document(userId).set(user, SetOptions.merge());*/

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().setTitle("Setting");
    }

    private void update(String Weight){

        Map<String, Object> user = new HashMap<>();
        user.put("Weight", Weight);
        db.collection("users")
                .document(userId)
                .set(user, SetOptions.merge())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }});
        /*DocumentReference documentReference = db.collection("users").document(userId);
        documentReference.update("Weight", Weight)
        .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(mContext, "Updated", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(mContext, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });*/
    }
}
