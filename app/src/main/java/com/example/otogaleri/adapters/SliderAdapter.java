package com.example.otogaleri.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.otogaleri.R;
import com.example.otogaleri.constants.Constants;
import com.example.otogaleri.models.SliderModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderAdapter extends PagerAdapter {

    List<SliderModel> list;
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(List<SliderModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);
        ImageView imageView=view.findViewById(R.id.sliderImageView);
        Picasso.get().load(Constants.urlApp+Constants.myAdsImagePagePath +"/"+list.get(position).getResim()).resize(500, 500).into(imageView);
        container.addView(view);
        return view;
    }
}
