package com.syt.Notepad.fg;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.syt.Notepad.R;
import com.syt.Notepad.adapter.ListViewAdapterOfBill;
import com.syt.Notepad.dbservice.UserService;
import com.syt.Notepad.entity.GridViewEntity;
import com.syt.Notepad.otheractivity.AddBill;

import java.util.ArrayList;
import java.util.List;

public class FgBill extends Fragment implements OnClickListener {
	private Button add_btn_of_bill;
	private View view;

//	adapter相关的变量
	private ListView billlistview;
	private GridViewEntity ent;
	private ListViewAdapterOfBill adapter;
	private List<GridViewEntity> list;

//	获得数据库bill数据相关变量
	private UserService service;
	private Integer[] img1 = { R.drawable.type_big_1, R.drawable.type_big_2, R.drawable.type_big_3,
			R.drawable.type_big_4, R.drawable.type_big_5, R.drawable.type_big_6, R.drawable.type_big_7,
			R.drawable.type_big_8, R.drawable.type_big_9, R.drawable.type_big_10, R.drawable.type_big_11,
			R.drawable.type_big_12 };
	private Integer[] img2 = { R.drawable.type_big_13, R.drawable.type_big_14, R.drawable.type_big_15,
			R.drawable.type_big_16, R.drawable.type_big_17, R.drawable.type_big_18, R.drawable.type_big_19 };

	private List<GridViewEntity> dblist;

	@Override
	public void onStart() {
		super.onStart();
		init();
		initView();

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fgbill, container, false);
		return view;
	}

	private void init() {
		add_btn_of_bill = (Button) view.findViewById(R.id.add_btn_of_bill);
		add_btn_of_bill.setOnClickListener(this);
		billlistview = (ListView) view.findViewById(R.id.billlistview);
	}

	private void initView() {
		service = new UserService(this.getActivity());
		dblist=new ArrayList<GridViewEntity>() ;
		dblist = service.findAllBill();
		for (GridViewEntity dbent : dblist) {
			int state=Integer.parseInt(dbent.getBillState().toString().trim());
			if(state==0){
				dbent.setImg(img1[dbent.getImg()]);
			}else if(state==1){
				dbent.setImg(img2[dbent.getImg()]);
			}
		}
		adapter = new ListViewAdapterOfBill(getActivity(), dblist);
		billlistview.setAdapter(adapter);
        service = new UserService(this.getActivity());
		billlistview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final GridViewEntity gve = dblist.get(position);
                final AlertDialog.Builder normalDialog =
                        new AlertDialog.Builder(getActivity());
                normalDialog.setMessage("确定删除吗?");
                normalDialog.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                service.deleteBill(gve.getBillType());
                                adapter.notifyDataSetChanged();
                                Toast.makeText(getActivity(), "删除成功！", Toast.LENGTH_SHORT).show();
                            }
                        });
                normalDialog.setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //...To-do
                            }
                        });
                // 显示
                normalDialog.show();
            }
		});
	}  

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add_btn_of_bill:
			Intent intent = new Intent(getActivity(), AddBill.class);
			getActivity().startActivity(intent);
			break;
		default:
			break;
		}
	}

}
