package com.example.otogaleri.pages.login;

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

import androidx.lifecycle.ViewModelProvider;

import com.example.otogaleri.R;
import com.example.otogaleri.databinding.FragmentLoginBinding;
import com.example.otogaleri.others.ChangeFragment;
import com.example.otogaleri.activities.MainActivity;
import com.example.otogaleri.others.Onesignal;
import com.example.otogaleri.pages.newuser.NewUserFragment;

public class LoginFragment extends Fragment {


    private FragmentLoginBinding binding;
    private LoginViewModel viewModel;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPreferences = getContext().getSharedPreferences("giris", 0);
        if (sharedPreferences.getString("uye_id", null) != null && sharedPreferences.getString("uye_kullaniciadi", null) != null) {
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
                ChangeFragment changeFragment = new ChangeFragment(getContext(), new NewUserFragment(), "replaceFragNewUser", R.id.nav_host_fragment_content_login);
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
                        case "0":
                            Toast.makeText(getContext(), "Hatalıdır Yetkiliye bildiriniz", Toast.LENGTH_SHORT).show();
                            break;
                        case "1":
                            sharedpereferencessave();
                            Toast.makeText(getContext(), "Giriş Yapıldı", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            break;
                        case "2":
                            Toast.makeText(getContext(), "Kullanıcı Adı yada şifre hatalıdır", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            break;
                    }
                });
            }
        });
    }

    //region sharedpereferences işlemi id ve uyeid işlemi
    public void sharedpereferencessave() {
        viewModel.resultlist.observe(getViewLifecycleOwner(), uye -> {
            /*1 ayıt varsa tek tek alabiliyoruz ama arraylist halinde alamadım daha araştırıcam */
            //Log.i("deneme3",viewModel.resultlist.getValue().getId());
            //aynı ikisde çalışıyor sdende çektirebiliriz /new Observer<Uyeler>()(eksi bu) buda çalışıyor
            //Log.i("deneme2",s.getId());

            sharedPreferences = getContext().getSharedPreferences("giris", 0);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("uye_id", uye.getId());
            editor.putString("uye_kullaniciadi", uye.getKadi());
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