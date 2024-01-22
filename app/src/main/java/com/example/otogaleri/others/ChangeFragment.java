package com.example.otogaleri.others;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.otogaleri.R;


public class ChangeFragment {
    private final Context context;
    private final Fragment fragment;
    private final String addToBackStackString;
    private final int fragmentlayout;

    public ChangeFragment(Context context, Fragment fragment, String addToBackStackString,int fragmentlayout) {
        this.context = context;
        this.fragment = fragment;
        this.addToBackStackString = addToBackStackString;
        this.fragmentlayout = fragmentlayout;
    }

    public ChangeFragment(Context context, Fragment fragment, String addToBackStackString, int fragmentlayout, Bundle bundle) {
        this.context = context;
        this.fragment = fragment;
        this.addToBackStackString = addToBackStackString;
        this.fragmentlayout = fragmentlayout;
        this.fragment.setArguments(bundle);
    }

    public void change() {
        //((FragmentActivity) context).getSupportFragmentManager().beginTransaction().replace(fragmentlayout, fragment, "fragment").addToBackStack(addToBackStackString).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
        //setCustomAnimations(R.anim.anim_in,R.anim.anim_out) bu şekilde fragment açılırken açılış animasyonu eklendi
        ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.anim_in,R.anim.anim_out).replace(fragmentlayout, fragment, "fragment").addToBackStack(addToBackStackString).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
        }
}

