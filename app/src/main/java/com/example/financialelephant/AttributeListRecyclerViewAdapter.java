package com.example.financialelephant;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AttributeListRecyclerViewAdapter extends RecyclerView.Adapter<AttributeListRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Company> companyArrayList;
    private ArrayList<Attribute> attributeArrayList;
    private Company company;

    public AttributeListRecyclerViewAdapter(Company company) {
        this.company = company;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.atrribute_list_rec_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.attributeName.setText(attributeArrayList.get(position).getName());
        {
            for (Attribute attribute : company.getAttributes()){
                if(attributeArrayList.get(position).getName().equals(attribute.getName()))
                    holder.attributeValue.setText(String.valueOf(attribute.getValue()));
            }
        }

    }

    @Override
    public int getItemCount() {
        return attributeArrayList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView attributeName,attributeValue;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            attributeValue = itemView.findViewById(R.id.attributeValue);
            attributeName = itemView.findViewById(R.id.attributeName);
        }
    }

    public void setCompanyArrayList(ArrayList<Attribute> attributeArrayList) {
        notifyDataSetChanged();
        this.attributeArrayList = attributeArrayList;
    }
}
