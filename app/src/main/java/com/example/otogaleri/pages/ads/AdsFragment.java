package com.example.otogaleri.pages.ads;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.example.otogaleri.R;
import com.example.otogaleri.activities.MainActivity;
import com.example.otogaleri.adapters.IlanlarAdapter;
import com.example.otogaleri.adapters.IlanlarMahalleAdapter;
import com.example.otogaleri.databinding.FragmentAdsBinding;
import com.example.otogaleri.databinding.FragmentHomeBinding;
import com.example.otogaleri.models.Ilanlar;
import com.example.otogaleri.models.Iller;
import com.example.otogaleri.models.Mahalleler;
import com.example.otogaleri.others.MainClass;
import com.example.otogaleri.others.StaticData;
import com.example.otogaleri.pages.home.HomeViewModel;
import com.google.android.material.slider.RangeSlider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class AdsFragment extends Fragment {

    private AdsViewModel viewModel;
    private FragmentAdsBinding binding;
    ProgressDialog progressDialog;
    boolean[] selectedcity;
    ArrayList<Integer> cityList;
    String[] cityArray;
    List<String> ilidlist;
    String[] ilceler;

    ArrayList<String> mahallelerArraylist;
    ArrayList<Integer> mahallelerArrrayListCount;
    ArrayList<String> mahallelerArrayliste;
    IlanlarMahalleAdapter ilanlarMahalleAdapter;
    ArrayList<String> mahallelerArrrayListSearch;
    ArrayList<String> mahallelerArraylst;
    ArrayList<String> mahalleListIsSelected;
    String mahalleSelected = "";

    ArrayList<String> illerArraylist;
    ArrayList<Integer> illerArrrayListCount;
    ArrayList<String> illerArrayliste;


    ArrayList<Ilanlar> ilanlars;
    IlanlarAdapter ilanlarAdapter;
    int minYil = 0;
    int maxYil = 0;
    boolean ilkSearchYear = true;
    List<String> ilanTuruList;
    List<String> kimdenList;
    List<String> sortList;
    //ilanlarReccyclerView detaylı ve kart biçiminde olma durumu
    boolean isNormalView = true;
    MainClass mainClass;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(AdsViewModel.class);
        binding = FragmentAdsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fromLoad();
        mahalleListIsSelected = new ArrayList<>();
    }

    public void fromLoad() {
        sortList();
        filtreLists();
        sortSpinnerSelected();
        filtreEditTextChange();
        buttonOnClick();
        ilanlarRecyclerViewRefresh();
    }

    private void sortSpinnerSelected() {
        binding.sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (binding.sortSpinner.getSelectedItemPosition()) {
                    case 0:
                    case 1:
                        ilanlarlist();

                        filterTags();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void sortList() {
        sortList = new ArrayList<>();
        sortList.add("En Düşük Fiyat");
        sortList.add("En Yüksek Fiyat");
        sortList.add("En Düşük Yıl");
        sortList.add("En Yüksek Yıl");
        sortList.add("En Az Km");
        sortList.add("En Çok Km");
        sortList.add("En Hızlı Araç");
        sortList.add("En Yavaş Araç");
        sortList.add("En Az Yakan Araç");
        sortList.add("En Çok Yakan Araç");
        ArrayAdapter<String> sortListAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, sortList);
        sortListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.sortSpinner.setAdapter(sortListAdapter);
    }

    //https://www.youtube.com/watch?v=Ffa0Mtd21_M
    private void ilanlarRecyclerViewRefresh() {
        binding.swifeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ilanlarlist();
                //adapterı yeniliyo diyebiliriz
                ilanlarAdapter.notifyDataSetChanged();
                binding.swifeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public void filtreLists() {
        ilanTuruList();
        kimdenList();
        mahalleListIsSelected = new ArrayList<>();
        ilanlarillerlist();
        ilanlarmahallelist();
        ilcelerlist();
        yillarchange();//ilanlarlistede set ediliyo
    }

    public void ilanTuruList() {
        ilanTuruList = new ArrayList<>();
        ilanTuruList.add("İlan Tür");
        ilanTuruList.add("Satılık");
        ilanTuruList.add("Kiralık");
        ilanTuruList.add("Teklif");
        ilanTuruList.add("Açık Attırma");
        ArrayAdapter<String> turListAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, ilanTuruList);
        turListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.ilanturuspinner.setAdapter(turListAdapter);


    }

    private void kimdenList() {
        kimdenList = new ArrayList<>();
        kimdenList.add("Kimden");
        kimdenList.add("Sahibinden");
        kimdenList.add("Galeriden");
        kimdenList.add("Yetkili Bayi");
        ArrayAdapter<String> kimdenListAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, kimdenList);
        kimdenListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.kimdenspinner.setAdapter(kimdenListAdapter);
    }

    public void buttonOnClick() {
        filtreButtonOnclick();
        filterClearButtonOnClick();
        viewChangeButtonOnClick();
    }

    private void viewChangeButtonOnClick() {
        binding.viewChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isNormalView) {
                    binding.ilanlarrecycview.setLayoutManager(new LinearLayoutManager(getContext()));
                    ilanlarAdapter = new IlanlarAdapter(ilanlars, true,getContext());

                    isNormalView = true;
                } else {
                    binding.ilanlarrecycview.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
                    ilanlarAdapter = new IlanlarAdapter(ilanlars, false,getContext());

                    isNormalView = false;
                }
                binding.ilanlarrecycview.setAdapter(ilanlarAdapter);
            }
        });
    }

    public void filtreEditTextChange() {
        mahalleFiltreChange();
    }

    public void filterClearButtonOnClick() {
        binding.filtreButtonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.ilanbaslikedittext.setText("");
                binding.ilanaciklamaedittext.setText("");
                binding.ilanturuspinner.setSelection(0);
                binding.kimdenspinner.setSelection(0);
                //binding.ilcelerMultiAutoCompleteTextView.setText("");
                //binding.ilcelerMultiAutoCompleteTextView.setText("");
                //binding.ilcelerMultiAutoCompleteTextView.setText("");
                binding.markalarEdittext.setText("");
                binding.serilerEdittext.setText("");
                binding.modellerEdittext.setText("");
                binding.RangeSlider.setValues((float) minYil, (float) maxYil);
                binding.startkmedittext.setText("");
                binding.endkmedittext.setText("");
                binding.motortipiEdittext.setText("");
                binding.motorhacmiedittext.setText("");
                binding.startazamisuratedittext.setText("");
                binding.endazamisuratedittext.setText("");
                binding.yakittipiEdittext.setText("");
                binding.ortyakitedittext.setText("");
                binding.depohacmiedittext.setText("");
                binding.minucretedittext.setText("");
                binding.maxucretedittext.setText("");
                binding.filterTagsTextView.setText("Filtre: ");
            }
        });
    }

    public void mahalleFiltreChange() {
        binding.mahalleleredittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mahallelerListSearch();
            }
        });
    }

    public void ilcelerlist() {
//        ilceler = new String[]{"Bayrampaşa","Esenyurt","Avcılar","Tuzla","Bağcılar"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1, ilceler);
//        binding.ilcelerMultiAutoCompleteTextView.setAdapter(adapter);
//        binding.ilcelerMultiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }

    public void yillarchange() {
        binding.RangeSlider.setStepSize(1F);
        binding.startyearedittext.setText(String.valueOf(binding.RangeSlider.getValues().get(0).intValue()));
        binding.endyearedittext.setText(String.valueOf(binding.RangeSlider.getValues().get(1).intValue()));
        //sliderrange min yada maxlerini değiştirilce çalıştırılcak metot
        binding.RangeSlider.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                //sliderrange minimum değerini startyearedittext e  atamak
                binding.startyearedittext.setText(String.valueOf(binding.RangeSlider.getValues().get(0).intValue()));
                binding.endyearedittext.setText(String.valueOf(binding.RangeSlider.getValues().get(1).intValue()));
            }
        });
    }

    private void ilanlarillerlist() {

        //region tüm iller getirilmesi
        //Log.i("adsfragmentlogtest294","");
        mahalleListIsSelected.size();
        viewModel.ilanlarlarIlList(binding.sortSpinner.getSelectedItemPosition(), binding.ilanbaslikedittext.getText().toString().trim(), binding.ilanaciklamaedittext.getText().toString().trim(), binding.ilanturuspinner.getSelectedItemPosition(), binding.kimdenspinner.getSelectedItemPosition(),

                mahalleSelected,/*ilSelected,*/

                binding.markalarEdittext.getText().toString().trim(), binding.serilerEdittext.getText().toString().trim(), binding.modellerEdittext.getText().toString().trim(), binding.startyearedittext.getText().toString().trim(), binding.endyearedittext.getText().toString().trim(), binding.startkmedittext.getText().toString().trim(), binding.endkmedittext.getText().toString().trim(), binding.motortipiEdittext.getText().toString().trim(), binding.motorhacmiedittext.getText().toString().trim(), binding.startazamisuratedittext.getText().toString().trim(), binding.endazamisuratedittext.getText().toString().trim(), binding.yakittipiEdittext.getText().toString().trim(), binding.ortyakitedittext.getText().toString().trim(), binding.depohacmiedittext.getText().toString().trim(), binding.minucretedittext.getText().toString().trim(), binding.maxucretedittext.getText().toString().trim());
        viewModel.resultIlanlarilMessage.observe(getViewLifecycleOwner(), ilanlarIllerMessage -> {
            switch (ilanlarIllerMessage) {
                case "0":
                    //Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
                    break;
                case "1":
                    viewModel.resultIlanlarIlList.observe(getViewLifecycleOwner(), ilanlariIllerlist -> {

                        //Log.i("logtest311", ilanlariIllerlist.toString());
                        ilidlist = new ArrayList<>();
                        illerArraylist = new ArrayList<>();
                        for (int i = 0; i < ilanlariIllerlist.size(); i++) {
                            illerArraylist.add(ilanlariIllerlist.get(i).getIl());
                        }
                        for (int i = 0; i < ilanlariIllerlist.size(); i++) {
                            ilidlist.add(ilanlariIllerlist.get(i).getId());
                        }

                        //region olan iller sayıları
                        illerArrrayListCount = new ArrayList<>();
                        int ilCount = 0;
                        for (int j = 0; j < illerArraylist.size(); j++) {
                            for (int z = 0; z < illerArraylist.size(); z++) {
                                if (illerArraylist.get(j).equals(illerArraylist.get(z))) {
                                    ilCount = ilCount + 1;
                                }
                            }
                            illerArrrayListCount.add(j, ilCount);
                            ilCount = 0;
                        }
                        for (int b = 0; b < illerArraylist.size(); b++) {
                            illerArraylist.set(b, illerArraylist.get(b) + " (" + illerArrrayListCount.get(b) + ")");
                        }
                        //endregion olan iller sayıları

                        //region aynı olan iller eklenmedi
                        illerArrayliste = new ArrayList<>();
                        for (int b = 0; b < illerArraylist.size(); b++) {
                            for (int c = 0; c < illerArraylist.size(); c++) {
                                String il = illerArraylist.get(b);
                                String ikinciil = illerArraylist.get(c);
                                if (il == ikinciil) {
                                    if (!illerArrayliste.contains(il)) {
                                        illerArrayliste.add(illerArraylist.get(b));
                                    }
                                }
                            }
                        }
                        //Log.i("logtest360",illerArrayliste.toString());
                        //region aynı olan iller eklenmedi

                        cityArray = new String[illerArrayliste.size()];
                        for (int a = 0; a < illerArrayliste.size(); a++) {
                            cityArray[a] = illerArrayliste.get(a);
                        }
                        //https://www.youtube.com/watch?v=XrDVu3uPY3o&t=35s
                        cityList = new ArrayList<>();
                        selectedcity = new boolean[cityArray.length];
                        binding.iltextview.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                builder.setTitle("İl Seçimi");
                                builder.setCancelable(false);
                                builder.setMultiChoiceItems(cityArray, selectedcity, new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                        if (isChecked) {
                                            cityList.add(which);
                                            Collections.sort(cityList);
                                        } else {
                                            for (int j = 0; j < cityList.size(); j++) {
                                                if (cityList.get(j) == which) {
                                                    cityList.remove(j);
                                                }
                                            }
                                        }
                                    }
                                });
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        StringBuilder stringBuilder = new StringBuilder();
                                        for (int j = 0; j < cityList.size(); j++) {
                                            stringBuilder.append(cityArray[cityList.get(j)]);
                                            if (j != cityList.size() - 1) {
                                                stringBuilder.append(", ");
                                            }
                                        }
                                        binding.iltextview.setText(stringBuilder);
                                    }
                                });
                                //çalışırda sorunlar olabiliyo hata yok
//                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    });
                                builder.setNegativeButton("Select All", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        cityList.clear();
                                        for (int j = 0; j < cityArray.length; j++) {
                                            selectedcity[j] = true;
                                            cityList.add(j);
                                        }
                                        StringBuilder stringBuilder = new StringBuilder();
                                        for (String il : cityArray.clone()) {
                                            stringBuilder.append(il);
                                            if (cityArray.length != cityList.size() - 1) {
                                                stringBuilder.append(", ");
                                            }
                                        }
                                        binding.iltextview.setText(stringBuilder);
                                    }
                                });

                                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        for (int j = 0; j < selectedcity.length; j++) {
                                            selectedcity[j] = false;
                                            cityList.clear();
                                            binding.iltextview.setText("");
                                        }
                                    }
                                });
                                builder.show();
                            }
                        });
                    });
                    break;
                default:
                    break;
            }
        });
        //region ilanlariller getirilmesi
