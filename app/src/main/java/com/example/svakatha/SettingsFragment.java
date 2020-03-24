package com.example.svakatha;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        setViews(view);

        return view;
    }

    private void setViews(View view) {

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.ll_profile:
                        //TODO: open profile
                        break;
                    case R.id.rl_about_sva:
                        //TODO: open sva
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
    }
}
