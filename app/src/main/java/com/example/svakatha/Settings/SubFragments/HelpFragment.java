package com.example.svakatha.Settings.SubFragments;

import android.media.Image;
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

public class HelpFragment extends Fragment {

    ImageView imageViewHelpHeader;
    TextView textViewContactUs,textViewHelpHowItWorks;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help,container,false);
        imageViewHelpHeader = (ImageView) view.findViewById(R.id.imageViewHelpHeader);
        textViewContactUs = (TextView) view.findViewById(R.id.textViewContactUs);
        textViewHelpHowItWorks = (TextView) view.findViewById(R.id.textViewHelpHowItWorks);

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().setTitle("Setting");
    }
}
