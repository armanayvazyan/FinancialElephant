package com.example.financialelephant;

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

import java.io.IOException;
import java.util.ArrayList;
import static com.example.financialelephant.Analyser.AnaliseWithGivenParameters;


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
        setContentView(R.layout.activity_final);

        mediaViewLottie = findViewById(R.id.media_view_lottie);
        winnerCompanyImg = findViewById(R.id.winnerCompanyImg);
        backBtn = findViewById(R.id.backBtn);


        mediaViewLottie.setAnimationFromUrl("https://cdn130.picsart.com/05451672185558253119.json");
        mediaViewLottie.playAnimation();
        mediaViewLottie.setVisibility(View.VISIBLE);


        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        companiesList = bundle.getParcelableArrayList("updatedCompaniesList");
        attributeList = bundle.getParcelableArrayList("updatedAttributesList");


        Glide.with(this).load(getImage( analyseData().getImgUrl())).into(winnerCompanyImg);

        backBtn.setOnClickListener(c->{
            Intent intent1 = new Intent(FinalActivity.this,MainActivity.class);
            startActivity(intent1);
        });

    }

    Company analyseData(){
        double[][] p = new double[attributeList.size()][companiesList.size()];
        int k = 0;
        for (Attribute attribute : attributeList) {
            p[k++] = AnaliseWithGivenParameters(companiesList, attribute);
        }

        double[] pp = AnaliseWithGivenParameters(attributeList);

        double[] finalResults = new double[companiesList.size()];

        for (int i = 0; i < companiesList.size(); i++) {
            finalResults[i] = 0;
            for (int j = 0; j < attributeList.size(); j++) {
                finalResults[i] += pp[j] * p[j][i];
            }
        }
        System.out.println("WINNING COMPANY NAME");
        System.out.println();
        double max = finalResults[0];;
        for (int i = 0; i < finalResults.length; i++) {
            if(i>0){
                if(finalResults[i] > max)
                    max = finalResults[i];
            }
        }
        int index =0;
        for(double q : finalResults){
            if(q == max){
                return companiesList.get(index);
            } else
                index++;
        }
        return null;
    }

    public int getImage(String imageName) {

        int drawableResourceId = getResources().getIdentifier(imageName, "drawable", getPackageName());
        return drawableResourceId;
    }
}
