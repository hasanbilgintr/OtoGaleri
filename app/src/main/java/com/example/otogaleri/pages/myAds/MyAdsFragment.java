package com.example.otogaleri.pages.myAds;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.otogaleri.R;
import com.example.otogaleri.activities.MainActivity;

import com.example.otogaleri.databinding.FragmentMyadsBinding;
import com.example.otogaleri.others.ChangeFragment;
import com.example.otogaleri.others.StaticData;
import com.example.otogaleri.pages.myAdsImage.MyadsImageFragment;

import java.util.ArrayList;
import java.util.List;


public class MyAdsFragment extends Fragment {
    private MyAdsViewModel viewModel;
    private FragmentMyadsBinding binding;
    List<String> ilanList;
    List<String> kimdenList;
    List<String> illist;
    List<String> ilidlist;
    int ilpoz;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(MyAdsViewModel.class);
        binding = FragmentMyadsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        formload();
        buttonileri();
        buttongeri();
    }

    public void formload() {
        spinnerdoldur();
        staticdatatamasi();
    }

    public void staticdatatamasi() {
        binding.ilanbaslikEdittext.setText(StaticData.getBaslik());
        binding.ilanaciklamaEdittext.setText(StaticData.getAciklama());

        if (!StaticData.getIlantipi().equals("")) {
            binding.ilanturuSpinner.setSelection(Integer.valueOf(StaticData.getIlantipi()));
        }

        if (!StaticData.getKimden().equals("")) {
            binding.kimdenSpinner.setSelection(Integer.valueOf(StaticData.getKimden()));
        }


        binding.ilcelerEdittext.setText(StaticData.getIlce());
        binding.mahalelerEdittext.setText(StaticData.getMahalle());
        binding.markalarEdittext.setText(StaticData.getMarka());
        binding.serilerEdittext.setText(StaticData.getSeri());
        binding.modellerEdittext.setText(StaticData.getModel());
        binding.yillarEdittext.setText(StaticData.getYil());
        binding.kmlerEdittext.setText(StaticData.getKm());
        binding.motortipiEdittext.setText(StaticData.getMotortipi());
        binding.motorhacmiEdittext.setText(StaticData.getMotorhacmi());
        binding.azamisuratEdittext.setText(StaticData.getSurat());
        binding.yakittipiEdittext.setText(StaticData.getYakittipi());
        binding.ortyakitEdittext.setText(StaticData.getOrtalamayakit());
        binding.depohacmiEdittext.setText(StaticData.getDepohacmi());
        binding.ucretEdittext.setText(StaticData.getUcret());
    }

    public void spinnerdoldur() {
        ilanturudoldur();
        kimdenlistdoldur();
        illeridoldur();
    }

    private void illeridoldur() {
        viewModel.illeridoldur();
        viewModel.resultlist.observe(getViewLifecycleOwner(), list -> {
            illist = new ArrayList<>();
            ilidlist = new ArrayList<>();
            Log.i("list",list.toString());
            for (int i = 0; i < list.size(); i++) {
                illist.add(list.get(i).getIl());
            }
            for (int i = 0; i < list.size(); i++) {
                ilidlist.add(list.get(i).getId());
            }
//            Log.i("listil",illist.toString());
//            Log.i("listilid",ilidlist.toString());

            ArrayAdapter<String> illerlistadaper = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, illist);
            illerlistadaper.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            binding.illerSpinner.setAdapter(illerlistadaper);


            //veriyi okumada sorun olduğu için metotiçinde set ataması yapıldı
            if (!StaticData.getSehir().equals("")) {
                binding.illerSpinner.setSelection(Integer.valueOf(StaticData.getSehir()));
            }
            //veriyi okumada sorun olduğu için metotiçinde set ataması yapıldı

        });
    }

    public void kimdenlistdoldur() {
        kimdenList = new ArrayList<>();
        kimdenList.add("Lütfen Giriniz");
        kimdenList.add("Sahibinden");
        kimdenList.add("Galeriden");
        ArrayAdapter<String> kimdenListAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, kimdenList);
        kimdenListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.kimdenSpinner.setAdapter(kimdenListAdapter);
    }

    public void ilanturudoldur() {
        ilanList = new ArrayList<>();
        ilanList.add("Lütfen Giriniz");
        ilanList.add("Satılık");
        ilanList.add("Kiralık");
        //getContext fargment olduğu için aktivitede this diye yazılabilir
        Log.i("doldur", ilanList.toString());
        ArrayAdapter<String> turListAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, ilanList);
        //böle bi açılış içinde var (simple_expandable_list_item_1)
        //ArrayAdapter<String> turListAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_expandable_list_item_1,ilanList);
        turListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinnere set edilmesi
        binding.ilanturuSpinner.setAdapter(turListAdapter);
    }

    private void buttonileri() {
        binding.ileriButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.kimdenSpinner.getSelectedItemPosition() != 0 &&
                        binding.ilanturuSpinner.getSelectedItemPosition() != 0 &&
                        !binding.ilanbaslikEdittext.getText().toString().equals("") &&
                        !binding.ilanaciklamaEdittext.getText().toString().equals("") &&
                        !binding.ilcelerEdittext.getText().toString().equals("") &&
                        !binding.mahalelerEdittext.getText().toString().equals("") &&
                        !binding.markalarEdittext.getText().toString().equals("") &&
                        !binding.serilerEdittext.getText().toString().equals("") &&
                        !binding.modellerEdittext.getText().toString().equals("") &&
                        !binding.yillarEdittext.getText().toString().equals("") &&
                        !binding.kmlerEdittext.getText().toString().equals("") &&
                        !binding.motortipiEdittext.getText().toString().equals("") &&
                        !binding.motorhacmiEdittext.getText().toString().equals("") &&
                        !binding.azamisuratEdittext.getText().toString().equals("") &&
                        !binding.yakittipiEdittext.getText().toString().equals("") &&
                        !binding.ortyakitEdittext.getText().toString().equals("") &&
                        !binding.depohacmiEdittext.getText().toString().equals("") &&
                        !binding.ucretEdittext.getText().toString().equals("")) {

                    StaticData.setBaslik(binding.ilanbaslikEdittext.getText().toString());
                    StaticData.setAciklama(binding.ilanaciklamaEdittext.getText().toString());
                    StaticData.setIlantipi(String.valueOf(binding.ilanturuSpinner.getSelectedItemPosition()));
                    StaticData.setKimden(String.valueOf(binding.kimdenSpinner.getSelectedItemPosition()));
                    StaticData.setSehir(String.valueOf(binding.illerSpinner.getSelectedItemPosition()));
                    StaticData.setSehirid(String.valueOf(ilidlist.get(binding.illerSpinner.getSelectedItemPosition())));

                    StaticData.setIlce(binding.ilcelerEdittext.getText().toString());
                    StaticData.setMahalle(binding.mahalelerEdittext.getText().toString());
                    StaticData.setMarka(binding.markalarEdittext.getText().toString());
                    StaticData.setSeri(binding.serilerEdittext.getText().toString());
                    StaticData.setModel(binding.modellerEdittext.getText().toString());
                    StaticData.setYil(binding.yillarEdittext.getText().toString());
                    StaticData.setKm(binding.kmlerEdittext.getText().toString());
                    StaticData.setMotortipi(binding.motortipiEdittext.getText().toString());
                    StaticData.setMotorhacmi(binding.motorhacmiEdittext.getText().toString());
                    StaticData.setSurat(binding.azamisuratEdittext.getText().toString());
                    StaticData.setYakittipi(binding.yakittipiEdittext.getText().toString());
                    StaticData.setOrtalamayakit(binding.ortyakitEdittext.getText().toString());
                    StaticData.setDepohacmi(binding.depohacmiEdittext.getText().toString());
                    StaticData.setUcret(binding.ucretEdittext.getText().toString());
//

                    ChangeFragment changeFragment = new ChangeFragment(getContext(), new MyadsImageFragment(), "replaceFragMyAdsImage", R.id.content_FrameLayout);
                    changeFragment.change();
                } else {
                    Toast.makeText(getContext(), "Tüm Bilgileri Giriniz", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void buttongeri() {
        binding.geriButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                startActivity(intent);
                //fragmentten aktiviteye animasyon geçiş kodudur
                getActivity().overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
                //fragmentten aktivityi geri tuşu yaptığında tekrar açılmaması için eklendi
                getActivity().finish();
            }
        });
    }


}