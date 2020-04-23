package com.example.financialelephant;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ChooserFragment.OnDataPass {

    private static final String TAG = "MainActivity";

    private ArrayList<Company> companiesList;
    private ArrayList<Attribute> attributeList;

    private ArrayList<Company> UpdatedCompaniesList;
    private ArrayList<Attribute> UpdatedAttributeList;

    private Bundle bundle;
    private Button mainBtn;
    private Fragment infoCardFragment,chooserFragment;


    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(MainActivity.this.getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT){
            MainActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initParsedData();

        infoCardFragment = new InformationViewFragment();
        chooserFragment = new ChooserFragment();
        mainBtn = findViewById(R.id.mainBtn);

        chooserFragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.replacableFragment, chooserFragment, chooserFragment.getClass().getSimpleName())
                .commit();


        mainBtn.setOnClickListener(v -> {
            UpdatedCompaniesList.removeIf(company -> !company.isChecked());
            UpdatedAttributeList.removeIf(attribute -> !attribute.isChecked());

            if(UpdatedCompaniesList.isEmpty()){
                Toast.makeText(this, "Company list Is Empty", Toast.LENGTH_SHORT).show();
                recreate();
            }
            if(UpdatedAttributeList.isEmpty()){
                Toast.makeText(this, "Attribute list Is Empty", Toast.LENGTH_SHORT).show();
                recreate();
            }
            else if(mainBtn.getText().toString().equals("Start")) {
                openInfoScreen();
            }
            else if(mainBtn.getText().toString().equals("Start to Count")){
                openFinalScreen();
            }
            });

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initParsedData(){
        JsonParser parser = new JsonParser();
        try{
            parser.convertToJson(MainActivity.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        companiesList = parser.getCompaniesList();
        attributeList = parser.getAttributeList();

        bundle = new Bundle();
        bundle.putParcelableArrayList("companiesList",companiesList);
        bundle.putParcelableArrayList("attributesList",attributeList);

        UpdatedAttributeList = parser.getAttributeList();
        UpdatedCompaniesList = parser.getCompaniesList();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onAttributeDataPass(ArrayList<Attribute> object) {
        UpdatedAttributeList = object;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCompanyDataPass(ArrayList<Company> object) {
        UpdatedCompaniesList = object;
    }

    void openInfoScreen(){
        Bundle updatedBundle = new Bundle();
        updatedBundle.putParcelableArrayList("updatedCompaniesList", UpdatedCompaniesList);
        updatedBundle.putParcelableArrayList("updatedAttributesList", UpdatedAttributeList);
        infoCardFragment.setArguments(updatedBundle);

        mainBtn.setText("Start to Count");

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.replacableFragment, infoCardFragment, infoCardFragment.getClass().getSimpleName())
                .commit();
    }

    void openFinalScreen(){
        Intent intent = new Intent(MainActivity.this,FinalActivity.class);
        Bundle updatedBundle = new Bundle();
        updatedBundle.putParcelableArrayList("updatedCompaniesList", UpdatedCompaniesList);
        updatedBundle.putParcelableArrayList("updatedAttributesList", UpdatedAttributeList);
        intent.putExtra("bundle",updatedBundle);
        startActivity(intent);
    }

}
