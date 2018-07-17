package com.hycollege.net.reader;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.hycollege.net.reader.R;
import com.hycollege.net.reader.view.Sig_on;

import java.util.Timer;
import java.util.TimerTask;


public class WelcomeActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_welcome);

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
			timer.schedule(task, 1000 * 2); //2秒后跳转到登陆界面
	}
}
