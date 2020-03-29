package com.example.svakatha;

import android.Manifest;
import android.annotation.SuppressLint;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.svakatha.PermissionUtils.PermissionUtils;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static android.app.Activity.RESULT_OK;

public class RatingFragment extends Fragment {

    private Context context;
    private ImageView imageViewCapturedImage,imageViewLikeDislike,imageViewLike;
    private TextView textViewLikeDislikePercentage;
    //private EditText editTextPercentage;
    //private Button buttonChangeLikeDislike;
    private static final int CAMERA_PERMISSIONS_REQUEST = 2;
    private static final int CAMERA_IMAGE_REQUEST = 3;
    private static final String FILE_NAME = "temp.jpg";
    Uri uri;
    int a;

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
        ImageButton imageButtonCameraButton = (ImageButton) view.findViewById(R.id.imageButtonCameraButton);
        textViewLikeDislikePercentage = (TextView) view.findViewById(R.id.textViewLikeDislikePercentage);
//        editTextPercentage = (EditText) view.findViewById(R.id.editTextPercentge);
//        buttonChangeLikeDislike = (Button) view.findViewById(R.id.buttonChangeLikeDislike);
        imageViewLike = (ImageView) view.findViewById(R.id.imageViewLike);


        imageButtonCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCamera();
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
        imageViewCapturedImage.setImageURI(uri);
        if(a<50){
            imageViewLikeDislike.setVisibility(View.VISIBLE);
            imageViewLike.setVisibility(View.INVISIBLE);
            textViewLikeDislikePercentage.setText(a+"%");
        }
        else{
            imageViewLike.setVisibility(View.VISIBLE);
            imageViewLikeDislike.setVisibility(View.INVISIBLE);
            textViewLikeDislikePercentage.setText(a+"%");
        }

        return view;
    }
    private void startCamera() {
        if (PermissionUtils.requestPermission(
                getActivity(),
                CAMERA_PERMISSIONS_REQUEST,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Uri photoUri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", getCameraFile());
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivityForResult(intent, CAMERA_IMAGE_REQUEST);
        }
    }

    private File getCameraFile() {
        File dir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return new File(dir, FILE_NAME);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_IMAGE_REQUEST && resultCode == RESULT_OK) {
            try {
                Toast.makeText(context, "Image Captured", Toast.LENGTH_LONG).show();
                Uri photoUri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", getCameraFile());
                uri = photoUri;
                imageViewCapturedImage.setImageURI(uri);
                final int percentage = new Random().nextInt(41) + 30;
                a = percentage;

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
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uri = null;
    }
}
