package com.example.financialelephant.RecViewAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financialelephant.R;
import com.example.financialelephant.Utilities.Attribute;

import java.util.ArrayList;

public class AttributeChooserRecyclerViewAdapter extends RecyclerView.Adapter<AttributeChooserRecyclerViewAdapter.ViewHolder>  {

    private ArrayList<Attribute> attributeArrayList;

    public AttributeChooserRecyclerViewAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chooser_recycler_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(attributeArrayList.get(position).isChecked()){
            holder.aSwitch.setChecked(true);
        } else
            holder.aSwitch.setChecked(false);

        holder.simpleTxt.setText(attributeArrayList.get(position).getName());
        holder.aSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(holder.aSwitch.isChecked()){
                attributeArrayList.get(position).setChecked(true);
            } else
                attributeArrayList.get(position).setChecked(false);
        });

    }

    @Override
    public int getItemCount() {
        return attributeArrayList.size();
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

    public ArrayList<Attribute> getAttributeArrayList() {
        return attributeArrayList;
    }

    public void setAttributeArrayList(ArrayList<Attribute> attributeArrayList) {
        this.attributeArrayList = attributeArrayList;
        notifyDataSetChanged();
    }

}
