package com.example.financialelephant;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.ConditionVariable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ChooserFragment extends Fragment {

    private static final String TAG = "ChooserFragment";

    private RelativeLayout companyChooserContainer;
    private RelativeLayout attributeChooserContainer;
    private TextView chooseCompanyTxt;
    private TextView chooseAttributesTxt;

    private ArrayList<Company> companiesList;
    private ArrayList<Attribute> attributeList;

    private OnDataPass listener;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.chooser_fragment, container, false);

        initViews(view);



        companyChooserContainer.setOnClickListener(v -> {
            ChooserDialog alert = new ChooserDialog();
            Bundle args = new Bundle();
            args.putString("ChooserName", chooseCompanyTxt.getText().toString());
            args.putParcelableArrayList("companiesList",companiesList);
            alert.showDialog(getActivity(),args);
            alert.setDataReceiveListener((CompanyObjectListener) object -> {
                companiesList = object;
                listener.onCompanyDataPass(companiesList);
            });
        });
        attributeChooserContainer.setOnClickListener(v -> {
            ChooserDialog alert = new ChooserDialog();
            Bundle args = new Bundle();
            args.putString("ChooserName",chooseAttributesTxt.getText().toString());
            args.putParcelableArrayList("attributesList",attributeList);
            alert.showDialog(getActivity(), args);
            alert.setDataReceiveListener((AttributebjectListener) object -> {
                attributeList = object;
                listener.onAttributeDataPass(attributeList);
            });
        });


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnDataPass) context;
        assert getArguments() != null;
        companiesList = getArguments().getParcelableArrayList("companiesList");
        attributeList = getArguments().getParcelableArrayList("attributesList");
    }


    private void initViews(View view){
        chooseCompanyTxt = view.findViewById(R.id.chooseCompanyTxt);
        chooseAttributesTxt = view.findViewById(R.id.chooseAttributesTxt);
        companyChooserContainer = view.findViewById(R.id.companyChooserContainer);
        attributeChooserContainer = view.findViewById(R.id.attributeChooserContainer);
    }




    public interface CompanyObjectListener {

        public void onReceiveReady(ArrayList<Company> object);
    }

    public interface AttributebjectListener {

        public void onReceiveReady(ArrayList<Attribute> object);
    }


    public interface OnDataPass {
        public void onAttributeDataPass(ArrayList<Attribute> object);
        public void onCompanyDataPass(ArrayList<Company> object);
    }


}
