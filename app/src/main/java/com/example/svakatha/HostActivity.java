package com.example.svakatha;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.svakatha.Closet.ClosetFragment;
import com.example.svakatha.PermissionUtils.PermissionUtils;
import com.example.svakatha.Settings.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class HostActivity extends AppCompatActivity {

    private static final String FILE_NAME = "temp.jpg";
    private static final int GALLERY_PERMISSIONS_REQUEST = 0;
    private static final int GALLERY_IMAGE_REQUEST = 1;
    private static final int CAMERA_PERMISSIONS_REQUEST = 2;
    private static final int CAMERA_IMAGE_REQUEST = 3;
    private ClosetFragment closetFragment;
    private ShopClothes shopClothes;
    private RatingFragment ratingFragment;
    private SettingsFragment settingsFragment;
    int result = 0;
    BottomNavigationView bnv;
    private boolean doubleBackToExitPressedOnce = false;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        bnv = findViewById(R.id.bottom_navigation);

        bnv.setItemIconTintList(null);

        bnv.setOnNavigationItemSelectedListener(navListener);

        bnv.setSelectedItemId(R.id.closet);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            switch (item.getItemId()) {
                case R.id.closet:
                    result = 1;
                    if (closetFragment == null) {
                        closetFragment = new ClosetFragment();
                    }
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.host_fragment,
                                    closetFragment).commit();
                    setTitle("My Closet");
                    break;
                case R.id.shop:
                    result = 2;
                    if (shopClothes == null) {
                        shopClothes = new ShopClothes();
                    }
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.host_fragment,
                                    shopClothes).commit();
                    setTitle("Closet Suggestion");
                    //TODO: add fragment 2
                    break;
                case R.id.rating:
                    result = 3;
                    if (ratingFragment == null) {
                        ratingFragment = new RatingFragment();
                    }
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.host_fragment,
                                    ratingFragment).commit();
                    setTitle("Rating");
                    break;
                case R.id.setting:
                    result = 4;
                    if (settingsFragment == null) {
                        settingsFragment = new SettingsFragment();
                    }
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.host_fragment,
                                    settingsFragment).commit();
                    setTitle("Setting");
                    //TODO: add fragment 4

            }
            return true;
        }
    };

    private void makeToast(String text) {
        Toast.makeText(this,"Shop clicked",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_PERMISSIONS_REQUEST:
                if (PermissionUtils.permissionGranted(requestCode, CAMERA_PERMISSIONS_REQUEST, grantResults)) {
                    startCamera();
                } else {
                    Toast.makeText(this,"Please grand permission to use camera from settings",Toast.LENGTH_LONG).show();
                }
                break;
            case GALLERY_PERMISSIONS_REQUEST:
                if (PermissionUtils.permissionGranted(requestCode, GALLERY_PERMISSIONS_REQUEST, grantResults)) {
                    startGalleryChooser();
                } else {
                    Toast.makeText(this,"Please grand permission to access files from settings",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void startGalleryChooser() {
        if (PermissionUtils.requestPermission(this, GALLERY_PERMISSIONS_REQUEST, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select a photo"),
                    GALLERY_IMAGE_REQUEST);
        }
    }

    private void startCamera() {
        if (PermissionUtils.requestPermission(
                this,
                CAMERA_PERMISSIONS_REQUEST,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Uri photoUri = FileProvider.getUriForFile(this, this.getApplicationContext().getPackageName() + ".provider", getCameraFile());
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivityForResult(intent, CAMERA_IMAGE_REQUEST);
        }
    }

    private File getCameraFile() {
        File dir = this.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return new File(dir, FILE_NAME);
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(result==1) {
            closetFragment.onActivityResult(requestCode, resultCode, data);
        }
        if(result==3) {
            ratingFragment.onActivityResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        if(bnv.getSelectedItemId() == R.id.closet){
            if(doubleBackToExitPressedOnce){
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            },2000);
        }
        if(bnv.getSelectedItemId() == R.id.rating){
            bnv.setSelectedItemId(R.id.closet);
        }
        if(bnv.getSelectedItemId() == R.id.shop){
            bnv.setSelectedItemId(R.id.closet);
        }
        if(bnv.getSelectedItemId() == R.id.setting && getFragmentManager().getBackStackEntryCount() == 0){
            bnv.setSelectedItemId(R.id.closet);
        }
    }
}
