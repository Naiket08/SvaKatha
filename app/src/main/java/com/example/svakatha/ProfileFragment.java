package com.example.svakatha;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ProfileFragment extends Fragment {
    private ImageView imageViewprofilefragment;
    private ImageButton imagebuttonanalysis;
    private TextView textviewdetail1,textviewdetail2,textviewdetail3,textviewdetail4,textviewdetail5,textviewdetail6,textviewdetail7,textviewdetail8,
            textviewdetail9,textViewprofilefragment2,textViewprofilefragment3;
    private Context mContext;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private View view;
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
        textviewdetail1=(TextView)view.findViewById(R.id.textviewdetail1);
        textviewdetail2=(TextView)view.findViewById(R.id.textviewdetail2);
        textviewdetail3=(TextView)view.findViewById(R.id.textviewdetail3);
        textviewdetail4=(TextView)view.findViewById(R.id.textviewdetail4);
        textviewdetail5=(TextView)view.findViewById(R.id.textviewdetail5);
        textviewdetail6=(TextView)view.findViewById(R.id.textviewdetail6);
        textviewdetail7=(TextView)view.findViewById(R.id.textviewdetail7);
        textviewdetail8=(TextView)view.findViewById(R.id.textviewdetail8);
        textviewdetail9=(TextView)view.findViewById(R.id.textviewdetail9);

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
                        textviewdetail1.setText("<b>Body Shape</b> /n"+bodyweight);
                        textviewdetail2.setText("<b>Height</b> /n"+height);
                        textviewdetail3.setText("<b>Skin Tone</b> /n"+skintone);
                        textviewdetail4.setText("<b>Name</b> /n"+finalProfileText);
                        textviewdetail5.setText("<b>Email Address</b> /n"+email);
                        textviewdetail6.setText("<b>Style/Comfort</b> /n"+style);
                        textviewdetail7.setText("<b>Gender</b> /n"+gender);
                        textviewdetail8.setText("<b>Body Shape</b> /n"+bodyweight);
                        textviewdetail9.setText("<b>Body Shape</b> /n"+bodyweight);

                    }
                });

        return view;
    }

}
