/*
 * @Title:  NewsAdapter.java
 * @Copyright:  XXX Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * @Description:  TODO<>
 * @author:  jjx
 * @data:  2014��11��13�� ����3:59:53
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
 * TODO<������������Ǹ�ʲô��>
 * @author  jjx
 * @data:  2014��11��13�� ����3:59:53
 * @version:  V1.0
 */
public class NewsAdapter  extends BaseAdapter
{
	private Context m_context = null;
	
	/** 
	 * <Ĭ�Ϲ��캯��>
	 */
	public NewsAdapter(Context context) {
		// TODO Auto-generated constructor stub
		this.m_context = context;
	}

	/**
	 * ���ط���
	 * @return
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 30;
	}

	/**
	 * ���ط���
	 * @param position
	 * @return
	 */
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * ���ط���
	 * @param position
	 * @return
	 */
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * ���ط���
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
