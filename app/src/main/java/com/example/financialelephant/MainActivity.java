package com.example.financialelephant;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button mainBtn;
    private ArrayList<Company> companiesList;
    private ArrayList<Attribute> attributeList;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initParsedData();

        Fragment chooserFragment = new ChooserFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.replacableFragment, chooserFragment, chooserFragment.getClass().getSimpleName())
                .commit();

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
        companiesList.removeIf(company -> !company.isChecked());

        attributeList = parser.getAttributeList();
        attributeList.removeIf(attribute -> !attribute.isChecked());
    }
}
