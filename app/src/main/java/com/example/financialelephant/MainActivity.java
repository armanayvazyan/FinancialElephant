package com.example.financialelephant;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ChooserFragment.OnDataPass {

    private static final String TAG = "MainActivity";

    private Button mainBtn;
    private ArrayList<Company> companiesList;
    private ArrayList<Attribute> attributeList;
    private Bundle bundle;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initParsedData();

        Fragment chooserFragment = new ChooserFragment();
        chooserFragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.replacableFragment, chooserFragment, chooserFragment.getClass().getSimpleName())
                .commit();


        mainBtn = findViewById(R.id.mainBtn);
        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        companiesList.removeIf(company -> !company.isChecked());

        attributeList = parser.getAttributeList();
        attributeList.removeIf(attribute -> !attribute.isChecked());

        bundle = new Bundle();
        bundle.putParcelableArrayList("companiesList",companiesList);
        bundle.putParcelableArrayList("attributesList",attributeList);
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    @Override
    public void onAttributeDataPass(ArrayList<Attribute> object) {
        Log.d(TAG, "onAttributeDataPass: ");
    }

    @Override
    public void onCompanyDataPass(ArrayList<Company> object) {

    }
}
