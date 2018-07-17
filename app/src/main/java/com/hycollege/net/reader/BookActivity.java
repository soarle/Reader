package com.hycollege.net.reader;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hycollege.net.reader.book.ContextActivity;

public class BookActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_book);
    }
    public void Bookimgclick(View view){
         switch(view.getId()){
             case R.id.img_1:
                 Toast.makeText(getApplicationContext(),"Android疯狂讲义",Toast.LENGTH_SHORT).show();
                 break;
             case R.id.img_2:
                 Toast.makeText(getApplicationContext(),"Java程序员上班那点事儿",Toast.LENGTH_SHORT).show();
                 break;
             case R.id.img_3:
                 Toast.makeText(getApplicationContext(),"Java编程思维",Toast.LENGTH_SHORT).show();
                 break;
             case R.id.img_4:
                 Intent intent=new Intent(BookActivity.this, ContextActivity.class);
                 startActivity(intent);
                 break;
         }
    }
}
