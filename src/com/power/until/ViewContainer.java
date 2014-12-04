package com.power.until;

import android.view.View;
import android.widget.Adapter;
import android.widget.BaseAdapter;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class ViewContainer {
	private PullToRefreshListView m_pullView = null;
	private BaseAdapter m_adapter = null;
	private View m_view = null;

	public ViewContainer() {
	}

	public View getM_view() {
		return m_view;
	}

	public void setM_view(View m_view) {
		this.m_view = m_view;
	}
	
	public PullToRefreshListView getM_pullView() {
		return m_pullView;
	}

	public void setM_pullView(PullToRefreshListView m_pullView) {
		this.m_pullView = m_pullView;
	}

	public BaseAdapter getM_adapter() {
		return m_adapter;
	}

	public void setM_adapter(BaseAdapter m_adapter) {
		this.m_adapter = m_adapter;
	}

}
