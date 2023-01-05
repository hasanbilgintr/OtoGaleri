
package com.example.otogaleri.pages.newuser;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.otogaleri.R;
import com.example.otogaleri.databinding.FragmentNewuserBinding;
import com.example.otogaleri.others.ChangeFragment;
import com.example.otogaleri.others.Onesignal;
import com.example.otogaleri.pages.veritification.VerificationFragment;

public class NewUserFragment extends Fragment {


    private FragmentNewuserBinding binding;
    private NewUserViewModel viewModel;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNewuserBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(NewUserViewModel.class);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttoncreateuser();
    }

    private void buttoncreateuser() {

        binding.buttonCreateuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prossegdialogac();
                viewModel.createuser(binding.edittextUsername.getText().toString(), binding.edittextEmail.getText().toString(), binding.edittextPassword.getText().toString(), Onesignal.appuserid);
                viewModel.resultmessage.observe(getViewLifecycleOwner(), resultmesaj -> {

                    switch (resultmesaj) {
                        case "0":
                            Toast.makeText(getContext(), "Hata var yetkiliye bildiniriz", Toast.LENGTH_SHORT).show();
                            break;
                        case "1":
                            Toast.makeText(getContext(), "Kayıt Başarılıdır Hesap Aktif olması için Lütfen Doğrulayınız", Toast.LENGTH_SHORT).show();
                            createuserlistdinleceac();
                            break;
                        case "2":
                            Toast.makeText(getContext(), "Böle Bir Kullanici Adı Var", Toast.LENGTH_SHORT).show();
                            break;
                        case "3":
                            Toast.makeText(getContext(), "Böle Bir Email Var", Toast.LENGTH_SHORT).show();
                            break;
                        case "4":
                            Toast.makeText(getContext(), "4Kayıt Başarısız Lütfen Yetkiliye bildiriniz", Toast.LENGTH_SHORT).show();
                            break;
                        case "5":
                            Toast.makeText(getContext(), "5Kayıt Başarısız Lütfen Yetkiliye bildiriniz", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            break;
                    }
                });
                progressDialog.cancel();
            }
        });
    }

    public void createuserlistdinleceac() {
        viewModel.resultlist.observe(getViewLifecycleOwner(), resultlist -> {
            Bundle bundle = new Bundle();
            bundle.putString("email", binding.edittextEmail.getText().toString());
            bundle.putInt("kod", resultlist.getDogrulamakodu());
            ChangeFragment changeFragment = new ChangeFragment(getContext(), new VerificationFragment(), "replaceFragVerificition", R.id.nav_host_fragment_content_login, bundle);
            changeFragment.change();
        });

    }

    public void prossegdialogac() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Bilgiler Ekranı");
        progressDialog.setMessage("Devam eden işleminiz bulunmaktadır. Lütfen bekleyiniz..");
        progressDialog.setCancelable(false);//diğer yerlere tıklanmasın diye
        progressDialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}