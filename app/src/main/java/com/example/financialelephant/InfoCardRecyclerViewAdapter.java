package com.example.financialelephant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class InfoCardRecyclerViewAdapter extends RecyclerView.Adapter<InfoCardRecyclerViewAdapter.ViewHolder>{

    private ArrayList<Company> companyArrayList;
    private ArrayList<Attribute> attributeArrayList;
    Context context;


    public InfoCardRecyclerViewAdapter(Context context, ArrayList<Attribute> attributeArrayList) {
        this.context = context;
        this.attributeArrayList = attributeArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_info_recycler_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AttributeListRecyclerViewAdapter attributeAdapter = new AttributeListRecyclerViewAdapter(companyArrayList.get(position));

        Glide.with(context).load(getImage(companyArrayList.get(position).getImgUrl())).into(holder.companyImg);



        attributeAdapter.setCompanyArrayList(attributeArrayList);
        holder.attributeListRecView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        holder.attributeListRecView.setAdapter(attributeAdapter);

    }

    @Override
    public int getItemCount() {
        return companyArrayList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView companyImg;
        RecyclerView attributeListRecView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            companyImg = itemView.findViewById(R.id.companyImg);
            attributeListRecView = itemView.findViewById(R.id.attributeListRecView);
        }
    }

    public void setCompanyArrayList(ArrayList<Company> companyArrayList) {
        notifyDataSetChanged();
        this.companyArrayList = companyArrayList;
    }
    public int getImage(String imageName) {

        int drawableResourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        return drawableResourceId;
    }


}
