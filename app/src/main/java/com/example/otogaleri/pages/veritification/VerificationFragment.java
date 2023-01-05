package com.example.otogaleri.pages.veritification;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.otogaleri.activities.MainActivity;
import com.example.otogaleri.databinding.FragmentVerificationBinding;

public class VerificationFragment extends Fragment {

    private FragmentVerificationBinding binding;
    private VerificationViewModel viewModel;
    SharedPreferences sharedPreferences;
    private String uyeid;
    private String uyekullaniciadi;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentVerificationBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(VerificationViewModel.class);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getData();
        buttonActivate();
    }

    private void buttonActivate() {
        binding.buttonVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.userActivate(binding.edittextEmail.getText().toString(), binding.edittextKod.getText().toString());
                viewModel.resultmessage.observe(getViewLifecycleOwner(), resultmesaj -> {
                    switch (resultmesaj) {
                        case "0":
                            Toast.makeText(getContext(), "Hesabınız doğrulanmadı", Toast.LENGTH_SHORT).show();
                            break;
                        case "1":
                            Toast.makeText(getContext(), "Hesabınız doğrulandı", Toast.LENGTH_SHORT).show();
                            listdinlevalac();
                            break;
                        default:
                            break;
                    }
                });
            }
        });
    }

    private void listdinlevalac() {
        viewModel.resultlist.observe(getViewLifecycleOwner(), resultlist -> {
            Log.i("deneme5", resultlist.getUyeid());
            Log.i("deneme6", resultlist.getUyekullaniciadi());
            //ikiside olur
            //uyeid=Integer.parseInt(viewModel.resultlist.getValue().getUyeid());
            uyeid = resultlist.getUyeid();
            uyekullaniciadi = resultlist.getUyekullaniciadi();

            loginkaydet();
            fragmentdegistir();
        });
    }

    private void fragmentdegistir() {
        Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    private void loginkaydet() {
        Log.i("deneme",uyeid+"");
        Log.i("deneme2",uyekullaniciadi+"");
        sharedPreferences = getContext().getSharedPreferences("giris", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("uye_id", uyeid);
        editor.putString("uye_kullaniciadi", uyekullaniciadi);
        editor.commit();
    }

    private void getData() {
        if (getArguments() != null) {
            Toast.makeText(getContext(), "" + getArguments().getInt("kod") + " / " + getArguments().getString("email"), Toast.LENGTH_SHORT).show();
            binding.edittextEmail.setText(getArguments().getString("email"));
            binding.edittextKod.setText(String.valueOf(getArguments().getInt("kod")));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void deneme(){
        Toast.makeText(getContext(), "bildirim butouna tıkladın :DDD", Toast.LENGTH_SHORT).show();
    }
}