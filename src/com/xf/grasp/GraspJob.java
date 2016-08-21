package com.xf.grasp;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class GraspJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) {
		Frame.text.append("\r\n"+DateUtil.getLocalDate("yyyy-MM-dd HH:mm:ss")
				+ " 开始抓取数据...");
		for (int i = 0; i < Config.CITY.length; i++) {
			try {
				Grasp.grasp(Config.CITY[i]);
			} catch (Exception e) {
				Frame.text.append("\r\n"+DateUtil.getLocalDate("yyyy-MM-dd HH:mm:ss")
						+ " 抓取数据出现问题！");
				Frame.text.append("\r\n"+DateUtil.getLocalDate("yyyy-MM-dd HH:mm:ss")
						+ e);
			}
		}
		Frame.text.append("\r\n"+DateUtil.getLocalDate("yyyy-MM-dd HH:mm:ss")
				+ " 抓取数据结束...");
	}

}
