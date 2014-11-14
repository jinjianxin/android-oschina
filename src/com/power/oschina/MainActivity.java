package com.power.oschina;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.RadioButton;

import com.loopj.android.http.*;
import com.power.adapter.MainPagerAdapter;
import com.power.adapter.NewsAdapter;
import com.power.view.PullToRefreshListView;


public class MainActivity extends Activity {
	
	private RadioButton m_news = null;
	private RadioButton m_question = null;
	private RadioButton m_tweet = null;
	private RadioButton m_active = null;
	private RadioButton m_more = null;
	
	private Button m_newLast = null;
	private Button m_newBlog = null;
	private Button m_newRead = null;
	private PullToRefreshListView m_newPullList = null;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        initRadioButton();
        initNewsFrame();
        
    }
    
    private void initRadioButton()
    {
    	m_news = (RadioButton) findViewById(R.id.foot_news);
    	m_question = (RadioButton) findViewById(R.id.foot_question);
    	m_tweet = (RadioButton) findViewById(R.id.foot_tweet);
    	m_active =(RadioButton) findViewById(R.id.foot_active);
    	m_more = (RadioButton) findViewById(R.id.foot_more);   	
    	
    	m_news.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			m_question.setChecked(false);
			m_tweet.setChecked(false);
			m_active.setChecked(false);
			m_more.setChecked(false);
				
			}
		});
    	
    	m_question.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
    	
    }
   
    private void initNewsFrame()
    {
    	m_newLast =(Button) findViewById(R.id.new_last);
    	m_newBlog = (Button) findViewById(R.id.new_blog);
    	m_newRead = (Button) findViewById(R.id.new_read);
    	
    	m_newPullList = (PullToRefreshListView) findViewById(R.id.new_pull_list);        
        NewsAdapter adapter = new NewsAdapter(getApplicationContext());
        m_newPullList.setAdapter(adapter);
    
    }
}
