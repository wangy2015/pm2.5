package com.xf.grasp;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Grasp {

	/**
	 * @param args
	 */
	public static void grasp(String city) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			String url = Config.URL + "?city=" + city + "&token="
					+ Config.TOKEN;
			HttpGet httpget = new HttpGet(url);
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				HttpEntity entity = response.getEntity();
				String body = EntityUtils.toString(entity);
				EntityUtils.consume(response.getEntity());
				if (body != null && !"".equals(body)) {
					if (body.startsWith("{")) {
						JSONObject jsonObject = JSONObject.fromObject(body);
						String error = jsonObject.getString("error");
						Frame.text.append("\r\n"+DateUtil.getLocalDate("yyyy-MM-dd HH:mm:ss")
								+ error);
					}else if (body.startsWith("[")) {
						JSONArray jsonArray = JSONArray.fromObject(body);
						ExcelWriter.write(jsonArray, Config.SAVE_PATH + city + ".xls");
					}
				}
				
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
	}
}
