package com.example.welcome;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Sig_on extends Activity {

    EditText user;
    EditText pwd;
    Button log;
    Button btn;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.sigon);

        user = (EditText) findViewById(R.id.username);
        pwd = (EditText) findViewById(R.id.password);

        log = (Button) findViewById(R.id.login);
        btn = (Button) findViewById(R.id.btn);
        sp= getSharedPreferences("userdata", Context.MODE_PRIVATE);
        sp.edit().putBoolean("isguide",true).commit();
        log.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String User1 = sp.getString("phone", "");
                String Pwd1 = sp.getString("password", "");

                String User = user.getText().toString();
                String Pwd = pwd.getText().toString();


                if (User.equals("") && Pwd.equals("")) {
                    Toast.makeText(Sig_on.this, "用户名和密码不能为空！", Toast.LENGTH_LONG).show();
                    return;
                }


                if (User.equals("")) {
                    Toast.makeText(Sig_on.this, "请输入用户名", Toast.LENGTH_LONG).show();
                    return;
                }
                if (Pwd.equals("")) {
                    Toast.makeText(Sig_on.this, "请输入密码", Toast.LENGTH_LONG).show();
                    return;
                }


                if (User.equals(User1) && Pwd.equals(Pwd1)) {
                    Toast.makeText(Sig_on.this, "登录成功", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Sig_on.this,MainActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(Sig_on.this, "用户名和密码不存在！", Toast.LENGTH_LONG).show();
                }
            }
        });

        //注册界面
        btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent aa = new Intent(Sig_on.this, ZhuCeActivity.class);
                startActivity(aa);
                return;
            }
        });


    }


}
