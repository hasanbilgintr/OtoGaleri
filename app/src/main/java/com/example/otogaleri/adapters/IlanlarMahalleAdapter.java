package com.example.otogaleri.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.otogaleri.databinding.IlanlarMahalleRecyclerviewRowBinding;

import java.util.ArrayList;


public class IlanlarMahalleAdapter extends RecyclerView.Adapter<IlanlarMahalleAdapter.MahallelerkHolder> {

    private ArrayList<String> mahallelist;
    private ArrayList<Boolean> mahalleListIsSelected;
    private ItemClickListener itemClickListener;

    public IlanlarMahalleAdapter(ArrayList<String> mahallelist, ArrayList<Boolean> mahalleListIsSelected, ItemClickListener itemClickListener) {
        this.mahallelist = mahallelist;
        this.itemClickListener = itemClickListener;
        this.mahalleListIsSelected = mahalleListIsSelected;
    }

    @NonNull
    @Override
    public IlanlarMahalleAdapter.MahallelerkHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        IlanlarMahalleRecyclerviewRowBinding ilanlarMahalleRecyclerviewRowBinding = IlanlarMahalleRecyclerviewRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new IlanlarMahalleAdapter.MahallelerkHolder(ilanlarMahalleRecyclerviewRowBinding);
    }

    @Override
    //layoutumun içersinde hangi verileri göstermek istersek burda yazıcaz
    public void onBindViewHolder(IlanlarMahalleAdapter.MahallelerkHolder holder, int position) {

        holder.binding.CheckBox.setText(mahallelist.get(position));
        //mahalleListIsSelected.set(position, holder.binding.CheckBox.isChecked());
        holder.binding.CheckBox.setChecked(mahalleListIsSelected.get(position));
        holder.binding.CheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mahalleListIsSelected.set(position,holder.binding.CheckBox.isChecked());
                if(holder.binding.CheckBox.isChecked()){
                    mahalleListIsSelected.set(position,true);

                }else{
                    mahalleListIsSelected.set(position,false);
                }

                itemClickListener.onItemClick(mahallelist, mahalleListIsSelected);
            }
        });
    }

    @Override
    public int getItemCount() {
        //xml kaç kere oluşacağını sölücek yani item sayısı yani 4
        return mahallelist.size();
    }

    //https://www.youtube.com/watch?v=90khNCjWUEI //recyclerview tıklama için
    public interface ItemClickListener {
        void onItemClick(ArrayList<String> mahallelist, ArrayList<Boolean> mahalleListIsSelected);
    }


    public class MahallelerkHolder extends RecyclerView.ViewHolder {

        //layout ismi binding alındı
        private IlanlarMahalleRecyclerviewRowBinding binding;

        public MahallelerkHolder(IlanlarMahalleRecyclerviewRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}






