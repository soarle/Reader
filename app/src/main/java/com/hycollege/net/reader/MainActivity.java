package com.hycollege.net.reader;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemClock;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hycollege.net.reader.listview.FindActivity;
import com.hycollege.net.reader.view.Activity_Setting;
import com.hycollege.net.reader.view.FenleiActivity;
import com.hycollege.net.reader.view.SousuoActivity;

import java.util.ArrayList;
import java.util.List;
import android.support.design.widget.NavigationView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private DrawerLayout mDrawerLayout;//侧边栏
    NavigationView navView;
    ImageView titleleft,titleright;
    Button btnleft,btncenter,btnright;
    ViewPager imgviewpager;
    LinearLayout pointlayout;
    private List<ImageView> imglist;
    private ExitReceiver exitReceiver = new ExitReceiver();

    //图片
    private int[] bannerimgs={R.drawable.banner1,R.drawable.banner2,R.drawable.banner3,R.drawable.banner4};
    //线程标志
    private boolean isthread=false;
    //设置适配器和监听器
    private BannerAdapter banneradapter;
    private BannerListener bannerlistener;
    //定义圆点标志
    private int pointIndex=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initEvent();
        //用线程2秒更新图片
        new Thread(new Runnable() {
            @Override
            public void run() {
                 while(!isthread){
                     SystemClock.sleep(2000);
                     runOnUiThread(new Runnable() {
                         @Override
                         public void run() {
                             //设置当前图片
                             imgviewpager.setCurrentItem(imgviewpager.getCurrentItem()+1);
                         }
                     });
                 }
            }
        }).start();
        //注册广播，退出时发送广播，finish退出
        IntentFilter filter = new IntentFilter();
        filter.addAction("action.exit");
        registerReceiver(exitReceiver, filter);
    }
    //初始化view
    private void initView(){
         titleleft=(ImageView)findViewById(R.id.titleleft);
         titleright=(ImageView)findViewById(R.id.titleright);
         btnleft=(Button)findViewById(R.id.btnleft);
         btncenter=(Button)findViewById(R.id.btncenter);
         btnright=(Button)findViewById(R.id.btnright);
         imgviewpager=(ViewPager)findViewById(R.id.viewpager);
         pointlayout=(LinearLayout)findViewById(R.id.points);
         mDrawerLayout =(DrawerLayout)findViewById(R.id.drawer_layout);
         navView= (NavigationView) findViewById(R.id.nav_view);
         navView.setCheckedItem(R.id.nav_8);
         navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.nav_1:
                        Toast.makeText(getApplicationContext(),"已购和上传",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_2:
                        Toast.makeText(getApplicationContext(),"钱包和福利",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_3:
                        Toast.makeText(getApplicationContext(),"购物车",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_4:
                        Toast.makeText(getApplicationContext(),"想读",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_5:
                        Toast.makeText(getApplicationContext(),"书单",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_6:
                        Toast.makeText(getApplicationContext(),"笔记",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_7:
                        Toast.makeText(getApplicationContext(),"阅历",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_8:
                        mDrawerLayout.closeDrawers();
                        Intent intent = new Intent(MainActivity.this,Activity_Setting.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
    }
    //初始化数据
    private void initData(){
        imglist=new ArrayList<ImageView>();
        View view;
        LinearLayout.LayoutParams params;
        for(int i=0;i<bannerimgs.length;i++){
            //设置图片
            ImageView imgView=new ImageView(MainActivity.this);
            imgView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            imgView.setBackgroundResource(bannerimgs[i]);
            imglist.add(imgView);
            //圆点
            view=new View(MainActivity.this);
            params=new LinearLayout.LayoutParams(10,10);
            params.leftMargin=10;
            view.setBackgroundResource(R.drawable.pointbackground);
            view.setLayoutParams(params);
            view.setEnabled(false);
            pointlayout.addView(view);
        }
        banneradapter=new BannerAdapter(imglist);
        imgviewpager.setAdapter(banneradapter);
    }
    //初始化事件
    private void initEvent(){
        //注册头标栏事件
        titleleft.setOnClickListener(titleBarlistener);
        titleright.setOnClickListener(titleBarlistener);
        btnleft.setOnClickListener(titleBarlistener);
        btncenter.setOnClickListener(titleBarlistener);
        btnright.setOnClickListener(titleBarlistener);
        //注册图片滚动事件
         bannerlistener=new BannerListener();
         imgviewpager.addOnPageChangeListener(bannerlistener);
         //计算中间数作为起始位置
         int index=(Integer.MAX_VALUE/2)-(Integer.MAX_VALUE/2%imglist.size());
         imgviewpager.setCurrentItem(index);
         pointlayout.getChildAt(pointIndex).setEnabled(true);
    }
    class BannerListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
                int newposition=position%bannerimgs.length;
                 pointlayout.getChildAt(newposition).setEnabled(true);
                 pointlayout.getChildAt(pointIndex).setEnabled(false);
                 // 更新圆点标志
                 pointIndex=newposition;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
    //头标栏事件
    private View.OnClickListener titleBarlistener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=null;
            switch(view.getId()){
                //调用openDrawer()方法传入Gravity参数将滑动菜单展示出来
                case R.id.titleleft:
                    mDrawerLayout.openDrawer(GravityCompat.START);
                    break;
                //搜索栏
                case R.id.titleright:
                    intent=new Intent(MainActivity.this, SousuoActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btncenter:
                    intent=new Intent(MainActivity.this,FindActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btnright:
                    intent=new Intent(MainActivity.this, BookActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
    //icon点击事件
    public void Iconimgclick(View view){
           Intent intent=null;
            switch(view.getId()){
                case R.id.icon_fenlei:
                    intent=new Intent(MainActivity.this, FenleiActivity.class);
                    startActivity(intent);
                    break;
                case R.id.icon_zhuanti:
                    intent=new Intent(MainActivity.this, FenleiActivity.class);
                    startActivity(intent);
                    break;
                case R.id.icon_show:
                    intent=new Intent(MainActivity.this, FenleiActivity.class);
                    startActivity(intent);
                    break;
                case R.id.icon_shudan:
                    intent=new Intent(MainActivity.this, FenleiActivity.class);
                    startActivity(intent);
                    break;
            }
    }
    //广播事件
    class ExitReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
           MainActivity.this.finish();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(exitReceiver);
        //关闭定时器
        isthread=true;
        imgviewpager.removeOnPageChangeListener(bannerlistener);

    }
}
