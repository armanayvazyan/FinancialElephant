package com.example.financialelephant.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.financialelephant.Fragments.ChooserFragment;
import com.example.financialelephant.R;
import com.example.financialelephant.Utilities.Attribute;
import com.example.financialelephant.Utilities.Company;
import com.example.financialelephant.Utilities.JsonParser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ChooserFragment.OnDataPass {

    private ArrayList<Company> UpdatedCompaniesList;
    private ArrayList<Attribute> UpdatedAttributeList;

    private Bundle bundle;
    private Button mainBtn;
    private NavHostFragment finalHost;


    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(MainActivity.this.getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT){
            MainActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
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

        setContentView(R.layout.activity_main);

        initParsedData();

        mainBtn = findViewById(R.id.mainBtn);

        finalHost = NavHostFragment.create(R.navigation.nav_graph,bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, finalHost)
                .setPrimaryNavigationFragment(finalHost)
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
        ArrayList<Company> companiesList = parser.getCompaniesList();
        ArrayList<Attribute> attributeList = parser.getAttributeList();

        bundle = new Bundle();
        bundle.putParcelableArrayList("companiesList", companiesList);
        bundle.putParcelableArrayList("attributesList", attributeList);

        UpdatedAttributeList = parser.getAttributeList();
        UpdatedCompaniesList = parser.getCompaniesList();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onAttributeDataPass(ArrayList<Attribute> object) {
        UpdatedAttributeList = object;
    }

    @Override
    public void onBackPressed() {
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCompanyDataPass(ArrayList<Company> object) {
        UpdatedCompaniesList = object;
    }

    @SuppressLint("SetTextI18n")
    void openInfoScreen(){
        Bundle updatedBundle = new Bundle();
        updatedBundle.putParcelableArrayList("updatedCompaniesList", UpdatedCompaniesList);
        updatedBundle.putParcelableArrayList("updatedAttributesList", UpdatedAttributeList);

        mainBtn.setText("Start to Count");

        finalHost.getNavController().navigate(R.id.action_chooserFragment_to_informationViewFragment,updatedBundle);
    }
    void openFinalScreen(){
        Intent intent = new Intent(MainActivity.this, FinalActivity.class);
        Bundle updatedBundle = new Bundle();
        updatedBundle.putParcelableArrayList("updatedCompaniesList", UpdatedCompaniesList);
        updatedBundle.putParcelableArrayList("updatedAttributesList", UpdatedAttributeList);
        intent.putExtra("bundle",updatedBundle);
        startActivity(intent);
    }

}
