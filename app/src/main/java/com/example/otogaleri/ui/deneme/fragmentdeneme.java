package com.example.otogaleri.ui.deneme;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.otogaleri.databinding.FragmentdenemeBinding;

public class fragmentdeneme extends Fragment {


   private FragmentdenemeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        FragmentdenemeViewModel fragmentdenemeViewModel =
                new ViewModelProvider(this).get(FragmentdenemeViewModel.class);

        binding = FragmentdenemeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.text;
        fragmentdenemeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}