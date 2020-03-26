package com.example.svakatha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import com.example.svakatha.Adapter.RecyclerViewAdapter;

public class ShapeBody extends AppCompatActivity {

    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter recyclerViewAdapter;
    private TextView textViewBodyShapeText2, textViewBodyShapeText3, textViewBodyShapeText4;

    int []arr={R.drawable.pear,R.drawable.rectangle,R.drawable.hourglass,R.drawable.inverted,R.drawable.oval};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_body);

        textViewBodyShapeText2 = (TextView) findViewById(R.id.textViewBodyShapeText2_1);


        Intent intent = getIntent();
        textViewBodyShapeText2.setTypeface(textViewBodyShapeText2.getTypeface(), Typeface.BOLD);
        String name_bodyshape = intent.getStringExtra("Name_skintone");
        textViewBodyShapeText2.setText("Hi"+" "+name_bodyshape);

        recyclerView=findViewById(R.id.recycler);
        layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter=new RecyclerViewAdapter(arr);

        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setHasFixedSize(true);
    }
}
