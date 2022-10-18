package com.example.otogaleri.ui.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.otogaleri.ChangeFragment;
import com.example.otogaleri.MainActivity;
import com.example.otogaleri.Models.Uyeler;
import com.example.otogaleri.databinding.FragmentLoginBinding;
import com.example.otogaleri.ui.NewUser.NewUserFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private LoginViewModel viewModel;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPreferences=getContext().getSharedPreferences("giris",0);
        if(sharedPreferences.getString("uye_id",null)!=null && sharedPreferences.getString("uye_kullaniciadi",null)!=null){
            Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        buttonlogin();
        buttonnewuser();
    }

    private void buttonnewuser() {
        binding.textviewNewuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    ChangeFragment changeFragment=new ChangeFragment(getContext(),new NewUserFragment(),"replaceFragNewUserFragment");
                    changeFragment.change();

            }
        });
    }


    private void buttonlogin() {
        binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*nav_grap.xmldeki oraya bakar yinede araştır*/
                /*NavHostFragment.findNavController(LoginFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);*/

                viewModel.loginuser(binding.edittextUsername.getText().toString(), binding.edittextPassword.getText().toString());
                viewModel.resultmessage.observe(getViewLifecycleOwner(), resultmesaj -> {
                    switch (resultmesaj.toString()) {
                        case "1":
                            sharedpereferencessave();
                            Toast.makeText(getContext(), "Giriş Yapıldı", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            break;
                        case "2":
                            Toast.makeText(getContext(), "Kullanıcı Adı yada şifre hatalıdır", Toast.LENGTH_SHORT).show();
                            break;
                        case "3":
                            Toast.makeText(getContext(), "Hatalıdır Yetkiliye bildiriniz", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            break;
                    }
                });
            }
        });
    }

    //region sharedpereferences işlemi
    public  void sharedpereferencessave(){
        viewModel.resultlist.observe(getViewLifecycleOwner(), uye -> {
            /*1 ayıt varsa tek tek alabiliyoruz ama arraylist halinde alamadım daha araştırıcam */
            //Log.i("deneme3",viewModel.resultlist.getValue().getId());
            //aynı ikisde çalışıyor sdende çektirebiliriz /new Observer<Uyeler>()(eksi bu) buda çalışıyor
            //Log.i("deneme2",s.getId());

            sharedPreferences = getContext().getSharedPreferences("giris",0);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("uye_id",uye.getId());
            editor.putString("uye_kullaniciadi",uye.getKadi());
            editor.commit();

        });
//                viewModel.resultlist.observe(getViewLifecycleOwner(), new Observer<Uyeler>() {
//                    @Override
//                    public void onChanged(Uyeler uye) {
//                        //Log.i("deneme3",viewModel.resultlist.getValue().getId());
//                    }
//                });
    }
    //endregion
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}