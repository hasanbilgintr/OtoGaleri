package com.example.otogaleri;


import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;


public class ChangeFragment {
    private Context context;
    private Fragment fragment;
    private String addToBackStackString;

    public ChangeFragment(Context context, Fragment fragment, String addToBackStackString) {
        this.context = context;
        this.fragment = fragment;
        this.addToBackStackString = addToBackStackString;
    }

    public void change() {
        ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_login, fragment, "fragment").addToBackStack(addToBackStackString).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
    }
}

