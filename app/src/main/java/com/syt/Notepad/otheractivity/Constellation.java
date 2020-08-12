package com.syt.Notepad.otheractivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.syt.Notepad.R;
import com.syt.Notepad.entity.Info;

public class Constellation extends Activity {

    //  查询星座

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constellation);
        Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Info info = new Info();
                if("".equals(((EditText)findViewById(R.id.birthday)).getText().toString())){
                    Toast.makeText(Constellation.this, "请输入您的阳历生日，否则不能计算！", Toast.LENGTH_SHORT).show();
                    return;
                }
                String birthday = ((EditText)findViewById(R.id.birthday)).getText().toString();
                info.setBirthday(birthday);
                Bundle bundle = new Bundle();
                bundle.putSerializable("info", info);
                Intent intent = new Intent(Constellation.this, ResultActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
