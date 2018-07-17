package com.hycollege.net.reader;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by shengle on 2017/6/20.
 */

public class BannerAdapter extends PagerAdapter {
      private List<ImageView> mlist;
      public BannerAdapter(List<ImageView> list){
          this.mlist=list;
      }
    @Override
    public int getCount() {
        //取最大值实现无限循环
        return Integer.MAX_VALUE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mlist.get(position%mlist.size()));
        return mlist.get(position%mlist.size());
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mlist.get(position%mlist.size()));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
