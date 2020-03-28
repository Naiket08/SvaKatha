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

public class ShareAndEarnFragment extends Fragment {

    ImageView imageViewShareAndEarnHeader;
    TextView textViewInvite, textViewShareAndEarnHowItWorks;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_share_and_earn,container,false);
        imageViewShareAndEarnHeader = (ImageView) view.findViewById(R.id.imageViewShareAndEarnHeader);
        textViewInvite = (TextView) view.findViewById(R.id.textViewInvite);
        textViewShareAndEarnHowItWorks = (TextView) view.findViewById(R.id.textViewShareAndEarnHowItWorks);

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().setTitle("Setting");
    }
}
