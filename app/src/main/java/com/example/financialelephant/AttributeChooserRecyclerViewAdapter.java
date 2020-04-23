package com.example.financialelephant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AttributeChooserRecyclerViewAdapter extends RecyclerView.Adapter<AttributeChooserRecyclerViewAdapter.ViewHolder>  {

    private ArrayList<Attribute> attributeArrayList;
    private Context context;

    AttributeChooserRecyclerViewAdapter(Context context) {
        this.context = context;
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
            holder.checkBox.setChecked(true);
        } else
            holder.checkBox.setChecked(false);

        holder.simpleTxt.setText(attributeArrayList.get(position).getName());
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(holder.checkBox.isChecked()){
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
        CheckBox checkBox;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            simpleTxt = itemView.findViewById(R.id.simpleTxt);
            checkBox = itemView.findViewById(R.id.checkbox);
        }
    }

    ArrayList<Attribute> getAttributeArrayList() {
        return attributeArrayList;
    }

    void setAttributeArrayList(ArrayList<Attribute> attributeArrayList) {
        this.attributeArrayList = attributeArrayList;
        notifyDataSetChanged();
    }

}
