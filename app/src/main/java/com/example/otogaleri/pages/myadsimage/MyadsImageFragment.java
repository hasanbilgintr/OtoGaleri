package com.example.otogaleri.pages.myadsimage;

import static android.app.Activity.RESULT_OK;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.bluetooth.le.ScanSettings;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.example.otogaleri.R;
import com.example.otogaleri.activities.MainActivity;
import com.example.otogaleri.databinding.FragmentMyadsBinding;
import com.example.otogaleri.databinding.FragmentMyadsimageBinding;
import com.example.otogaleri.others.ChangeFragment;
import com.example.otogaleri.others.StaticData;
import com.example.otogaleri.pages.myads.MyAdsFragment;
import com.example.otogaleri.pages.myads.MyAdsViewModel;
import com.example.otogaleri.pages.newuser.NewUserFragment;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MyadsImageFragment extends Fragment {

    private MyadsImageViewModel viewModel;
    private FragmentMyadsimageBinding binding;
    Bitmap bitmap;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(MyadsImageViewModel.class);
        binding = FragmentMyadsimageBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        buttongeri();
        buttonilanverkayit();
        resimSecButton();
        resimEkleButton();
        anaSayfayOnClick();

    }

    private void buttongeri() {

        binding.geriButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
//                startActivity(intent);
                ChangeFragment changeFragment = new ChangeFragment(getContext(), new MyAdsFragment(), "replaceFragMyAds", R.id.content_FrameLayout);
                changeFragment.change();

                //fragmentten aktiviteye animasyon geçiş kodudur
                //getActivity().overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
            }
        });
    }

    public void buttonilanverkayit() {
        binding.ilanyayinlaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.ilanver(StaticData.getKullaniciadi(), Integer.valueOf(StaticData.getSehirid()), StaticData.getIlce(), StaticData.getMahalle(), StaticData.getMarka(), StaticData.getSeri(), StaticData.getModel(), StaticData.getYil(), StaticData.getKm(), StaticData.getIlantipi(), StaticData.getKimden(), StaticData.getBaslik(), StaticData.getAciklama(), StaticData.getMotortipi(), StaticData.getMotorhacmi(), StaticData.getSurat(), StaticData.getYakittipi(), StaticData.getOrtalamayakit(), StaticData.getDepohacmi(), StaticData.getUcret());

                viewModel.resultmessage.observe(getViewLifecycleOwner(), resultmesaj -> {
                    switch (resultmesaj) {
                        case "0":
                            Toast.makeText(getContext(), "İlan Verme Başarısız", Toast.LENGTH_SHORT).show();
                            break;
                        case "1":
                            Toast.makeText(getContext(), "İlan Verme Başarılı", Toast.LENGTH_SHORT).show();
                            binding.resimekleButton.setEnabled(true);
                            binding.resimsecButton.setEnabled(true);
                            binding.geriButton.setEnabled(false);
                            binding.ilanyayinlaButton.setEnabled(false);
                            binding.anaSayfaButton.setEnabled(true);
                            break;
                        default:
                            break;
                    }
                });
                viewModel.resultlist.observe(getViewLifecycleOwner(), resultlist -> {
                    StaticData.setIlanid(resultlist.getIlanId());
                    StaticData.setUye_id(String.valueOf(resultlist.getUyeId()));
                });

            }
        });
    }

    public void resimSec() {
        Intent intent = new Intent();
        intent.setType("image/*");
        //galeri açması
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 777);
    }

    public void resimSecButton() {
        binding.resimsecButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resimSec();
            }
        });
    }


    public void resimEkleButton() {
        binding.resimekleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //imageView e resim olmadığında kontrolünü sağlar
                if (bitmap!=null) {
                    viewModel.resimEkle(StaticData.getUye_id(), StaticData.getIlanid(), imageToString());
                    viewModel.resultmessage.observe(getViewLifecycleOwner(), resultmesaj -> {
                        switch (resultmesaj) {
                            case "0":
                                Toast.makeText(getContext(), "Resim Ekleme Başarısız", Toast.LENGTH_SHORT).show();
                                break;
                            case "1":
                                Toast.makeText(getContext(), "Resim Ekleme Başarılı", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                    });
                }else{
                    Toast.makeText(getContext(), "Lütfen Resim Ekleyiniz", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 777 && resultCode == RESULT_OK && data != null) {
            Uri path = data.getData();
            try {
                //bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);//activity için
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), path);
                binding.secilenilanresmiImageview.setImageBitmap(bitmap);
                binding.secilenilanresmiImageview.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //buraya gelen bitmap türündeki data stringe çevrililerek veri tabanına parametre olarak alcak
    public String imageToString() {
        /*seçili resmi stringe çeviricek */
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        /*100 ile kaliteyi düşürebiliriz*/
        byte[] byt = byteArrayOutputStream.toByteArray();
        String imageToString = Base64.encodeToString(byt, Base64.DEFAULT);
        return imageToString;
    }

    public void anaSayfayOnClick(){
        binding.anaSayfaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temizle();
                Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void temizle(){
        StaticData.setBaslik("");
        StaticData.setAciklama("");
        StaticData.setIlantipi("");
        StaticData.setKimden("");
        StaticData.setSehir("");
        StaticData.setSehirid("");
        StaticData.setIlce("");
        StaticData.setMahalle("");
        StaticData.setMarka("");
        StaticData.setSeri("");
        StaticData.setModel("");
        StaticData.setYil("");
        StaticData.setKm("");
        StaticData.setMotortipi("");
        StaticData.setMotorhacmi("");
        StaticData.setSurat("");
        StaticData.setYakittipi("");
        StaticData.setOrtalamayakit("");
        StaticData.setDepohacmi("");
        StaticData.setUcret("");
    }
}