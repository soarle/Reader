package com.example.welcome;

import java.util.Timer;
import java.util.TimerTask;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;


public class WelcomeActivity extends Activity {
	SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_welcome);
		sp=getSharedPreferences("userdata",MODE_PRIVATE);
		boolean userguide=sp.getBoolean("isguide",false);
		if(!userguide){
			//设置定时器
			final Intent it = new Intent(this,Sig_on.class);
			Timer timer = new Timer();
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					startActivity(it); //执行
					finish();
				}
			};
			timer.schedule(task, 1000 * 3); //3秒后跳转到登陆界面
		}else{
			startActivity(new Intent(this,Sig_on.class));
			finish();
		}
	}
}
