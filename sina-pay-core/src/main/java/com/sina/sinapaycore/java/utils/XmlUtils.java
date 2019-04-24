package com.sina.sinapaycore.java.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sina.sinapaycore.java.exception.SDKException;

public class XmlUtils
{
	private static final Logger LOG = LoggerFactory.getLogger(XmlUtils.class);
	
	private static XmlUtils instance;
	
	/**
	 * mas地址
	 */
	private String mas;
	
	/**
	 * mgs地址
	 */
	private String mgs;
	
	/**
	 * sftp连接地址
	 */
	private String host;
	
	/**
	 * sftp连接用户名
	 */
	private String username;
	
	/**
	 * sftp端口号
	 */
	private int port;
	
	private XmlUtils()
	{
		
	}
	
	public static synchronized XmlUtils getInstance()
	{
		if (instance == null)
		{
			instance = new XmlUtils();
			String path = Tools.class.getResource("/").getPath().substring(1) + "setting.xml";
			Map<String, String> map = getUrlByEle(path);
			instance.setMas(map.get("mas"));
			instance.setMgs(map.get("mgs"));
			instance.setHost(map.get("host"));
			instance.setUsername(map.get("username"));
			instance.setPort(Integer.valueOf(map.get("port")));
		}
		return instance;
	}
	
	private static Map<String, String> getUrlByEle(String xmlPath)
	{
		SAXReader reader = new SAXReader();
		Document doc = null;
		Map<String, String> map = new HashMap<>();
		
		try
		{
			doc = reader.read(new File(xmlPath));
			Element root = doc.getRootElement();
			Element url = root.element("url");
			Element sftp = root.element("sftp");
			map.put("mas", url.element("mas").getText());
			map.put("mgs", url.element("mgs").getText());
			map.put("host", sftp.element("hostip").getText());
			map.put("username", sftp.element("username").getText());
			map.put("port", sftp.element("port").getText());
			return map;
		} catch (DocumentException e)
		{
			LOG.error("【SDK setting.xml读取失败】", e);
//			e.printStackTrace();
			throw new SDKException(e.getMessage());
		}
		
	}

	public String getMas()
	{
		return mas;
	}

	public void setMas(String mas)
	{
		this.mas = mas;
	}

	public String getMgs()
	{
		return mgs;
	}

	public void setMgs(String mgs)
	{
		this.mgs = mgs;
	}

	public String getHost()
	{
		return host;
	}

	public void setHost(String host)
	{
		this.host = host;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public int getPort()
	{
		return port;
	}

	public void setPort(int port)
	{
		this.port = port;
	}
	
}