//        viewModel.illeridoldur();
 /*       viewModel.resultillist.observe(getViewLifecycleOwner(), ilanlarIlList -> {
            //illist = new ArrayList<>();
            ilidlist = new ArrayList<>();
            int a = ilanlarIlList.size();

            cityArray = new String[a];
            for (int i = 0; i < ilanlarIlList.size(); i++) {
                cityArray[i] = ilanlarIlList.get(i).getIl();
            }
            for (int i = 0; i < ilanlarIlList.size(); i++) {
                ilidlist.add(ilanlarIlList.get(i).getId());
            }
            //https://www.youtube.com/watch?v=XrDVu3uPY3o&t=35s
//            cityList = new ArrayList<>();
//            selectedcity = new boolean[cityArray.length];
//            binding.iltextview.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                    builder.setTitle("İl Seçimi");
//                    builder.setCancelable(false);
//
//                    builder.setMultiChoiceItems(cityArray, selectedcity, new DialogInterface.OnMultiChoiceClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//                            if (isChecked) {
//                                cityList.add(which);
//                                Collections.sort(cityList);
//                            } else {
//                                for (int j = 0; j < cityList.size(); j++) {
//                                    if (cityList.get(j) == which) {
//                                        cityList.remove(j);
//                                    }
//                                }
//                            }
//
//
//                        }
//                    });
//                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            StringBuilder stringBuilder = new StringBuilder();
//                            for (int j = 0; j < cityList.size(); j++) {
//                                stringBuilder.append(cityArray[cityList.get(j)]);
//                                if (j != cityList.size() - 1) {
//                                    stringBuilder.append(", ");
//                                }
//                            }
//                            Log.i("log138", stringBuilder.toString());
//                            binding.iltextview.setText(stringBuilder);
//                        }
//                    });
//                    //çalışırda sorunlar olabiliyo hata yok
////                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
////                        @Override
////                        public void onClick(DialogInterface dialog, int which) {
////                            dialog.dismiss();
////                        }
////                    });
//                    builder.setNegativeButton("Select All", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            cityList.clear();
//                            for (int j = 0; j < cityArray.length; j++) {
//                                selectedcity[j] = true;
//                                cityList.add(j);
//                            }
//                            StringBuilder stringBuilder = new StringBuilder();
//                            for (String il : cityArray.clone()) {
//                                stringBuilder.append(il);
//                                if (cityArray.length != cityList.size() - 1) {
//                                    stringBuilder.append(", ");
//                                }
//                            }
//                            binding.iltextview.setText(stringBuilder);
//                        }
//                    });
//
//                    builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            for (int j = 0; j < selectedcity.length; j++) {
//                                selectedcity[j] = false;
//                                cityList.clear();
//                                binding.iltextview.setText("");
//                            }
//                        }
//                    });
//                    builder.show();
                }
            });
        });
     */
        //endregion tüm illerin getirilmesi
    }

    private void mahallelerListSearch() {
        mahallelerArrrayListSearch = new ArrayList<>();
        //mahalle2 olan listeyi burda aratıp mahalle2 (3) yazdırdık
        for (int e = 0; e < mahallelerArraylst.size(); e++) {
            String mahallleSearch = binding.mahalleleredittext.getText().toString();
            String mahallle = mahallelerArraylst.get(e);
            if (mahallle.contains(mahallleSearch)) {
                mahallelerArrrayListSearch.add(mahallelerArrayliste.get(e));
            }
        }
        binding.mahalleFiltreRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //region dataların seçili olanları buraya yansıtıılcak
        ArrayList<Boolean> mahallelerArraylisteIsSelected = new ArrayList<>();
        for (int a = 0; a < mahallelerArrrayListSearch.size(); a++) {
            mahallelerArraylisteIsSelected.add(a, false);
        }
        //endregion  dataların seçili olanları buraya yansıtıılcak

        ilanlarMahalleAdapter = new IlanlarMahalleAdapter(mahallelerArrrayListSearch, mahallelerArraylisteIsSelected, new IlanlarMahalleAdapter.ItemClickListener() {
            @Override
            public void onItemClick(ArrayList<String> mahallelist, ArrayList<Boolean> mahalleListIsSelectedp) {
                mahalleListIsSelected = new ArrayList<>();
                for (int a = 0; a < mahallelist.size(); a++) {
                    if (mahalleListIsSelectedp.get(a)) {
                        mahalleListIsSelected.add(mahallelist.get(a));
                    }
                }
                mahalleSelected = "";
                for (int i = 0; i < mahalleListIsSelected.size(); i++) {
                    for (int j = 0; j < mahallelist.size(); j++) {
                        String mahallelistSelecteddata = mahalleListIsSelected.get(i);
                        String mahallelistdata = mahallelist.get(j);
                        if (Objects.equals(mahallelistdata, mahallelistSelecteddata)) {

                            if (Objects.equals(mahalleSelected, "")) {
                                mahalleSelected = mahallelerArraylst.get(j);
                            } else {
                                mahalleSelected = mahalleSelected + "," + mahallelerArraylst.get(j);
                            }
                        }
                    }
                }
                Log.i("logtestadsfragment590",mahallelist.toString());
                Log.i("logtestadsfragment591",mahalleListIsSelectedp.toString());
                ilanlarillerlist();
            }
        });
        binding.mahalleFiltreRecyclerView.setAdapter(ilanlarMahalleAdapter);
        //mahallelerlist
    }

    public void filterTags() {
        mainClass = new MainClass();
        binding.filterTagsTextView.setText("Filtre:   " + mainClass.noEmptyTag("Baslık",
                binding.ilanbaslikedittext.getText().toString())
                + mainClass.noEmptyTag("Açıklama", binding.ilanaciklamaedittext.getText().toString())
                + mainClass.noZeroTag("İlan Türü", binding.ilanturuspinner.getSelectedItemPosition(),
                binding.ilanturuspinner.getSelectedItem().toString())
                + mainClass.noZeroTag("Kimden", binding.kimdenspinner.getSelectedItemPosition(),
                binding.kimdenspinner.getSelectedItem().toString())
                /*il*//*ilçe*//*mahalle*/
                + mainClass.noEmptyTag("Marka", binding.markalarEdittext.getText().toString())
                + mainClass.noEmptyTag("Seri", binding.serilerEdittext.getText().toString())
                + mainClass.noEmptyTag("Model", binding.modellerEdittext.getText().toString())
                + mainClass.isDataBarTag("Min./Max. Yıl", Integer.valueOf(binding.startyearedittext.getText().toString()), Integer.valueOf(binding.endyearedittext.getText().toString()), (int) binding.RangeSlider.getValueFrom(), (int) binding.RangeSlider.getValueTo()) + mainClass.noEmptyTag("Min. Km", binding.startkmedittext.getText().toString())
                + mainClass.noEmptyTag("Max. Km", binding.endkmedittext.getText().toString())
                + mainClass.noEmptyTag("Motor Tipi", binding.motortipiEdittext.getText().toString())
                + mainClass.noEmptyTag("Motor Hacmi", binding.motorhacmiedittext.getText().toString())
                + mainClass.noEmptyTag("Min. Sürat", binding.startazamisuratedittext.getText().toString())
                + mainClass.noEmptyTag("Max. Sürat", binding.endazamisuratedittext.getText().toString())
                + mainClass.noEmptyTag("Yakıt Tipi", binding.yakittipiEdittext.getText().toString())
                + mainClass.noEmptyTag("Ort. Yakıt", binding.ortyakitedittext.getText().toString())
                + mainClass.noEmptyTag("Depo Hacmi", binding.depohacmiedittext.getText().toString())
                + mainClass.noEmptyTag("Min. Ücret", binding.minucretedittext.getText().toString())
                + mainClass.noEmptyTag("Max. Ücret", binding.maxucretedittext.getText().toString())
        );
    }

    public void ilanlarlist() {
        //progresDialogOpen();
        viewModel.ilanlarList(binding.sortSpinner.getSelectedItemPosition(), binding.ilanbaslikedittext.getText().toString().trim(), binding.ilanaciklamaedittext.getText().toString().trim(), binding.ilanturuspinner.getSelectedItemPosition(), binding.kimdenspinner.getSelectedItemPosition(), binding.markalarEdittext.getText().toString().trim(), binding.serilerEdittext.getText().toString().trim(), binding.modellerEdittext.getText().toString().trim(), binding.startyearedittext.getText().toString().trim(), binding.endyearedittext.getText().toString().trim(), binding.startkmedittext.getText().toString().trim(), binding.endkmedittext.getText().toString().trim(), binding.motortipiEdittext.getText().toString().trim(), binding.motorhacmiedittext.getText().toString().trim(), binding.startazamisuratedittext.getText().toString().trim(), binding.endazamisuratedittext.getText().toString().trim(), binding.yakittipiEdittext.getText().toString().trim(), binding.ortyakitedittext.getText().toString().trim(), binding.depohacmiedittext.getText().toString().trim(), binding.minucretedittext.getText().toString().trim(), binding.maxucretedittext.getText().toString().trim());
        viewModel.resultmessage.observe(getViewLifecycleOwner(), resultmesaj -> {
            switch (resultmesaj) {
                case "0":
                    Toast.makeText(getContext(), "Kayıt Yoktur", Toast.LENGTH_SHORT).show();
                    binding.ilanlarrecycview.setLayoutManager(new LinearLayoutManager(getContext()));
                    ilanlars = new ArrayList<>();
                    ilanlarAdapter = new IlanlarAdapter(ilanlars, true,getContext());
                    binding.ilanlarrecycview.setAdapter(ilanlarAdapter);
                    break;
                case "1":
                    viewModel.resultlist.observe(getViewLifecycleOwner(), resultlist -> {
                        ilanlars = new ArrayList<>();
                        int yil = 0;
                        for (int i = 0; i < resultlist.size(); i++) {
                            ilanlars.add(resultlist.get(i));
                            if (ilkSearchYear) {
                                yil = Integer.parseInt(resultlist.get(i).getyil());
                                if (i == 0) {
                                    minYil = yil;
                                    maxYil = yil;
                                }
                                if (minYil >= yil) {
                                    minYil = yil;
                                }
                                if (maxYil <= yil) {
                                    maxYil = yil;
                                }
                            }
                        }
                        if (!isNormalView) {
                            //ayriyetten kart gibi xml yapılabilir
                            binding.ilanlarrecycview.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
                            ilanlarAdapter = new IlanlarAdapter(ilanlars, false,getContext());
                        } else {
                            binding.ilanlarrecycview.setLayoutManager(new LinearLayoutManager(getContext()));
                            ilanlarAdapter = new IlanlarAdapter(ilanlars, true,getContext());
                        }

                        binding.ilanlarrecycview.setAdapter(ilanlarAdapter);

                        Toast.makeText(getContext(), ilanlars.get(0).getSayi() + " Adet listelendi", Toast.LENGTH_SHORT).show();

                        //yearchange verileri dolduruldu (baruzunluğu)
                        if (ilkSearchYear) {
                            // (baruzunluğu)
                            binding.RangeSlider.setValueFrom(minYil);
                            binding.RangeSlider.setValueTo(maxYil);
                            //nokta
                            binding.RangeSlider.setValues((float) minYil, (float) maxYil);
                            ilkSearchYear = false;
                        }
                        //yearchange verileri dolduruldu


                    });
                    break;
                default:
                    break;
            }
        });
        //progresDialogClose();
    }

    public void ilanlarmahallelist() {
        viewModel.ilanlarlarMahalleList(binding.sortSpinner.getSelectedItemPosition(), binding.ilanbaslikedittext.getText().toString().trim(), binding.ilanaciklamaedittext.getText().toString().trim(), binding.ilanturuspinner.getSelectedItemPosition(), binding.kimdenspinner.getSelectedItemPosition(),
                binding.markalarEdittext.getText().toString().trim(), binding.serilerEdittext.getText().toString().trim(), binding.modellerEdittext.getText().toString().trim(), binding.startyearedittext.getText().toString().trim(), binding.endyearedittext.getText().toString().trim(), binding.startkmedittext.getText().toString().trim(), binding.endkmedittext.getText().toString().trim(), binding.motortipiEdittext.getText().toString().trim(), binding.motorhacmiedittext.getText().toString().trim(), binding.startazamisuratedittext.getText().toString().trim(), binding.endazamisuratedittext.getText().toString().trim(), binding.yakittipiEdittext.getText().toString().trim(), binding.ortyakitedittext.getText().toString().trim(), binding.depohacmiedittext.getText().toString().trim(), binding.minucretedittext.getText().toString().trim(), binding.maxucretedittext.getText().toString().trim());
        viewModel.resultIlanlarMahalleMessage.observe(getViewLifecycleOwner(), ilanlarMahalleresultmesaj -> {
            switch (ilanlarMahalleresultmesaj) {
                case "0":
                    //Toast.makeText(getContext(), "0", Toast.LENGTH_SHORT).show();
                    break;
                case "1":
                    //Toast.makeText(getContext(), "1", Toast.LENGTH_SHORT).show();
                    viewModel.resultIlanlarMahalleList.observe(getViewLifecycleOwner(), ilanlarMahalleResultList -> {
                        //Log.i("adsfragmentlogtest522", ilanlarMahalleResultList.toString());

                        //region mahallelerlist
                        mahallelerArraylist = new ArrayList<>();
                        for (int i = 0; i < ilanlarMahalleResultList.size(); i++) {
                            mahallelerArraylist.add(ilanlarMahalleResultList.get(i).getMahalle());
                        }

                        //search için aynılar çıkarılcak
                        mahallelerArraylst = new ArrayList<>();
                        for (int f = 0; f < mahallelerArraylist.size(); f++) {
                            for (int e = 0; e < mahallelerArraylist.size(); e++) {
                                String mahalleSearch = mahallelerArraylist.get(f);
                                String ikincimahalleSearch = mahallelerArraylist.get(e);
                                if (mahalleSearch == ikincimahalleSearch) {
                                    if (!mahallelerArraylst.contains(mahalleSearch)) {
                                        mahallelerArraylst.add(mahallelerArraylist.get(e));
                                    }
                                }
                            }
                        }
                        //search için aynılar çıkarılcak

                        //region olan mahalle sayıları
                        mahallelerArrrayListCount = new ArrayList<>();
                        int mahallecount = 0;
                        for (int j = 0; j < mahallelerArraylist.size(); j++) {
                            for (int z = 0; z < mahallelerArraylist.size(); z++) {
                                if (mahallelerArraylist.get(j).equals(mahallelerArraylist.get(z))) {
                                    mahallecount = mahallecount + 1;
                                }
                            }
                            mahallelerArrrayListCount.add(j, mahallecount);
                            mahallecount = 0;
                        }
                        for (int a = 0; a < mahallelerArraylist.size(); a++) {
                            mahallelerArraylist.set(a, mahallelerArraylist.get(a) + " (" + mahallelerArrrayListCount.get(a) + ")");
                        }
                        //endregion olan mahalle sayıları

                        //region aynı olan mahalleler eklenmedi
                        mahallelerArrayliste = new ArrayList<>();
                        for (int b = 0; b < mahallelerArraylist.size(); b++) {
                            for (int c = 0; c < mahallelerArraylist.size(); c++) {
                                String mahalle = mahallelerArraylist.get(b);
                                String ikincimahalle = mahallelerArraylist.get(c);
                                if (mahalle == ikincimahalle) {
                                    if (!mahallelerArrayliste.contains(mahalle)) {
                                        mahallelerArrayliste.add(mahallelerArraylist.get(b));
                                    }
                                }
                            }
                        }
                        //endregion aynı olan mahalleler eklenmedi

                        //region mahalle dataların seçili olanları buraya yansıtıılcak
                        ArrayList<Boolean> mahallelerArraylisteIsSelected = new ArrayList<>();
                        for (int a = 0; a < mahallelerArrayliste.size(); a++) {
                            mahallelerArraylisteIsSelected.add(a, false);
                        }
                        //endregion mahalle dataların seçili olanları buraya yansıtıılcak


                        binding.mahalleFiltreRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                        ilanlarMahalleAdapter = new IlanlarMahalleAdapter(mahallelerArrayliste, mahallelerArraylisteIsSelected, new IlanlarMahalleAdapter.ItemClickListener() {
                            @Override
                            public void onItemClick(ArrayList<String> mahallelist, ArrayList<Boolean> mahalleListIsSelectedp) {

                                for (int a = 0; a < mahallelist.size(); a++) {
                                    if (mahalleListIsSelectedp.get(a)) {
                                        mahalleListIsSelected.add(mahallelist.get(a));
                                    }
                                }
                                mahalleSelected = "";
                                for (int i = 0; i < mahalleListIsSelected.size(); i++) {
                                    for (int j = 0; j < mahallelist.size(); j++) {
                                        String mahallelistSelecteddata = mahalleListIsSelected.get(i);
                                        String mahallelistdata = mahallelist.get(j);
                                        if (Objects.equals(mahallelistdata, mahallelistSelecteddata)) {

                                            if (Objects.equals(mahalleSelected, "")) {
                                                mahalleSelected = mahallelerArraylst.get(j);
                                            } else {
                                                mahalleSelected = mahalleSelected + "," + mahallelerArraylst.get(j);
                                            }

                                        }
                                    }
                                }
//                                mahallelerArraylisteIsSelected.clear();
//                               ArrayList<Boolean> list=new ArrayList<>();
//                                list=mahalleListIsSelectedp;


                                Log.i("logtestadsfragment795",mahallelerArrayliste.toString());
                                Log.i("logtestadsfragment796",mahalleListIsSelectedp.toString());
                                ilanlarillerlist();
                            }
                        });
                        binding.mahalleFiltreRecyclerView.setAdapter(ilanlarMahalleAdapter);
                    });
                    break;
                default:
                    break;
            }
        });
    }

    public void filtreButtonOnclick() {
        binding.filtreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //değişçek
                ilanlarlist();
                filterTags();
            }
        });
    }

    public void progresDialogOpen() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("İlanlar Ekranı");
        progressDialog.setMessage("Devam eden işleminiz bulunmaktadır. Lütfen bekleyiniz..");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void progresDialogClose() {
        progressDialog.cancel();
    }
}