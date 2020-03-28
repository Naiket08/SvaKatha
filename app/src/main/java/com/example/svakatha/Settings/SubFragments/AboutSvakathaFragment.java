package com.example.svakatha.Settings.SubFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.svakatha.R;

public class AboutSvakathaFragment extends Fragment {

    ImageView imageViewAboutSvakathaHeader;
    TextView textViewSvakathaPvtLtd, textViewKnowMoreAboutUs;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aboutsvakatha,container,false);

        imageViewAboutSvakathaHeader = (ImageView) view.findViewById(R.id.imageViewAboutSvakathaHeader);
        textViewKnowMoreAboutUs = (TextView) view.findViewById(R.id.textViewKnowMoreAboutUs);
        textViewSvakathaPvtLtd = (TextView) view.findViewById(R.id.textViewSvakathaPvtLtd);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().setTitle("Setting");
    }
}
