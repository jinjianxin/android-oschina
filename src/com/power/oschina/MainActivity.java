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
import android.widget.RadioButton;

import com.loopj.android.http.*;
import com.power.adapter.MainPagerAdapter;


public class MainActivity extends Activity {
	
	private RadioButton m_news = null;
	private RadioButton m_question = null;
	private RadioButton m_tweet = null;
	private RadioButton m_active = null;
	private RadioButton m_more = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        initRadioButton();
        
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
   
}
