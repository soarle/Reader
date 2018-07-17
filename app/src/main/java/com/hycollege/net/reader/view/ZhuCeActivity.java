package com.hycollege.net.reader.view;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hycollege.net.reader.R;


public class ZhuCeActivity extends Activity{
    private EditText edtnum,edtpassword,edtsureps;
    private Button btnzhuce,btndenglu;
    private CheckBox readpsw,writepsw,buttonbox;
    private LinearLayout buttonlayout;
	protected String number;
	protected String password;
	private SharedPreferences sp=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_zhuce);
		
        //获取控件
		View v=(LinearLayout)findViewById(R.id.context);
		v.getBackground().setAlpha(230);
		buttonlayout=(LinearLayout)findViewById(R.id.buttonlayout);	
		edtnum=(EditText)findViewById(R.id.edtnum);
		edtpassword=(EditText)findViewById(R.id.edtpassword);
		edtsureps=(EditText)findViewById(R.id.edtsureps);
		
		btnzhuce=(Button)findViewById(R.id.zhuce);
		btnzhuce.setOnClickListener(doZhuCe);
		
		btndenglu=(Button)findViewById(R.id.denglu);
		btndenglu.setOnClickListener(doDengLu);
		
		readpsw=(CheckBox)findViewById(R.id.readpsw);
		readpsw.setOnCheckedChangeListener(doCheckBox);
		writepsw=(CheckBox)findViewById(R.id.writepsw);
		writepsw.setOnCheckedChangeListener(doCheckBox);
		buttonbox=(CheckBox)findViewById(R.id.button);
		buttonbox.setOnCheckedChangeListener(doCheckBox);
		
		sp=getSharedPreferences("userdata",Context.MODE_PRIVATE);
		
		if(sp.getBoolean("ispsw", true)){	
			writepsw.setChecked(true);
			edtnum.setText(sp.getString("phone", number));
			edtpassword.setText(sp.getString("password", password));
			edtsureps.setText(sp.getString("password", password));
		}
		else{
			edtnum.setText("");
			edtpassword.setText("");
			edtsureps.setText("");
		}
	}
	//注册
    private View.OnClickListener doZhuCe=new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		    number = edtnum.getText().toString().trim();
			password=edtpassword.getText().toString().trim();
			String surepsw=edtsureps.getText().toString().trim();
			if(TextUtils.isEmpty(number)||TextUtils.isEmpty(password)||TextUtils.isEmpty(surepsw)){
				Toast toast=Toast.makeText(getApplicationContext(), "用户名和密码不能为空", Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER,0,0);
				toast.show();
			}else if(surepsw.equals(password)){
				dialog();	
			}else{
				Toast toast=Toast.makeText(getApplicationContext(), "密码确认错误", Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
			}
		}
	};
	//登录
    private View.OnClickListener doDengLu=new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			Intent intent=new Intent(ZhuCeActivity.this,Sig_on.class);
			startActivity(intent);
		}
	};
	//复选框
	private CompoundButton.OnCheckedChangeListener doCheckBox=new CompoundButton.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO Auto-generated method stub
			switch(buttonView.getId()){
			      case R.id.readpsw:
			    	  if(isChecked){
			    		   edtpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
			    		   edtsureps.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
			    	  }else{
			    		  edtpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
			    		  edtsureps.setTransformationMethod(PasswordTransformationMethod.getInstance());
			    	  }
			    	  break;
			      case R.id.writepsw:
			    	  if(isChecked){
                         sp.edit().putBoolean("ispsw", true).commit();
			    	  }
			    	  else{
			    		 sp.edit().putBoolean("ispsw", false).commit();
			    	  }
			    	  break;
			      case R.id.button:
			    	  if(isChecked){
                         buttonlayout.setVisibility(View.INVISIBLE);
			    	  }
			    	  else{
			    		 buttonlayout.setVisibility(View.VISIBLE);
			    	  }
			    	  break;
			}
		}
	};
	//保存数据
	public void save(){
		Editor editor=sp.edit();
		editor.putString("phone", number); 
		editor.putString("password", password);
		editor.commit();
	}
	//对话框
	private void dialog(){
		 Builder builder=new Builder(ZhuCeActivity.this);
		 builder.setMessage("确认注册吗？");
		 builder.setTitle("提示");
		 builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();	
				Toast toast=Toast.makeText(getApplicationContext(), "注册成功！", Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				save();
				edtnum.setText("");
				edtpassword.setText("");
				edtsureps.setText("");			
			    writepsw.setChecked(false);
				finish();
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				writepsw.setChecked(true);
				finish();
			}
		});
		builder.create().show();
	}
	//图片
	public void onclickImg(View v){
		 switch(v.getId()){
		     case R.id.imgwb:
		    	 Toast.makeText(getApplicationContext(), "微博", Toast.LENGTH_SHORT).show();
		    	 break;
		     case R.id.imgqq:
		    	  Toast.makeText(getApplicationContext(), "QQ", Toast.LENGTH_SHORT).show();
		    	 break;
		     case R.id.imgwx:
		    	  Toast.makeText(getApplicationContext(), "微信", Toast.LENGTH_SHORT).show();
		    	 break;
		 }
	}
}
