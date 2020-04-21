package com.example.financialelephant;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.ConditionVariable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ChooserFragment extends Fragment {

    private RelativeLayout companyChooserContainer;
    private RelativeLayout attributeChooserContainer;
    private TextView chooseCompanyTxt;
    private TextView chooseAttributesTxt;


    public static ChooserFragment newInstance() {
        return new ChooserFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.chooser_fragment, container, false);

        initViews(view);
        companyChooserContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooserDialog alert = new ChooserDialog();
                Bundle args = new Bundle();
                args.putString("ChooserName", chooseCompanyTxt.getText().toString());
                alert.showDialog(getActivity(),args);
            }
        });

        attributeChooserContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooserDialog alert = new ChooserDialog();
                Bundle args = new Bundle();
                args.putString("ChooserName",chooseAttributesTxt.getText().toString());
                alert.showDialog(getActivity(), args);
            }
        });


        return view;


    }


    private void initViews(View view){
        chooseCompanyTxt = view.findViewById(R.id.chooseCompanyTxt);
        chooseAttributesTxt = view.findViewById(R.id.chooseAttributesTxt);
        companyChooserContainer = view.findViewById(R.id.companyChooserContainer);
        attributeChooserContainer = view.findViewById(R.id.attributeChooserContainer);
    }



}
