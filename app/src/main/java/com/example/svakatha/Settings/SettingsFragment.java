package com.example.svakatha.Settings;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.svakatha.Settings.SubFragments.ProfileFragment;
import com.example.svakatha.R;
import com.example.svakatha.Settings.SubFragments.AboutApplicationFragment;
import com.example.svakatha.Settings.SubFragments.AboutSvakathaFragment;
import com.example.svakatha.Settings.SubFragments.HelpFragment;
import com.example.svakatha.Settings.SubFragments.ShareAndEarnFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.rpc.Help;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    private ProfileFragment profileFragment;
    private AboutSvakathaFragment aboutSvakathaFragment;
    private AboutApplicationFragment aboutApplicationFragment;
    private ShareAndEarnFragment shareAndEarnFragment;
    private HelpFragment helpFragment;
    TextView tv_profile_name,tv_profile_mail_id;
    LinearLayout ll_profile;
    RelativeLayout rl_about_sva;
    RelativeLayout rl_about_app;
    RelativeLayout rl_share;
    LinearLayout ll_help;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth= FirebaseAuth.getInstance();
        db= FirebaseFirestore.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();
        ll_profile = (LinearLayout) view.findViewById(R.id.ll_profile);
        rl_about_sva = (RelativeLayout) view.findViewById(R.id.rl_about_sva);
        rl_about_app = (RelativeLayout) view.findViewById(R.id.rl_about_app);
        rl_share = (RelativeLayout) view.findViewById(R.id.rl_share);
        ll_help = (LinearLayout) view.findViewById(R.id.ll_help);
        tv_profile_name = (TextView)view.findViewById(R.id.tv_profile_name);
        tv_profile_mail_id = (TextView)view.findViewById(R.id.tv_profile_mail_id);


        ll_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(profileFragment == null) {
                    profileFragment = new ProfileFragment();
                }
                supportFragmentManager.beginTransaction().replace(R.id.host_fragment,profileFragment).addToBackStack(null).commit();
                getActivity().setTitle("Profile");
            }
        });

        rl_about_sva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aboutSvakathaFragment == null){
                    aboutSvakathaFragment = new AboutSvakathaFragment();
                }
                supportFragmentManager.beginTransaction().replace(R.id.host_fragment,aboutSvakathaFragment).commit();
                getActivity().setTitle("About SvaKatha");
            }
        });

        rl_about_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aboutApplicationFragment == null){
                    aboutApplicationFragment = new AboutApplicationFragment();
                }
                supportFragmentManager.beginTransaction().replace(R.id.host_fragment,aboutApplicationFragment).addToBackStack(null).commit();
                getActivity().setTitle("Our Application");
            }
        });

        rl_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shareAndEarnFragment == null){
                    shareAndEarnFragment = new ShareAndEarnFragment();
                }
                supportFragmentManager.beginTransaction().replace(R.id.host_fragment,shareAndEarnFragment).addToBackStack(null).commit();
                getActivity().setTitle("Share and Earn");
            }
        });

        ll_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(helpFragment == null){
                    helpFragment = new HelpFragment();
                }
                supportFragmentManager.beginTransaction().replace(R.id.host_fragment,helpFragment).addToBackStack(null).commit();
                getActivity().setTitle("Help");
            }
        });

        db.collection("users").document(mAuth.getCurrentUser().getUid()).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                          @SuppressLint("SetTextI18n")
                                          @Override
                                          public void onSuccess(DocumentSnapshot documentSnapshot) {
                                              String finalProfileText = documentSnapshot.getString("FirstName");
                                              String email = documentSnapshot.getString("Email");

                                              tv_profile_name.setText(finalProfileText);
                                              tv_profile_mail_id.setText(email);
                                          }
                                      });

        //setViews(view);
        return view;
    }

    /*private void setViews(View view) {

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();
                switch (v.getId()) {
                    case R.id.ll_profile:
                        //TODO: open profile
                        if(profileFragment == null) {
                            profileFragment = new ProfileFragment();
                        }
                        supportFragmentManager.beginTransaction()
                                .replace(R.id.host_fragment,
                                        profileFragment).commit();
                        getActivity().setTitle("Profile");
                        break;
                    case R.id.rl_about_sva:
                        //TODO: open sva
                        if(aboutSvakathaFragment == null) {
                            aboutSvakathaFragment = new AboutSvakathaFragment();
                        }
                        aboutSvakathaFragment = new AboutSvakathaFragment();
                        supportFragmentManager.beginTransaction()
                                .replace(R.id.host_fragment,
                                        aboutSvakathaFragment).commit();
                        getActivity().setTitle("About SvaKatha");
                        break;
                    case R.id.rl_about_app:
                        //TODO: open about
                        break;
                    case R.id.rl_share:
                        //TODO: open share
                        break;
                    case R.id.ll_help:
                        //TODO: open help
                        break;
                }
            }
        };
    }*/
}
