package com.example.otogaleri.ui.NewUser;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.otogaleri.R;
import com.example.otogaleri.databinding.FragmentNewUserBinding;


public class NewUserFragment extends Fragment {


    private FragmentNewUserBinding binding;
    private NewUserViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNewUserBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(NewUserViewModel.class);
        return inflater.inflate(R.layout.fragment_new_user, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}