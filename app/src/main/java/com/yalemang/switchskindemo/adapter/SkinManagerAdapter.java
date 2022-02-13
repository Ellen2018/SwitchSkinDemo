package com.yalemang.switchskindemo.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yalemang.switchskindemo.R;
import com.yalemang.switchskindemo.skin.SkinLoadApkPath;
import com.yalemang.switchskindemo.skin.SkinManager;

import java.util.List;

public class SkinManagerAdapter extends RecyclerView.Adapter<SkinManagerAdapter.SkinManagerViewHolder> {

    private List<String> skinNameList;
    private ItemClick itemClick;

    public SkinManagerAdapter(List<String> skinNameList) {
        this.skinNameList = skinNameList;
    }

    public void setItemClick(ItemClick itemClick) {
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public SkinManagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_skin_manager, parent, false);
        return new SkinManagerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SkinManagerViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String skinName = skinNameList.get(position);
        holder.tvSkinName.setText(skinName);
        holder.itemView.setOnClickListener(v -> {
            if (itemClick != null) {
                itemClick.click(position);
            }
        });

        //设置选中
        if(skinName.equals(SkinManager.getInstance().getCurrentSkin())){
            holder.ivSelect.setVisibility(View.VISIBLE);
        } else {
            holder.ivSelect.setVisibility(View.GONE);
        }
        if(position == 0){
            holder.tvSkinName.setTextColor(Color.BLUE);
        }else if(position == 1){
            holder.tvSkinName.setTextColor(Color.RED);
        }else if(position == 2){
            holder.tvSkinName.setTextColor(Color.BLACK);
        }else if(position == 3){
            holder.tvSkinName.setTextColor(Color.GREEN);
        }else if(position == 4){
            holder.tvSkinName.setTextColor(Color.parseColor("#FFA500"));
        }
    }

    @Override
    public int getItemCount() {
        return skinNameList.size();
    }

    static class SkinManagerViewHolder extends RecyclerView.ViewHolder {

        TextView tvSkinName;
        ImageView ivSelect;

        public SkinManagerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSkinName = itemView.findViewById(R.id.tv_skin_name);
            ivSelect = itemView.findViewById(R.id.iv_select);
        }
    }

    public interface ItemClick {
        void click(int position);
    }
}
