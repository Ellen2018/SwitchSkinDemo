package com.yalemang.switchskindemo.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yalemang.switchskindemo.R;

public class FragmentThree extends Fragment {

    private Button btSkin1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three,container,false);
        btSkin1 = view.findViewById(R.id.bt_skin_1);
        btSkin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"切换皮肤1",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
