package com.example.svakatha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.svakatha.Adapter.RecyclerViewAdapter;
import com.example.svakatha.Model.ShapeBodyModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ShapeBody extends AppCompatActivity {

    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter recyclerViewAdapter;
    private TextView textViewBodyShapeText2, textViewBodyShapeText3, textViewBodyShapeText4;
    FirebaseFirestore mDb;
    private StorageReference mStorageRef;
    List<ShapeBodyModel> modelList;
    FirebaseAuth mAuth;
    String genderStatus;
    ImageButton imageButtonShapeBodyScreenForward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        modelList= new ArrayList<>();
        recyclerViewAdapter=new RecyclerViewAdapter(modelList);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mAuth=FirebaseAuth.getInstance();
        mDb=FirebaseFirestore.getInstance();
        mDb.collection("users").document(mAuth.getCurrentUser().getUid())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                   genderStatus=documentSnapshot.getString("Gender");
                if(genderStatus.equals("FEMALE")) {
                  //  Toast.makeText(this, "female", Toast.LENGTH_SHORT).show();
                    mStorageRef.child("BodyShapeFemale").listAll()
                            .addOnSuccessListener(new OnSuccessListener<ListResult>() {
                                @Override
                                public void onSuccess(ListResult listResult) {
                                    for (StorageReference ref : listResult.getItems()) {
                                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {
                                                modelList.add(new ShapeBodyModel(uri));
                                                recyclerViewAdapter.notifyDataSetChanged();
                                            }
                                        });
                                    }
                                }
                            });
                }else {
                    //Toast.makeText(this, "male", Toast.LENGTH_SHORT).show();
                    mStorageRef.child("BodyShapeMale").listAll()
                            .addOnSuccessListener(new OnSuccessListener<ListResult>() {
                                @Override
                                public void onSuccess(ListResult listResult) {
                                    for (StorageReference ref : listResult.getItems()) {
                                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {
                                                modelList.add(new ShapeBodyModel(uri));
                                                recyclerViewAdapter.notifyDataSetChanged();
                                            }
                                        });
                                    }
                                }
                            });
                }
            }
        });


        setContentView(R.layout.activity_shape_body);

        textViewBodyShapeText2 = (TextView) findViewById(R.id.textViewBodyShapeText2_1);
        imageButtonShapeBodyScreenForward = (ImageButton) findViewById(R.id.imageButtonShapeBodyScreenForward_1);

        Intent intent = getIntent();
        textViewBodyShapeText2.setTypeface(textViewBodyShapeText2.getTypeface(), Typeface.BOLD);
        String name_bodyshape = intent.getStringExtra("Name_skintone");
        textViewBodyShapeText2.setText("Hi"+" "+name_bodyshape);

        recyclerView=findViewById(R.id.recycler);
        layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter=new RecyclerViewAdapter(modelList);

        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setHasFixedSize(true);

        imageButtonShapeBodyScreenForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShapeBody.this,ImageSelection.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("Name_ShapeBody",name_bodyshape);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });


    }


}
