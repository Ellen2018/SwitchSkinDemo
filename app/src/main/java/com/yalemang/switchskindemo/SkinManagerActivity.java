package com.yalemang.switchskindemo;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yalemang.switchskindemo.adapter.SkinManagerAdapter;
import com.yalemang.switchskindemo.skin.SkinManager;

import java.util.List;

public class SkinManagerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView tvSkinColor;
    private List<String> skinData;
    private SkinManagerAdapter skinManagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        skinData = SkinManager.getInstance().getSkinData();
        setContentView(R.layout.activity_skin_manager);
        recyclerView = findViewById(R.id.recycler_view);
        tvSkinColor = findViewById(R.id.tv_skin_color);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(skinManagerAdapter = new SkinManagerAdapter(skinData));
        tvSkinColor.setBackgroundColor(skinManagerAdapter.getSelectColor());
        skinManagerAdapter.setItemClick(position -> {
             //切换皮肤
            SkinManager.getInstance().switchSkin(skinData.get(position));
            if(SkinManager.getInstance().getCurrentSkin().equals("skin_red.apk")){
                //红色
                skinManagerAdapter.setSelectColor(Color.RED);
            }else if(SkinManager.getInstance().getCurrentSkin().equals("skin_blue.apk")){
                skinManagerAdapter.setSelectColor(Color.BLUE);
            }else if(SkinManager.getInstance().getCurrentSkin().equals("skin_green.apk")){
                skinManagerAdapter.setSelectColor(Color.GREEN);
            }else if(SkinManager.getInstance().getCurrentSkin().equals("skin_black.apk")){
                skinManagerAdapter.setSelectColor(Color.BLACK);
            }else {
                //默认
                skinManagerAdapter.setSelectColor(Color.parseColor("#FFA500"));
            }
            skinManagerAdapter.notifyDataSetChanged();
            tvSkinColor.setBackgroundColor(skinManagerAdapter.getSelectColor());
        });
    }
}
