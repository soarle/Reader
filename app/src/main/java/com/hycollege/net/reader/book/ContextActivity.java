package com.hycollege.net.reader.book;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.hycollege.net.reader.R;

public class ContextActivity extends Activity {
     Button btnmore;
     TextView tvcontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_context);
        btnmore=(Button)findViewById(R.id.btnmore);
        tvcontext=(TextView)findViewById(R.id.tvcontext);
        btnmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 tvcontext.setMaxLines(15);
            }
        });
    }
}
