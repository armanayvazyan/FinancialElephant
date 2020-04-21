package com.example.financialelephant;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChooserDialog  {

    private ChooserFragment.CompanyObjectListener coListener;
    private ChooserFragment.AttributebjectListener attrListener;

    private ImageView backBtn;
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
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

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

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(title.contains("Company")){
                    coListener.onReceiveReady(companiesList);
                }
                if(title.contains("Attributes")){
                    attributeList = attributeAdapter.getAttributeArrayList();
                    attrListener.onReceiveReady(attributeList);

                }
                dialog.dismiss();
            }
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
        companyAdapter = new CompanyChooserRecyclerViewAdapter(dialog.getContext());
        companyAdapter.setCompanyArrayList(companiesList);
        chooserRecView.setAdapter(companyAdapter);
    }

    private void getAttributeChooserList(Bundle args,Dialog dialog){
        attributeList = args.getParcelableArrayList("attributesList");
        chooserRecView.setLayoutManager(new LinearLayoutManager(dialog.getContext()));
        attributeAdapter = new AttributeChooserRecyclerViewAdapter(dialog.getContext());
        attributeAdapter.setAttributeArrayList(attributeList);
        chooserRecView.setAdapter(attributeAdapter);
    }

    public void setDataReceiveListener(ChooserFragment.CompanyObjectListener listener){
        this.coListener = listener;
    }

    public void setDataReceiveListener(ChooserFragment.AttributebjectListener listener){
        this.attrListener = listener;
    }


}
