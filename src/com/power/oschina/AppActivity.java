package com.power.oschina;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.Header;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.SyncStateContract.Helpers;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.power.until.BtHelper;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.State;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.power.adapter.NewsAdapter;
import com.power.data.NewsData;
import com.power.until.ViewContainer;

public class AppActivity extends Activity {

	// http://www.oschina.net/action/api/news_list?catalog=1&pageIndex=0&pageSize=20
	// http://www.oschina.net/action/api/blog_list?type=latest&pageIndex=0&pageSize=20
	// http://www.oschina.net/action/api/blog_list?type=recommend&pageIndex=0&pageSize=20

	private String tag = "oschina";

	private ViewPager m_viewPager;
	private ArrayList<ViewContainer> m_viewList;

	private int m_currentPage = 0;
	private int m_catalog = 0;

	// 第一个页面
	private Button m_newsButton = null;
	private Button m_blogButton = null;
	private Button m_recommButton = null;
	private int m_firstType = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.app_layout);

		m_viewList = new ArrayList<ViewContainer>();

		initNews();
		/*
		 * initQuestion(); initTweet(); initOwn();
		 */

		m_viewPager = (ViewPager) findViewById(R.id.vp_list);
		m_viewPager.setAdapter(new ListViewPagerAdapter());
		m_viewPager.setCurrentItem(0);
		m_viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				m_currentPage = arg0;
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		ViewContainer viewContainer = m_viewList.get(0);
		PullToRefreshListView listView = viewContainer.getM_pullView();
		listView.onRefreshComplete();
		listView.setRefreshing();

	}

	private void initNews() {
		View view = LayoutInflater.from(getApplicationContext()).inflate(
				R.layout.news_layout, null, false);

		ViewContainer container = new ViewContainer();

		PullToRefreshListView listView = (PullToRefreshListView) view
				.findViewById(R.id.pullView);
		NewsAdapter adapter = new NewsAdapter(getApplicationContext());

		container.setM_pullView(listView);
		listView.setMode(Mode.BOTH);
		listView.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub

				PullToRefreshBase<?> mRefreshedView = refreshView;
				// new GetDataTask(refreshView).execute();
				ViewContainer viewContainer = m_viewList.get(m_currentPage);

				int size = viewContainer.getM_adapter().getCount() / 20;
				String url = null;
				if (m_firstType == 0) {
					url = BtHelper.g_newUrl + "?catalog=1&pageIndex=" + size
							+ "&pageSize=20";
				} else if (m_firstType == 1) {
					
					// http://www.oschina.net/action/api/blog_list?type=latest&pageIndex=0&pageSize=20
					url = BtHelper.g_blogUrl + "?type=latest&pageIndex="+size+"&pageSize=20";
					
				} else if (m_firstType == 2) {
					url = BtHelper.g_newUrl + "?catalog=1&pageIndex=" + size
							+ "&pageSize=20";
				}

				BtHelper.logOutPut(url);
				AsyncHttpClient client = new AsyncHttpClient();
				client.get(url, new AsyncHttpResponseHandler() {

					@Override
					public void onFailure(int arg0, Header[] arg1, byte[] arg2,
							Throwable arg3) {
						// TODO Auto-generated method stub
						BtHelper.logOutPut("onFailure");
					}

					@Override
					public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
						// TODO Auto-generated method stub

						String xmlStr = new String(arg2);

						if(m_firstType ==0)
						{
						
						List<NewsData> list = BtHelper.newsXmlParser(xmlStr);

						ViewContainer viewContainer = m_viewList
								.get(m_currentPage);
						viewContainer.getM_pullView().onRefreshComplete();
						NewsAdapter adapter = (NewsAdapter) viewContainer
								.getM_adapter();
						adapter.add(list);
						adapter.notifyDataSetChanged();
						}
						else if(m_firstType ==1)
						{
							BtHelper.blogXmlParser(xmlStr);
						}
					}
				});
			}

		});
		container.setM_adapter(adapter);
		container.setM_view(view);

		m_viewList.add(container);

		m_newsButton = (Button)view.findViewById(R.id.new_last);
		m_blogButton = (Button) view.findViewById(R.id.new_blog);
		m_recommButton = (Button)view.findViewById(R.id.new_read);

		View.OnClickListener listener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (v == m_newsButton) {
					m_firstType = 0;
				} else if (v == m_blogButton) {
					m_firstType = 1;
				} else if (v == m_recommButton) {
					m_firstType = 2;
				}

				ViewContainer viewContainer = m_viewList.get(0);
				
				
				NewsAdapter adapter = (NewsAdapter) viewContainer.getM_adapter();
				adapter.removeAll();
				adapter.notifyDataSetChanged();
				
				viewContainer.getM_pullView().setRefreshing();
				
			}
		};

		m_newsButton.setOnClickListener(listener);
		m_blogButton.setOnClickListener(listener);
		m_recommButton.setOnClickListener(listener);

	}

	private void initQuestion() {
		View view = LayoutInflater.from(getApplicationContext()).inflate(
				R.layout.news_layout, null, false);

		ViewContainer container = new ViewContainer();

		PullToRefreshListView listView = (PullToRefreshListView) view
				.findViewById(R.id.pullView);
		NewsAdapter adapter = new NewsAdapter(getApplicationContext());

		container.setM_pullView(listView);

		listView.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub

			}

		});

		container.setM_adapter(adapter);
		container.setM_view(view);

		m_viewList.add(container);
	}

	private void initTweet() {
		View view = LayoutInflater.from(getApplicationContext()).inflate(
				R.layout.news_layout, null, false);

		ViewContainer container = new ViewContainer();

		PullToRefreshListView listView = (PullToRefreshListView) view
				.findViewById(R.id.pullView);
		NewsAdapter adapter = new NewsAdapter(getApplicationContext());

		container.setM_pullView(listView);

		listView.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub

			}

		});

		container.setM_adapter(adapter);
		container.setM_view(view);

		listView.setAdapter(adapter);

		m_viewList.add(container);
	}

	private void initOwn() {
		View view = LayoutInflater.from(getApplicationContext()).inflate(
				R.layout.news_layout, null, false);

		ViewContainer container = new ViewContainer();

		PullToRefreshListView listView = (PullToRefreshListView) view
				.findViewById(R.id.pullView);
		NewsAdapter adapter = new NewsAdapter(getApplicationContext());

		container.setM_pullView(listView);

		listView.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub

			}

		});

		container.setM_adapter(adapter);
		container.setM_view(view);

		m_viewList.add(container);
	}

	private class ListViewPagerAdapter extends PagerAdapter {

		@Override
		public View instantiateItem(ViewGroup container, int position) {
			Context context = container.getContext();

			ViewContainer viewContainer = m_viewList.get(position);

			View view = viewContainer.getM_view();
			container.addView(view, LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT);

			viewContainer.getM_pullView().setAdapter(
					viewContainer.getM_adapter());

			return view;

		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public int getCount() {
			return m_viewList.size();
		}

	}

}
