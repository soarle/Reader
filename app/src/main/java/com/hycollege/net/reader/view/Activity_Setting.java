package com.hycollege.net.reader.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.hycollege.net.reader.R;

public class Activity_Setting extends Activity {
    CheckBox checkbox1, checkbox2, checkbox3;
    Button btnreturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity__setting);
        checkbox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkbox1.setOnCheckedChangeListener(checklistener);
        checkbox2 = (CheckBox) findViewById(R.id.checkBox2);
        checkbox2.setOnCheckedChangeListener(checklistener);
        checkbox3 = (CheckBox) findViewById(R.id.checkBox3);
        checkbox3.setOnCheckedChangeListener(checklistener);
        btnreturn=(Button)findViewById(R.id.btnreturn);
        btnreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    finish();
            }
        });
    }
    private CompoundButton.OnCheckedChangeListener checklistener=new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonview, boolean ischeck) {
             switch(buttonview.getId()){
                 case R.id.checkBox1:
                     if(ischeck){
                          checkbox1.setEnabled(true);
                          checkbox2.setEnabled(false);
                          checkbox3.setEnabled(false);
                     }else{
                         checkbox1.setEnabled(true);
                         checkbox2.setEnabled(true);
                         checkbox3.setEnabled(true);
                     }
                     break;
                 case R.id.checkBox2:
                     if(ischeck){
                         checkbox1.setEnabled(false);
                         checkbox2.setEnabled(true);
                         checkbox3.setEnabled(false);
                     }else{
                         checkbox1.setEnabled(true);
                         checkbox2.setEnabled(true);
                         checkbox3.setEnabled(true);
                     }
                     break;
                 case R.id.checkBox3:
                     if(ischeck){
                         checkbox1.setEnabled(false);
                         checkbox2.setEnabled(false);
                         checkbox3.setEnabled(true);
                     }else{
                         checkbox1.setEnabled(true);
                         checkbox2.setEnabled(true);
                         checkbox3.setEnabled(true);
                     }
                     break;
             }
        }
    };
}
