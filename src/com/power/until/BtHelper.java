/**  
 * @Title: Log.java
 * @Package com.sp.bt.util
 * @Description: TODO 日志输出
 * @author jjx
 * @date 2014年9月24日 上午10:49:58
 * @version V1.0 
 */

package com.power.until;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.power.data.NewsData;

/**
 * @author jjx
 *
 */

public class BtHelper extends Object {

	public static String g_newUrl = "http://www.oschina.net/action/api/news_list";
	public static String g_blogUrl = "http://www.oschina.net/action/api/blog_list";
	public static String g_recommend_url = " http://www.oschina.net/action/api/blog_list?";
	
	
	private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
	    @Override
	    protected SimpleDateFormat initialValue() {
	        return new SimpleDateFormat("yyyy-MM-dd");
	    }
	};

	private static String TAG = "oschina_app";

	public static void logOutPut(String log) {
		android.util.Log.d(TAG, log);
	}

	public static List<NewsData> newsXmlParser(String xmlStr) {

		List<NewsData>  list = new ArrayList<NewsData>();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();

			try {
				//Document document = builder.parse(xmlStr);
				Document document =builder.parse(new InputSource(new ByteArrayInputStream(xmlStr.getBytes("utf-8"))));
				Element root = document.getDocumentElement();
				
				NodeList items = root.getElementsByTagName("news");
				
				for(int i=0;i<items.getLength();i++)
				{
					Element element  = (Element) items.item(i);
					String _id = getNodeValue(element, "id");
					String _title = getNodeValue(element, "title");
					String _commentCount = getNodeValue(element, "commentCount");
					String _author = getNodeValue(element, "author");
					String _authorid =getNodeValue(element,"authorid");
					String _pubDate = getNodeValue(element, "pubDate");
					String _url = getNodeValue(element, "url");
					
					String _type = null;
					String _authoruid2 = null;
					NodeList nodeList = element.getElementsByTagName("newstype").item(0).getChildNodes();  
						
					for(int j=0;j<nodeList.getLength();j++)
					{
						if("type".equals(nodeList.item(j).getNodeName())){  
							_type = nodeList.item(j).getFirstChild().getNodeValue();
						}
						else if("authoruid2".equals(nodeList.item(j).getNodeName()))
						{
							_authoruid2 = nodeList.item(j).getFirstChild().getNodeValue();
						}
					}	
					
					NewsData data = new NewsData(_id, _title, _commentCount, _author, _authorid, _pubDate, _url, _type, _authoruid2);
					list.add(data); 
				}
								
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static void  blogXmlParser(String xmlStr) {
		try {
			XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
			
		//	parser.setInput(new InputSource(new ByteArrayInputStream(xmlStr.getBytes("utf-8"))), "utf-8");
			parser.setInput(new StringReader(xmlStr));
			  int eventType = parser.getEventType();
		       while (eventType != XmlPullParser.END_DOCUMENT) {
		           if(eventType == XmlPullParser.START_DOCUMENT) {
		        	   BtHelper.logOutPut("Start document");
		           } else if(eventType == XmlPullParser.START_TAG) {
		        	   BtHelper.logOutPut("Start tag "+parser.getName());
		           } else if(eventType == XmlPullParser.END_TAG) {
		        	   BtHelper.logOutPut("End tag "+parser.getName());
		           } else if(eventType == XmlPullParser.TEXT) {
		               BtHelper.logOutPut("Text "+parser.getText());
		           }
		           
		           
		           try {
					eventType = parser.next();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		          }
		          System.out.println("End document");
			
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getNodeValue(Element element,String tag)
	{
		NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();  
		Node node = (Node) nodeList.item(0);  
		if(node!=null)
		{
			return node.getNodeValue();
		}
		else
		{
			return null;
		}
	}
	 
	public static String friendly_time(String sdate) {
	    Date time =  null;
	    
	    /*if (TimeZoneUtil.isInEasternEightZones()) {
	        time = toDate(sdate);
	    } else {
	        time = TimeZoneUtil.transformTime(toDate(sdate),
	                TimeZone.getTimeZone("GMT+08"), TimeZone.getDefault());
	    }
	    */
	    if (time == null) {
	        return "Unknown";
	    }
	    String ftime = "";
	    Calendar cal = Calendar.getInstance();

	    String curDate = dateFormater2.get().format(cal.getTime());
	    String paramDate = dateFormater2.get().format(time);
	    if (curDate.equals(paramDate)) {
	        int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
	        if (hour == 0)
	            ftime = Math.max(
	                    (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
	                    + "分钟前";
	        else
	            ftime = hour + "小时前";
	        return ftime;
	    }

	    long lt = time.getTime() / 86400000;
	    long ct = cal.getTimeInMillis() / 86400000;
	    int days = (int) (ct - lt);
	    if (days == 0) {
	        int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
	        if (hour == 0)
	            ftime = Math.max(
	                    (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
	                    + "分钟前";
	        else
	            ftime = hour + "小时前";
	    } else if (days == 1) {
	        ftime = "昨天";
	    } else if (days == 2) {
	        ftime = "前天";
	    } else if (days > 2 && days <= 10) {
	        ftime = days + "天前";
	    } else if (days > 10) {
	        ftime = dateFormater2.get().format(time);
	    }
	    return ftime;
	}

}
