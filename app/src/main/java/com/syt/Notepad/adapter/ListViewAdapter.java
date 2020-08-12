package com.syt.Notepad.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.syt.Notepad.R;
import com.syt.Notepad.entity.ListViewEntity;

import java.util.List;


public class ListViewAdapter extends BaseAdapter {

    private Context context;
    private List<ListViewEntity> list;
    private String dbtime;
    private String dbtime2 = "";

    public ListViewAdapter(Context context, List<ListViewEntity> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list != null) {
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
        if (convertView == null) {
            hod = new HodView();
            convertView = LayoutInflater.from(context).inflate(R.layout.listviewitem, null);
            hod.text_title_of_item = (TextView) convertView.findViewById(R.id.text_title_of_item);
            hod.text_content_of_item = (TextView) convertView.findViewById(R.id.text_content_of_item);
            hod.text_time_of_item = (TextView) convertView.findViewById(R.id.text_time_of_item);
            convertView.setTag(hod);
        } else {
            hod = (HodView) convertView.getTag();
        }
        if (list.get(position).getDtimes() == null) {
            dbtime = "";
        } else {
            dbtime = list.get(position).getDtimes();
        }
        hod.text_title_of_item.setText(list.get(position).getText_title_of_item());
        hod.text_content_of_item.setText(list.get(position).getText_content_of_item());
        hod.text_time_of_item.setText(dbtime + " ");
        return convertView;
    }

    private class HodView {
        TextView text_content_of_item, text_title_of_item, text_time_of_item;
    }
}
