package com.syt.Notepad.otheractivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.syt.Notepad.R;
import com.syt.Notepad.dbservice.UserService;
import com.syt.Notepad.entity.ListViewEntity;

import java.text.SimpleDateFormat;
import java.util.Date;


public class AddNoteBook extends Activity implements OnClickListener {

    long le;

    private String title, content;

    private ListViewEntity ent;

	private Button save_btn, undo_btn, addtime_btn, modify_btn, delete_btn;
	private EditText edit_title, edit_context;


	private String date = null;
	private UserService service;
	private String intext = null;
	private int op = 0;


	public AddNoteBook() {
	}

	public AddNoteBook(UserService service) {
		super();
		this.service = service;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addnote_layout);
		init();
        Intent intent = getIntent();
        int flag = 0;
        flag = intent.getIntExtra("flag", 0);
        if(flag == 1){
            Intent intent1=getIntent();
            ent=(ListViewEntity) intent1.getSerializableExtra("data");
            title = ent.getText_title_of_item();
            content = ent.getText_content_of_item();
            edit_title.setText(title);
            edit_context.setText(content);
        }

	}

	private void init() {
		edit_title = (EditText) findViewById(R.id.edit_title);
		edit_title.requestFocus();
		edit_context = (EditText) findViewById(R.id.edit_context);
		save_btn = (Button) findViewById(R.id.save_btn);
		undo_btn = (Button) findViewById(R.id.undo_btn);
		addtime_btn = (Button) findViewById(R.id.addtime_btn);
		addtime_btn.setOnClickListener(this);
        modify_btn = (Button) findViewById(R.id.modify_btn);
        modify_btn.setOnClickListener(this);
        delete_btn = (Button) findViewById(R.id.delete_btn);
        delete_btn.setOnClickListener(this);
		undo_btn.setOnClickListener(this);
		save_btn.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
			case R.id.save_btn:
				service = new UserService(AddNoteBook.this);
                date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				insertNote(service);
				finish();
				break;
			case R.id.undo_btn:
				if (!edit_title.getText().toString().trim().isEmpty()) {
					op = 1;
				}
				if (!edit_context.getText().toString().trim().isEmpty()) {
					op = 2;
				}
				switch (op) {
					case 1:
						String sub1=cancalInto(edit_title.getText().toString().trim());

						edit_title.setText(sub1);
						break;
					case 2:
						String sub2=cancalInto(edit_context.getText().toString().trim());
						edit_context.setText(sub2);
						break;

					default:
						break;
				}
				break;
			case R.id.addtime_btn:
				break;
            case R.id.modify_btn:
                service = new UserService(AddNoteBook.this);
                ContentValues values = new ContentValues();
                values.put("title", edit_title.getText().toString());
                values.put("content", edit_context.getText().toString());
                service.updateNote(title, values);
                finish();
                break;
            case R.id.delete_btn:
                service = new UserService(AddNoteBook.this);
                service.deleteNote(title);
                finish();
                break;
			default:
				break;
		}
	}

	//	定义方法去获得输入的内容插入数据库
	public  void insertNote(UserService service) {
		ListViewEntity ete = new ListViewEntity(edit_title.getText().toString(), edit_context.getText().toString(), date);
		le = service.insertNote(ete);
//        ete.setId((int)le);
//        Toast.makeText(this, ete.getId()+"", Toast.LENGTH_SHORT).show();
    }

	//	定义一个方法撤销editext输入
	private String cancalInto(String intext) {
		String subedtext = intext.substring(0, intext.length() - 1);
		return subedtext;
	}


    private void showModifyDialog(){
        AlertDialog.Builder modifyDialog = new AlertDialog.Builder(AddNoteBook.this);
        modifyDialog.setTitle("我是一个普通的Dialog");
        modifyDialog.setMessage("确定修改吗？");
        modifyDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        service = new UserService(AddNoteBook.this);
                        ContentValues values = new ContentValues();
                        values.put("title", edit_title.getText().toString());
                        values.put("content", edit_context.getText().toString());
                        service.updateNote(title, values);
                        finish();
                    }
                });
        modifyDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        // 显示
        modifyDialog.show();
    }

    private void showDeleteDialog(){
        AlertDialog.Builder deleteDialog = new AlertDialog.Builder(AddNoteBook.this);

        deleteDialog.setTitle("我也是一个普通的Dialog");
        deleteDialog.setMessage("确定删除吗？");
        deleteDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        service = new UserService(AddNoteBook.this);
                        service.deleteNote(title);
                        finish();
                    }
                });
        deleteDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        // 显示
        deleteDialog.show();
    }

}
