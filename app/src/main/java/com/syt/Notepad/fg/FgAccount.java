package com.syt.Notepad.fg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syt.Notepad.R;
import com.syt.Notepad.dbservice.UserService;
import com.syt.Notepad.entity.GridViewEntity;
import com.syt.Notepad.otheractivity.RollOutOrIn;

import java.util.ArrayList;
import java.util.List;

public class FgAccount extends Fragment implements OnClickListener {

    //转账  总资产  现金  储蓄  信用卡  支付宝
    private TextView transfer, zongzican_text, xianjin_text, chuxu_text, xinyongka_text, web_text;
    private View view;
    private Intent intent;
    private UserService service;
    private List<GridViewEntity> dblist;

    @Override
    public void onStart() {
        super.onStart();
        init();
        findAccount();
        Double xian = Double.valueOf(xianjin_text.getText().toString().trim());
        Double chu = Double.valueOf(chuxu_text.getText().toString().trim());
        Double xin = Double.valueOf(xinyongka_text.getText().toString().trim());
        Double web = Double.valueOf(web_text.getText().toString().trim());
        String zong = Double.toString(xian + chu + xin + web);
        zongzican_text.setText(zong);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fgaccount, container, false);
        return view;
    }


    private void init() {
        transfer = (TextView) view.findViewById(R.id.transfer);
        transfer.setOnClickListener(this);
        web_text = (TextView) view.findViewById(R.id.web_text);
        xinyongka_text = (TextView) view.findViewById(R.id.xinyongka_text);
        chuxu_text = (TextView) view.findViewById(R.id.chuxu_text);
        xianjin_text = (TextView) view.findViewById(R.id.xianjin_text);
        zongzican_text = (TextView) view.findViewById(R.id.zongzican_text);

    }

    private void findAccount() {
        service = new UserService(getActivity());
        List<TextView> textes = new ArrayList<TextView>();
        textes.add(xianjin_text);
        textes.add(chuxu_text);
        textes.add(xinyongka_text);
        textes.add(web_text);
        dblist = service.findAllAccount();
        for (int i = 0; i < textes.size(); i++) {
            textes.get(i).setText(dblist.get(i).getBill().toString().trim());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //  点击转账按钮跳到转账页面
            case R.id.transfer:
                intent = new Intent(getActivity(), RollOutOrIn.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
