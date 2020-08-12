package com.syt.Notepad.fg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syt.Notepad.R;
import com.syt.Notepad.otheractivity.Constellation;
import com.syt.Notepad.otheractivity.Wuziqi;


public class FgPerson extends Fragment implements View.OnClickListener{

    private View view;
    private TextView relax, xingzuo;


    @Override
    public void onStart() {
        super.onStart();
        init();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fgperson, container, false);
        return view;
    }

    private void init(){
        relax = (TextView) view.findViewById(R.id.relax);
        relax.setOnClickListener(this);
        xingzuo = (TextView) view.findViewById(R.id.xingzuo);
        xingzuo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.relax:
                Intent intent = new Intent(getActivity(), Wuziqi.class);
                startActivity(intent);
                break;
            case R.id.xingzuo:
                Intent intent1 = new Intent(getActivity(), Constellation.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }
}
