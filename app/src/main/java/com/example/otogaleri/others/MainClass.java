package com.example.otogaleri.others;

import android.util.Log;

public class MainClass {

    //edittext iççin
    public String noEmptyTag(String tagBaslik,String data){
        if(!data.equals(""))
        {
            return " "+tagBaslik+": "+data+" / ";
        }
        return "";
    }

    //spinner için
    public String noZeroTag(String tagBaslik,int index,String data){
        if(index!=0){
            return " "+tagBaslik+": "+data+" / ";
        }
        return "";
    }

    //bar için
    public String isDataBarTag(String tagBaslik,int minSayi,int maxSayi,int minSepSayi,int maxSepSayi){
        Log.i("logtest23",minSayi+"/"+minSepSayi +"/"+  maxSayi+"/"+maxSepSayi);
        if(minSayi!=minSepSayi ||  maxSayi!=maxSepSayi){
            return " "+tagBaslik+": "+minSayi+"-"+maxSayi+" / ";
        }
        return "";
    }
}
