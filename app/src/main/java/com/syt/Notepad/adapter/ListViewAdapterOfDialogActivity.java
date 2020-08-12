package com.syt.Notepad.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.syt.Notepad.R;
import com.syt.Notepad.entity.GridViewEntity;

import java.util.List;

public class ListViewAdapterOfDialogActivity extends BaseAdapter{

	private Context context;
	private List<GridViewEntity> list;
	
	public ListViewAdapterOfDialogActivity(Context context, List<GridViewEntity> list) {
		super();
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		if(list!=null){
			return list.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		HodView hod;
		if(convertView==null){
			hod=new HodView();
		    convertView=LayoutInflater.from(context).inflate(R.layout.listviewitem_dialogactivity, null);
		    hod.text_at_dialoglistview=(TextView) convertView.findViewById(R.id.text_at_dialoglistview);
		    convertView.setTag(hod);
		}else {
			hod=(HodView) convertView.getTag();
		}
		//  实现点击现金按钮弹出选择对话框
		hod.text_at_dialoglistview.setText(list.get(position).getAccountName());
		return convertView;
	}

	private class HodView{
		TextView text_at_dialoglistview;
	}
}
