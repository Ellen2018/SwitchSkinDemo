package com.yalemang.switchskindemo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yalemang.switchskindemo.adapter.SkinManagerAdapter;
import com.yalemang.switchskindemo.skin.SkinManager;

import java.util.List;

public class SkinManagerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> skinData;
    private SkinManagerAdapter skinManagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        skinData = SkinManager.getInstance().getSkinData();
        setContentView(R.layout.activity_skin_manager);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(skinManagerAdapter = new SkinManagerAdapter(skinData));
        skinManagerAdapter.setItemClick(position -> {
             //切换皮肤
            SkinManager.getInstance().switchSkin(skinData.get(position));
        });
    }
}
