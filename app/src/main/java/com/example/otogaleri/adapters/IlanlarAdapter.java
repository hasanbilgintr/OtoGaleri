package com.example.otogaleri.adapters;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.collection.CircularArray;
import androidx.recyclerview.widget.RecyclerView;

import com.example.otogaleri.databinding.RecycviewCardrowBinding;
import com.example.otogaleri.databinding.RecycviewRowBinding;
import com.example.otogaleri.models.Ilanlar;
import java.util.ArrayList;
import java.util.List;

import com.squareup.picasso.Picasso;
public class IlanlarAdapter  extends RecyclerView.Adapter<IlanlarAdapter.IlanlarkHolder>{
    ArrayList<Ilanlar> ilanlarlist;
    boolean isNormal=true;

    public IlanlarAdapter(ArrayList<Ilanlar> Ilanlarlist,boolean isNormal) {
        this.ilanlarlist = Ilanlarlist;
        this.isNormal=isNormal;
    }

    @NonNull
    @Override
    //viewholder sınıfından bir obje oluşturulduğunda çağırılcak olan metot
    //xmlimizi(binding) bağlama işlemini burda yapıcaz
    public IlanlarkHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(isNormal)
        {
            RecycviewRowBinding  recycviewRowBinding = RecycviewRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new IlanlarkHolder(recycviewRowBinding);
        }else{
            RecycviewCardrowBinding  recycviewCardrowBinding = RecycviewCardrowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new IlanlarkHolder(recycviewCardrowBinding);
        }

    }

    @Override
    //layoutumun içersinde hangi verileri göstermek istersek burda yazıcaz
    public void onBindViewHolder(IlanlarAdapter.IlanlarkHolder holder, int position) {
        if(isNormal){
            holder.binding.basliktextview.setText(ilanlarlist.get(position).getbaslik());
            holder.binding.adrestextview.setText(ilanlarlist.get(position).getSehirAdi()+" / "+ilanlarlist.get(position).getilce()+" / "+ilanlarlist.get(position).getmahalle());
            holder.binding.fiyattextview.setText(ilanlarlist.get(position).getucret());
            holder.binding.markaSeriModelTextview.setText(ilanlarlist.get(position).getmarka()+" / "+ilanlarlist.get(position).getseri()+" / "+ilanlarlist.get(position).getmodel());
            Picasso.get().load("http://hasanbilgin.web.tr/OtoGaleri//MyAdsImagePage/"+ilanlarlist.get(position).getyol()).resize(150,150).into(holder.binding.ilanlarresim);
        }else{
            holder.cardBinding.baslikTextView.setText(ilanlarlist.get(position).getbaslik());
            holder.cardBinding.adresTextView.setText(ilanlarlist.get(position).getSehirAdi()+"/"+ilanlarlist.get(position).getilce()+"/"+ilanlarlist.get(position).getmahalle());
            holder.cardBinding.ucretTextView.setText(ilanlarlist.get(position).getucret());
            holder.cardBinding.markaSeriModelTextView.setText(ilanlarlist.get(position).getmarka()+"/"+ilanlarlist.get(position).getseri()+"/"+ilanlarlist.get(position).getmodel());
            Picasso.get().load("http://hasanbilgin.web.tr/OtoGaleri//MyAdsImagePage/"+ilanlarlist.get(position).getyol()).resize(200,200).into(holder.cardBinding.ilanlarresim);
        }

holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(), "asdsadasdsad", Toast.LENGTH_SHORT).show();
    }
});


//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
////                Singleton singleton = Singleton.getInstance();
////                //ıntent yöntemi //intent.putExtra("landmark",landmarkList.get(position));
////                //intent veriler gönderirken kapasitesi önemli bielli kapasiteden sonra mesela bitmap atılamaz
////                //statik yöntem static oluştururuz heryerden get set edilerek veriler çekilebilir
////                //buda set bir defa edilebiliyo sonrası için edilemiyor bu şekilde kontrolünü sağalmış oluyoruz
////                singleton.setChosenLandmark(landmarkList.get(position));
////                holder.itemView.getContext().startActivity(intent);
//                Toast.makeText(v.getContext(), "nbr", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        //xml kaç kere oluşacağını sölücek yani item sayısı yani 4
        return ilanlarlist.size();
    }
    //
    //LandmarkHolder isim verilerek görünümlerimizi tutacak yardımcı bir sınıf
    //ViewHolder yani görünüm tutucu
    public class IlanlarkHolder extends RecyclerView.ViewHolder {

        //layout ismi binding alındı
        private RecycviewRowBinding binding;
        private RecycviewCardrowBinding cardBinding;


        public IlanlarkHolder(RecycviewRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public IlanlarkHolder(RecycviewCardrowBinding cardBinding) {
            super(cardBinding.getRoot());
            this.cardBinding = cardBinding;
        }
    }
}
