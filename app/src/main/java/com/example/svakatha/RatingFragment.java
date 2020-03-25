package com.example.svakatha;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.svakatha.PermissionUtils.PermissionUtils;

import org.w3c.dom.Text;

import java.io.File;

public class RatingFragment extends Fragment {

    private static final int RESULT_OK = 0;
    private Context context;
    private ImageView imageViewCapturedImage,imageViewLikeDislike,imageViewLike;
    private ImageButton imageButtonCameraButton;
    private TextView textViewLikeDislikePercentage;
    private EditText editTextPercentage;
    private Button buttonChangeLikeDislike;
    static final int REQUEST_IMAGE_CAPTURE = 4;



    public RatingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getContext();
        View view = inflater.inflate(R.layout.fragment_rating,container,false);
        imageViewCapturedImage = (ImageView) view.findViewById(R.id.imageViewCapturedImage);
        imageViewLikeDislike = (ImageView) view.findViewById(R.id.imageViewLikeDislike);
        imageButtonCameraButton = (ImageButton) view.findViewById(R.id.imageButtonCameraButton);
        textViewLikeDislikePercentage = (TextView) view.findViewById(R.id.textViewLikeDislikePercentage);
        editTextPercentage = (EditText) view.findViewById(R.id.editTextPercentge);
        buttonChangeLikeDislike = (Button) view.findViewById(R.id.buttonChangeLikeDislike);
        imageViewLike = (ImageView) view.findViewById(R.id.imageViewLike);

        buttonChangeLikeDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int percentage = Integer.parseInt(editTextPercentage.getText().toString());
                if(percentage<50){
                    imageViewLikeDislike.setVisibility(View.VISIBLE);
                    imageViewLike.setVisibility(View.INVISIBLE);
                    textViewLikeDislikePercentage.setText(percentage+"%");
                }
                else{
                    imageViewLike.setVisibility(View.VISIBLE);
                    imageViewLikeDislike.setVisibility(View.INVISIBLE);
                    textViewLikeDislikePercentage.setText(percentage+"%");
                }
            }
        });

        imageButtonCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(context.getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        String[] selectstyle = new String[] {"Select Style","Casual", "Business Wear", "Party Wear", "Formal"};
        Spinner selectstylespnr = view.findViewById(R.id.spnr_selectstyle);
        ArrayAdapter<String> occasionAdapter =
                new ArrayAdapter<>(
                        context,
                        R.layout.dropdown_menu_popup_item,
                        selectstyle);

        selectstylespnr.setAdapter(occasionAdapter);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageViewCapturedImage.setImageBitmap(imageBitmap);
        }
    }

}