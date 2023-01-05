package com.example.otogaleri.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.otogaleri.R;
import com.example.otogaleri.others.ChangeFragment;
import com.example.otogaleri.pages.ads.AdsFragment;
import com.example.otogaleri.pages.myads.MyAdsFragment;

public class ContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        Bundle bundle = getIntent().getExtras();
        String fragment = bundle.getString("fragment");

        if (fragment.equals("myads")) {
            ChangeFragment changeFragment = new ChangeFragment(ContentActivity.this, new MyAdsFragment(), "replaceFragMyAds", R.id.content_FrameLayout);
            changeFragment.change();
        }
        if (fragment.equals("ads")) {
            ChangeFragment changeFragment = new ChangeFragment(ContentActivity.this, new AdsFragment(), "replaceFragAds", R.id.content_FrameLayout);
            changeFragment.change();
        }

        //aktivite için animasyon eklemesi için kullandık
        //overridePendingTransition(R.anim.anim_in,R.anim.anim_out);

    }

//    //activite açılıp sonra fragmente açılıyor fragmentte geri tuşu yaptığında alt kodlar çalışçaktır direk MainActivity aktivitesi açılcaktır
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            //Toast.makeText(this, "aktivity", Toast.LENGTH_SHORT).show();
////            Intent intent = new Intent(ContentActivity.this, MainActivity.class);
////            startActivity(intent);
//            //buda iş görür
//
//            //startActivity(new Intent(ContentActivity.this,MainActivity.class));
//        }
//        return super.onKeyDown(keyCode, event);
//    }


}