package com.hycollege.net.reader.listview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.hycollege.net.reader.R;

public class FindActivity extends Activity {

	private List<Find> findList = new ArrayList<>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_find);

		initFind();///初始化数据

		//适配器
		FindAdapter adapter = new  FindAdapter(FindActivity.this, R.layout.find_item, findList);
		
		ListView listView = (ListView) findViewById(R.id.list_view);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Find find = findList.get(position);

				Toast.makeText(FindActivity.this, "点击的是："+find.getName(), Toast.LENGTH_SHORT).show();
			}
			
		});
	}

	private void initFind() {
		for(int i = 0; i < 3; i++){
			Find p1 = new Find("榜单的书", R.drawable.book1);
			findList.add(p1);
			Find p2 = new Find("榜单的书", R.drawable.book2);
			findList.add(p2);
			Find p3 = new Find("榜单的书", R.drawable.book3);
			findList.add(p3);
			Find p4 = new Find("榜单的书", R.drawable.book4);
			findList.add(p4);
			Find p5 = new Find("榜单的书", R.drawable.book5);
			findList.add(p5);
			Find p6 = new Find("榜单的书", R.drawable.book6);
			findList.add(p6);
		}
	}
	
}
