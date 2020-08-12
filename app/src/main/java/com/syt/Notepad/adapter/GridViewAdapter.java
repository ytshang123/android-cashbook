package com.syt.Notepad.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.syt.Notepad.R;
import com.syt.Notepad.entity.GridViewEntity;

import java.util.List;

//  bill
public class GridViewAdapter extends BaseAdapter{

	private List<GridViewEntity> list;
	private Context context;
    private GridViewEntity entity;
    private Integer []img;
    private String []st;
	public GridViewAdapter(List<GridViewEntity> list, Context context) {
		super();
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		if(list.size()>0)
			return list.size();
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
			convertView=LayoutInflater.from(context).inflate(R.layout.gridviewitem, null);
			hod.gridv_img=(ImageView) convertView.findViewById(R.id.gridv_img);
		    hod.gridv_text=(TextView) convertView.findViewById(R.id.gridv_text);
		    convertView.setTag(hod);
		}else {
			hod=(HodView) convertView.getTag();
		}
		entity=list.get(position);
		img=entity.getImgs();
		st=entity.getTypes();
		String path1=st[position];
		int path=img[position];
		hod.gridv_img.setImageResource(path);
		hod.gridv_text.setText(path1);
		return convertView;
	}

	private class HodView{
		TextView gridv_text;
		ImageView gridv_img;
	}

}
