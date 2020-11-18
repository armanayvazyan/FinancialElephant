package com.example.financialelephant.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.financialelephant.RecViewAdapters.InfoCardRecyclerViewAdapter;
import com.example.financialelephant.R;
import com.example.financialelephant.Utilities.Attribute;
import com.example.financialelephant.Utilities.Company;

import java.util.ArrayList;


public class InformationViewFragment extends Fragment {

    private RecyclerView cardRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_information_view, container, false);
        initViews(view);

        assert getArguments() != null;
        ArrayList<Company> companiesList = getArguments().getParcelableArrayList("updatedCompaniesList");
        ArrayList<Attribute> attributeList = getArguments().getParcelableArrayList("updatedAttributesList");

        cardRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        InfoCardRecyclerViewAdapter cardAdapter = new InfoCardRecyclerViewAdapter(getActivity(), attributeList);
        cardAdapter.setCompanyArrayList(companiesList);
        cardRecyclerView.setAdapter(cardAdapter);

        SnapHelper snap = new PagerSnapHelper();
        snap.attachToRecyclerView(cardRecyclerView);


        return view;
    }


    private void initViews(View view){
        cardRecyclerView = view.findViewById(R.id.cardRecyclerView);

    }
}
