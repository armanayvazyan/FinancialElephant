package com.example.financialelephant;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;


public class InformationViewFragment extends Fragment {

    private RecyclerView cardRecyclerView;
    private InfoCardRecyclerViewAdapter cardAdapter;
    private ArrayList<Company> companiesList;
    private ArrayList<Attribute> attributeList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_information_view, container, false);
        initViews(view);

        assert getArguments() != null;
        companiesList = getArguments().getParcelableArrayList("updatedCompaniesList");
        attributeList = getArguments().getParcelableArrayList("updatedAttributesList");

        cardRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        cardAdapter = new InfoCardRecyclerViewAdapter(getActivity(),attributeList);
        cardAdapter.setCompanyArrayList(companiesList);
        cardRecyclerView.setAdapter(cardAdapter);

        SnapHelper snap = new PagerSnapHelper();
        snap.attachToRecyclerView(cardRecyclerView);


        return view;
    }


    void initViews(View view){
        cardRecyclerView = view.findViewById(R.id.cardRecyclerView);

    }
}
