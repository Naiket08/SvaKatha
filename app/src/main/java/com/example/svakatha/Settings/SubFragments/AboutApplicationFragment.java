package com.example.svakatha.Settings.SubFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.svakatha.R;

public class AboutApplicationFragment extends Fragment {
    ImageView imageViewAboutApplicationHeader;
    TextView textViewApplication,textViewAboutApplicationHowItWorks;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aboutapplication,container,false);
        imageViewAboutApplicationHeader = (ImageView) view.findViewById(R.id.imageViewAboutApplicationHeader);
        textViewApplication = (TextView) view.findViewById(R.id.textViewApplication);
        textViewAboutApplicationHowItWorks = (TextView) view.findViewById(R.id.textViewHowItWorks);

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().setTitle("Setting");
    }
}
