package com.example.otogaleri.pages.deneme;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}