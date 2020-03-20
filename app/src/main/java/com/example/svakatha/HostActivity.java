package com.example.svakatha;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class HostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        BottomNavigationView bnv = findViewById(R.id.bottom_navigation);

        bnv.setItemIconTintList(null);

        bnv.setOnNavigationItemSelectedListener(navListener);

        bnv.setSelectedItemId(R.id.shop);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            switch (item.getItemId()) {
                case R.id.closet:
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.host_fragment,
                                    new ClosetFragment()).commit();
                    setTitle("My Closet");
                    break;
                case R.id.shop:
                    makeToast("Shop clicked");
                    //TODO: add fragment 2
                    break;
                case R.id.rating:
                    makeToast("Rating clicked");
                    //TODO: add fragment 3
                    break;
                case R.id.setting:
                    makeToast("Setting clicked");
                    //TODO: add fragment 4
            }
            return true;
        }
    };

    private void makeToast(String text) {
        Toast.makeText(this,"Shop clicked",Toast.LENGTH_SHORT).show();
    }
}
