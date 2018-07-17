package com.hycollege.net.reader.listview;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hycollege.net.reader.R;


/*
 * 自定义适配器
 * */
public class FindAdapter extends ArrayAdapter<Find> {
	
	private int resourceId;
	
	public FindAdapter(Context context, int textViewResurseceId, List<Find> Objects){
		super(context, textViewResurseceId, Objects);
		resourceId = textViewResurseceId;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Find find = getItem(position);//获取当前实例
		View view;
		ViewHolder viewHolder;
		if(convertView == null){
			view = LayoutInflater.from(getContext()).inflate(resourceId, parent,false);
			viewHolder = new ViewHolder();
			viewHolder.findImage = (ImageView) view.findViewById(R.id.find_image);
			viewHolder.findNmae = (TextView) view.findViewById(R.id.find_name);
			view.setTag(viewHolder);//把ViewHolder存储在View
			
		}else{
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();//重新获取
			
		}
		
		viewHolder.findImage.setImageResource(find.getImageId());
		viewHolder.findNmae.setText(find.getName());
		
		return view;		
		
	}
	
	public final class ViewHolder{
		ImageView findImage;
		TextView findNmae;
	}


}
