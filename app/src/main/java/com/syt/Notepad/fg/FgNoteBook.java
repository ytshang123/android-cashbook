package com.syt.Notepad.fg;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.syt.Notepad.R;
import com.syt.Notepad.adapter.ListViewAdapter;
import com.syt.Notepad.dbservice.UserService;
import com.syt.Notepad.entity.ListViewEntity;
import com.syt.Notepad.otheractivity.AddNoteBook;
import com.syt.Notepad.xlistviewutil.XListView;

import java.util.List;

public class FgNoteBook extends Fragment implements OnClickListener ,XListView.IXListViewListener {


    private ListView listview;
    private Button add_btn_of_notebook;
    private List<ListViewEntity> list;

    private ListViewAdapter adapter;
    private View view;
    private long e;

    private MyItemOnClick myItemOnClick;

    // 获得数据库note表相关数据所用变量

    private UserService service;

    // 通讯相关的变量定义
    private static final int Code_Note = 1;
    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }
    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }
    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
    }
    @Override
    public void onDestroyView() {
        // TODO Auto-generated method stub
        super.onDestroyView();
    }
    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        init();
        list=findNote();
        adapter = new ListViewAdapter(getActivity(), list);
        listview.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        view = inflater.inflate(R.layout.fgnotebook, container, false);
        return view;
    }

    private void init() {

        listview = (ListView) view.findViewById(R.id.listview);
        add_btn_of_notebook = (Button) view
                .findViewById(R.id.add_btn_of_notebook);
        add_btn_of_notebook.setOnClickListener(this);
        myItemOnClick=new MyItemOnClick();
        listview.setOnItemClickListener(myItemOnClick);


    }
    private class MyItemOnClick implements OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // TODO Auto-generated method stub

            Intent intent =new Intent(getActivity(),AddNoteBook.class);
            Bundle bundle=new Bundle();
            bundle.putSerializable("data", list.get(position));
            intent.putExtras(bundle);
            int flag = 1;
            intent.putExtra("flag", flag);
            getActivity().startActivity(intent);

        }

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.add_btn_of_notebook:
                Intent intent = new Intent(getActivity(), AddNoteBook.class);
                int flag = 0;
                intent.putExtra("flag", flag);
                getActivity().startActivity(intent);
                break;
            default:
                break;
        }
    }

    private List<ListViewEntity> findNote(){
        service=new UserService(getActivity());
        list=service.findAll();
        return list;

    }

    @Override
    public void onRefresh() {
        // TODO Auto-generated method stub

    }
    @Override
    public void onLoadMore() {
        // TODO Auto-generated method stub

    }
}
