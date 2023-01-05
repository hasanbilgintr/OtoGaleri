package com.example.otogaleri.pages.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.otogaleri.activities.ContentActivity;
import com.example.otogaleri.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel  = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ilanverbuton();
        ilanlarButton();
    }

    private void ilanverbuton() {

        binding.ilanverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), ContentActivity.class);
                intent.putExtra("fragment", "myads");
                startActivity(intent);
            }
        });
    }

    private void ilanlarButton() {

        binding.ilanlarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), ContentActivity.class);
                intent.putExtra("fragment", "ads");
                startActivity(intent);
            }
        });
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }





}