package com.syt.Notepad.otheractivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.syt.Notepad.dbservice.UserService;
import com.syt.Notepad.entity.GridViewEntity;
import com.syt.Notepad.adapter.ListViewAdapterOfDialogActivity;
import com.syt.Notepad.R;

import java.util.List;

public class DialogActivity extends Activity{

	private ListViewAdapterOfDialogActivity adapter;
	private List<GridViewEntity> list;
	private UserService service;

//	定义DialogActivity布局相关的变量
	private ListView listview_of_diact;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialogactivity);
		getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		initView();
		init();
		listview_of_diact.setOnItemClickListener( new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String st=list.get(position).getAccountName();
				Toast.makeText(DialogActivity.this, st, Toast.LENGTH_SHORT).show();
				DialogActivity.this.getIntent().putExtra("data", st);
				DialogActivity.this.setResult(RESULT_OK,DialogActivity.this.getIntent());
				DialogActivity.this.finish();
			}
		});
		
	}
	private void initView(){
		listview_of_diact=(ListView)findViewById(R.id.listview_of_diact);
	}
//	实例化adapter
	private void init(){
		service=new UserService(DialogActivity.this);
		list=service.findAllAccount();
		adapter=new ListViewAdapterOfDialogActivity(DialogActivity.this, list);
		listview_of_diact.setAdapter(adapter);
	}
	
}
