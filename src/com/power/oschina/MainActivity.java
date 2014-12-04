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
        

        
    }
    

    
   

}
