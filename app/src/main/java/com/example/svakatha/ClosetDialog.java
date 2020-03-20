package com.example.svakatha;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import com.example.svakatha.PermissionUtils.PermissionUtils;

public class ClosetDialog extends DialogFragment {

    private static final int GALLERY_PERMISSIONS_REQUEST = 0;
    private static final int GALLERY_IMAGE_REQUEST = 1;
    private static final int CAMERA_PERMISSIONS_REQUEST = 2;
    private static final int CAMERA_IMAGE_REQUEST = 3;
    private static final String FILE_NAME = "temp.jpg";

    private Context mContext;
    private FragmentActivity fa;
    private ClosetFragment fragment;

    private String[] garment = new String[] {"Type of garment","Item 1", "Item 2", "Item 3", "Item 4"};
    private String[] occasion = new String[] {"Occasion","Item 1", "Item 2", "Item 3", "Item 4"};

    ClosetDialog(Context mContext, FragmentActivity fa, ClosetFragment fragment) {
        this.mContext = mContext;
        this.fa = fa;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onResume() {

        int width = getResources().getDimensionPixelSize(R.dimen._303sdp);
        int height = getResources().getDimensionPixelSize(R.dimen._435sdp);
        getDialog().getWindow().setLayout(width, height);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        super.onResume();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_fragment_closet, container, false);

        setupGarmentMenu(view);
        setupOccasionMenu(view);

        setupButtons(view);

        return view;
    }

    private void setupGarmentMenu(View view) {
        ArrayAdapter<String> garmentAdapter =
                new ArrayAdapter<>(
                        mContext,
                        R.layout.dropdown_menu_popup_item,
                        garment);

        Spinner garmentMenu =
                view.findViewById(R.id.garment_menu);
        garmentMenu.setAdapter(garmentAdapter);
    }

    private void setupOccasionMenu(View view) {
        ArrayAdapter<String> occasionAdapter =
                new ArrayAdapter<>(
                        mContext,
                        R.layout.dropdown_menu_popup_item,
                        occasion);

        Spinner occasionMenu =
                view.findViewById(R.id.occasion_menu);
        occasionMenu.setAdapter(occasionAdapter);
    }

    private void setupButtons(View view) {

        AppCompatImageButton btnUpload = view.findViewById(R.id.btn_upload);
        AppCompatImageButton btnCancel = view.findViewById(R.id.btn_cancel);

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder
                        .setMessage("Upload a Picture")
                        .setPositiveButton("Gallery", (dialog, which) -> startGalleryChooser())
                        .setNegativeButton("Camera", (dialog, which) -> startCamera());
                builder.create().show();

                dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

    private void startGalleryChooser() {
        if (PermissionUtils.requestPermission(fa, GALLERY_PERMISSIONS_REQUEST, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            fragment.startActivityForResult(Intent.createChooser(intent, "Select a photo"),
                    GALLERY_IMAGE_REQUEST);
        }
    }

    private void startCamera() {
        if (PermissionUtils.requestPermission(
                fa,
                CAMERA_PERMISSIONS_REQUEST,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Uri photoUri = FileProvider.getUriForFile(mContext, mContext.getApplicationContext().getPackageName() + ".provider", getCameraFile());
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            fragment.startActivityForResult(intent, CAMERA_IMAGE_REQUEST);
        }
    }

    private File getCameraFile() {
        File dir = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return new File(dir, FILE_NAME);
    }


}
