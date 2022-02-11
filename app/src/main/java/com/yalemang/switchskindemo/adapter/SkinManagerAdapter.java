package com.yalemang.switchskindemo.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yalemang.switchskindemo.R;

import java.util.List;

public class SkinManagerAdapter extends RecyclerView.Adapter<SkinManagerAdapter.SkinManagerViewHolder> {

    private List<String> skinNameList;
    private ItemClick itemClick;

    public SkinManagerAdapter(List<String> skinNameList){
        this.skinNameList = skinNameList;
    }

    public void setItemClick(ItemClick itemClick) {
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public SkinManagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_skin_manager,parent,false);
        return new SkinManagerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SkinManagerViewHolder holder, @SuppressLint("RecyclerView") int position) {
       holder.tvSkinName.setText(skinNameList.get(position));
       holder.itemView.setOnClickListener(v -> {
           if(itemClick != null){
               itemClick.click(position);
           }
       });
    }

    @Override
    public int getItemCount() {
        return skinNameList.size();
    }

    static class SkinManagerViewHolder extends RecyclerView.ViewHolder{

        TextView tvSkinName;

        public SkinManagerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSkinName = itemView.findViewById(R.id.tv_skin_name);
        }
    }

    public interface ItemClick{
        void click(int position);
    }
}
