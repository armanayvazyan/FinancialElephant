package com.example.financialelephant.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.example.financialelephant.R;
import com.example.financialelephant.Utilities.*;
import java.util.ArrayList;


public class FinalActivity extends AppCompatActivity {

    LottieAnimationView mediaViewLottie;
    ImageView winnerCompanyImg;
    Button backBtn;

    private ArrayList<Company> companiesList;
    private ArrayList<Attribute> attributeList;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(FinalActivity.this.getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT){
            FinalActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        }

        super.onCreate(savedInstanceState);

        switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
            case Configuration.UI_MODE_NIGHT_YES:
                setTheme(R.style.DarkTheme);
                break;
            case Configuration.UI_MODE_NIGHT_NO:
                setTheme(R.style.LightTheme);
                break;
        }

        setContentView(R.layout.activity_final);

        mediaViewLottie = findViewById(R.id.media_view_lottie);
        winnerCompanyImg = findViewById(R.id.winnerCompanyImg);
        backBtn = findViewById(R.id.backBtn);

        mediaViewLottie.bringToFront();
        mediaViewLottie.setAnimationFromUrl("https://cdn130.picsart.com/05451672185558253119.json");
        mediaViewLottie.playAnimation();
        mediaViewLottie.setVisibility(View.VISIBLE);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        assert bundle != null;
        companiesList = bundle.getParcelableArrayList("updatedCompaniesList");
        attributeList = bundle.getParcelableArrayList("updatedAttributesList");

        Glide.with(this).load(getImage(Analyser.analyseData(companiesList,attributeList).getImgUrl())).into(winnerCompanyImg);

        backBtn.setOnClickListener(c->{
            Intent intent1 = new Intent(FinalActivity.this, MainActivity.class);
            startActivity(intent1);
        });
    }

    public int getImage(String imageName) {
        return getResources().getIdentifier(imageName, "drawable", getPackageName());
    }
}
