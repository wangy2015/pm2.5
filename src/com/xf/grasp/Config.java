package com.xf.grasp;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
	static String URL;
	static String[] CITY;
	static String TOKEN;
	static Integer INTERVAL;
	static String SAVE_PATH;
	

	static {
		Properties prop = new Properties();
		InputStream in = Config.class.getResourceAsStream("/config.properties");
		try {
			prop.load(in);
			CITY = ((String) prop.get("city")).split("\\|");
			URL = (String) prop.get("url");
			INTERVAL = Integer.valueOf((String) prop.get("interval"));
			SAVE_PATH = (String) prop.get("save_path");
			TOKEN = (String) prop.get("token");
		} catch (IOException e) {
			Frame.text.append("\r\n"+DateUtil.getLocalDate("yyyy-MM-dd HH:mm:ss")
					+ " 配置文件出现错误！");
		}
	}

}
