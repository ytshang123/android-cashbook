package com.syt.Notepad.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.syt.Notepad.R;
import com.syt.Notepad.database.DBOpenHelper;
import com.syt.Notepad.otheractivity.UserRegist;
import com.syt.Notepad.user.UserInterface;


public class MainActivity extends Activity implements OnClickListener{

	private Button directlogin_btn, login_btn;
    DBOpenHelper dbOpenHelper;
	
	private TextView nonum_text;
	private EditText name_text,password_text;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.directlogin_btn:
			Intent intent=new Intent(MainActivity.this, UserInterface.class);
			startActivity(intent);
			break;
		case R.id.nonum_text:
			Intent intents=new Intent(MainActivity.this, UserRegist.class);
			startActivity(intents);
			break;
        case R.id.login_btn:
            Intent intent1=new Intent(MainActivity.this, UserInterface.class);
            startActivity(intent1);
//            SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
//            Cursor cursor = db.query("person", null, null, null, null, null, null);
//            if (cursor.moveToFirst()) {
//                do {
//                    String name = cursor.getString(cursor.getColumnIndex("username"));
//                    if (name.equals(name_text.getText().toString())) {
//                        String password = cursor.getString(cursor.getColumnIndex("password"));
//                        if (password.equals(password_text.getText().toString())) {
//                            Intent intent1 = new Intent(MainActivity.this, UserInterface.class);
//                            startActivity(intent1);
//                        }
//                    }
//                } while (cursor.moveToNext());
//            }
//            cursor.close();
            break;
		default:
			break;
		}
	}

	private void init(){
		directlogin_btn=(Button)findViewById(R.id.directlogin_btn);
		directlogin_btn.setOnClickListener(this);
        login_btn = (Button) findViewById(R.id.login_btn);
        login_btn.setOnClickListener(this);
		nonum_text=(TextView) findViewById(R.id.nonum_text);
		nonum_text.setOnClickListener(this);
		name_text = (EditText) findViewById(R.id.name_text);
		password_text = (EditText) findViewById(R.id.edit_context);
	}
	

}
