package com.example.financialelephant;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financialelephant.Fragments.ChooserFragment;
import com.example.financialelephant.RecViewAdapters.AttributeChooserRecyclerViewAdapter;
import com.example.financialelephant.RecViewAdapters.CompanyChooserRecyclerViewAdapter;
import com.example.financialelephant.Utilities.Attribute;
import com.example.financialelephant.Utilities.Company;

import java.util.ArrayList;
import java.util.Objects;

public class ChooserDialog  {

    private ChooserFragment.CompanyObjectListener coListener;
    private ChooserFragment.AttributeObjectListener attrListener;

    private Button backBtn;
    private TextView chooserText;
    private RecyclerView chooserRecView;
    private CompanyChooserRecyclerViewAdapter companyAdapter;
    private AttributeChooserRecyclerViewAdapter attributeAdapter;

    private ArrayList<Company> companiesList;
    private ArrayList<Attribute> attributeList;

    public void showDialog(Activity activity, Bundle args) {


        Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_layout);
        Objects.requireNonNull(dialog.getWindow())
                .setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        initView(dialog);

        String title = args.getString("ChooserName");
        chooserText.setText(title);

        assert title != null;
        if(title.contains("Company")){
            getCompanyChooserList(args,dialog);
        }
        if(title.contains("Attributes")){
            getAttributeChooserList(args,dialog);
        }

        backBtn.setOnClickListener(v -> {
            if(title.contains("Company")){
                companiesList = companyAdapter.getCompanyArrayList();
                coListener.onReceiveReady(companiesList);
            }
            if(title.contains("Attributes")){
                attributeList = attributeAdapter.getAttributeArrayList();
                attrListener.onReceiveReady(attributeList);

            }
            dialog.dismiss();
        });


        dialog.show();
    }

    private void initView(Dialog dialog){
        backBtn = dialog.findViewById(R.id.backBtn);
        chooserText = dialog.findViewById(R.id.chooserText);
        chooserRecView = dialog.findViewById(R.id.chooserRecView);
    }

    private void getCompanyChooserList(Bundle args,Dialog dialog){
        companiesList = args.getParcelableArrayList("companiesList");
        chooserRecView.setLayoutManager(new LinearLayoutManager(dialog.getContext()));
        companyAdapter = new CompanyChooserRecyclerViewAdapter();
        companyAdapter.setCompanyArrayList(companiesList);
        chooserRecView.setAdapter(companyAdapter);
    }

    private void getAttributeChooserList(Bundle args,Dialog dialog){
        attributeList = args.getParcelableArrayList("attributesList");
        chooserRecView.setLayoutManager(new LinearLayoutManager(dialog.getContext()));
        attributeAdapter = new AttributeChooserRecyclerViewAdapter();
        attributeAdapter.setAttributeArrayList(attributeList);
        chooserRecView.setAdapter(attributeAdapter);
    }

    public void setDataReceiveListener(ChooserFragment.CompanyObjectListener listener){
        this.coListener = listener;
    }

    public void setDataReceiveListener(ChooserFragment.AttributeObjectListener listener){
        this.attrListener = listener;
    }


}
