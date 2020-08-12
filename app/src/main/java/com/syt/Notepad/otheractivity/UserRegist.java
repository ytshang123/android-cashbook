package com.syt.Notepad.otheractivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.syt.Notepad.R;
import com.syt.Notepad.activity.MainActivity;
import com.syt.Notepad.dbservice.UserService;
import com.syt.Notepad.entity.PersonEntity;

public class UserRegist extends Activity {

    private EditText regist_ID, re_password, rer_password;
    private Button regist_btn;
    private UserService service;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.useregist);
        init();
        if (!(re_password.getText().toString().equals(rer_password.getText().toString()))) {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        }
        regist_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                service = new UserService(UserRegist.this);
                if (!(regist_ID.getText().toString().equals(""))){
                    if (!(re_password.getText().toString().equals(""))){
                        PersonEntity pet = new PersonEntity(regist_ID.getText().toString(), re_password.getText().toString());
                        service.insertPerson(pet);
                        Intent intent = new Intent(UserRegist.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    private void init() {
        regist_btn = (Button) findViewById(R.id.regist_btn);
        regist_ID = (EditText) findViewById(R.id.regist_ID);
        re_password = (EditText) findViewById(R.id.re_password);
        rer_password = (EditText) findViewById(R.id.rer_password);
    }

}
