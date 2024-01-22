package com.example.otogaleri.pages.adsDetail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.otogaleri.adapters.SliderAdapter;
import com.example.otogaleri.databinding.FragmentAdDetailBinding;
import com.example.otogaleri.models.SliderModel;

import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class AdDetailFragment extends Fragment {


    private FragmentAdDetailBinding binding;
    private AdDetailViewModel viewModel;
    //private ViewPager sliderViewPaper;
    private String ilanid;
    List<SliderModel> list;
    SliderAdapter sliderAdapter;
    CircleIndicator circleIndicator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewModel = new ViewModelProvider(this).get(AdDetailViewModel.class);
        binding = FragmentAdDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        getData();
        getAdsDetail();
    }

    private void getData() {
        if (getArguments() != null) {
            ilanid = getArguments().getString("ilanid");
        }
    }

    private void getAdsDetail() {
        viewModel.adDetail(ilanid);
        viewModel.adDetailPictures(ilanid);
        viewModel.resultDetail.observe(getViewLifecycleOwner(), result -> {
            //Log.i("logtestaddetailfrag1149",result.getBaslik());
            binding.baslikTextView.setText(result.getBaslik());
            binding.adressTextView.setText(result.getSehiradi() + " / " + result.getIlce() + " / " + result.getMahalle());
            binding.priceTextView.setText(result.getUcret());
            binding.brandTextView.setText(result.getMarka());
            binding.modelTextView.setText(result.getModel());
            binding.seriesTextView.setText(result.getSeri());
            binding.yearTextView.setText(result.getYil());
            binding.adTypeTextView.setText(result.getIlantipi());
            binding.isWhoTextView.setText(result.getKimden());
            binding.motorTypeTextView.setText(result.getMotortipi());
            binding.motorCapacityTextView.setText(result.getMotorhacmi());
            binding.speedTextView.setText(result.getSurat());
            binding.fuelTypeTextView.setText(result.getYakittipi());
            binding.averageFuelTextView.setText(result.getOrtalamayakit());
            binding.warehouseCapacityTextView.setText(result.getDepohacmi());
            binding.kmTextView.setText(result.getKm());
        });
        viewModel.resultDetailPicturesList.observe(getViewLifecycleOwner(), resultList -> {
            list=resultList;
            sliderAdapter = new SliderAdapter(list, getContext());
            binding.sliderViewPager.setAdapter(sliderAdapter);
            binding.slideNoktaCircleIndicator.setViewPager(binding.sliderViewPager);
            //öne getirmek nokta yapısıı
            binding.slideNoktaCircleIndicator.bringToFront();

        });
    }


}