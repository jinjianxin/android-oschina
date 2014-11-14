/*
 * @Title:  NewsAdapter.java
 * @Copyright:  XXX Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * @Description:  TODO<>
 * @author:  jjx
 * @data:  2014年11月13日 下午3:59:53
 * @version:  V1.0
 */
package com.power.adapter;

import com.power.oschina.R;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * TODO<请描述这个类是干什么的>
 * @author  jjx
 * @data:  2014年11月13日 下午3:59:53
 * @version:  V1.0
 */
public class NewsAdapter  extends BaseAdapter
{
	private Context m_context = null;
	
	/** 
	 * <默认构造函数>
	 */
	public NewsAdapter(Context context) {
		// TODO Auto-generated constructor stub
		this.m_context = context;
	}

	/**
	 * 重载方法
	 * @return
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 30;
	}

	/**
	 * 重载方法
	 * @param position
	 * @return
	 */
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 重载方法
	 * @param position
	 * @return
	 */
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 重载方法
	 * @param position
	 * @param convertView
	 * @param parent
	 * @return
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		LayoutInflater inflater = (LayoutInflater) m_context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		
		View view = inflater.inflate(R.layout.new_adapter_layout, null);
		
		return view;
	}

}
