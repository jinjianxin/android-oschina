/*
 * @Title:  NewsAdapter.java
 * @Copyright:  XXX Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * @Description:  TODO<>
 * @author:  jjx
 * @data:  2014��11��13�� ����3:59:53
 * @version:  V1.0
 */
package com.power.adapter;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Text;

import com.power.data.NewsData;
import com.power.oschina.R;
import com.power.until.BtHelper;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class NewsAdapter extends BaseAdapter {
	private List<NewsData> m_list;
	private Context m_context = null;

	public NewsAdapter(Context context) {
		// TODO Auto-generated constructor stub
		this.m_context = context;

		m_list = new ArrayList<NewsData>();
	}

	public void add(List<NewsData> list) {
		m_list.addAll(list);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return m_list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
	
		if (convertView == null) {

			LayoutInflater inflater = (LayoutInflater) m_context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			View view = inflater.inflate(R.layout.new_adapter_layout, null);
			
			NewsData data = m_list.get(position);
			
			TextView title = (TextView) view.findViewById(R.id.title);
			title.setText(data.getM__title());
			
			TextView author = (TextView) view.findViewById(R.id.author); 
			author.setText(data.getM_author());
			
			TextView commentCount = (TextView) view.findViewById(R.id.count);
			commentCount.setText(data.getM_commentCount());

			return view;
		} else {
		
			NewsData data = m_list.get(position);
			
			TextView textView = (TextView) convertView.findViewById(R.id.title);
			textView.setText(data.getM__title());
			
			TextView author = (TextView) convertView.findViewById(R.id.author); 
			author.setText(data.getM_author());

			TextView commentCount = (TextView) convertView.findViewById(R.id.count);
			commentCount.setText(data.getM_commentCount());
			
			return convertView;
		}
	}

	public void removeAll() {
		// TODO Auto-generated method stub
		m_list.removeAll(m_list);
	}

}
