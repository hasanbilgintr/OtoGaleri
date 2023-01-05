package com.example.otogaleri.pages.deneme;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FragmentdenemeViewModel extends ViewModel {
    // TODO: Implement the ViewModel


    private final MutableLiveData<String> mText;

    public FragmentdenemeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is denemenin fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}