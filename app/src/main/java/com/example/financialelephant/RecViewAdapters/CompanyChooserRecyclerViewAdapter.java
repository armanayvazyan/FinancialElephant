package com.example.financialelephant.RecViewAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financialelephant.R;
import com.example.financialelephant.Utilities.Company;

import java.util.ArrayList;

public class CompanyChooserRecyclerViewAdapter extends RecyclerView.Adapter<CompanyChooserRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Company> companyArrayList;

    public CompanyChooserRecyclerViewAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chooser_recycler_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(companyArrayList.get(position).isChecked()){
            holder.aSwitch.setChecked(true);
        } else
            holder.aSwitch.setChecked(false);

        holder.simpleTxt.setText(companyArrayList.get(position).getName());
        holder.aSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(holder.aSwitch.isChecked()){
                companyArrayList.get(position).setChecked(true);
            } else
                companyArrayList.get(position).setChecked(false);
        });
    }

    @Override
    public int getItemCount() {
        return companyArrayList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView simpleTxt;
        Switch aSwitch;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            simpleTxt = itemView.findViewById(R.id.simpleTxt);
            aSwitch = itemView.findViewById(R.id.checkbox);
        }
    }

    public ArrayList<Company> getCompanyArrayList() {
        return companyArrayList;
    }

    public void setCompanyArrayList(ArrayList<Company> companyArrayList) {
        this.companyArrayList = companyArrayList;
        notifyDataSetChanged();
    }

}
