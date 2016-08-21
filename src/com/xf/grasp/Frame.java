/**  
 *@Copyright: Copyright (c) 2014
 *@Company: Iwell
 */  
package com.xf.grasp;  

import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
  
  
/**  
 *@ClassName: Frame 
 *@Description: 窗体
 *@Author: wangy  
 *@Since: 2014-5-21  
 *@Version: 1.0  
 */
public class Frame extends JFrame {
	static JTextArea text;
	public Frame() {
		super();
		this.setTitle("pm2.5数据抓取程序");
		this.setSize(500, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		text = new JTextArea("数据文件保存路径："+Config.SAVE_PATH);
		text.append("\r\n采集数据城市："+Arrays.toString(Config.CITY));
		text.append("\r\n数据采集间隔："+Config.INTERVAL+"分钟");
		text.setEditable(false);
		JScrollPane scroll = new JScrollPane(text);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.add(scroll);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Frame();
		try {
			new GraspScheduler().startScheduler();
		} catch (Exception e) {
			Frame.text.append("\r\n"+DateUtil.getLocalDate("yyyy-MM-dd HH:mm:ss")
					+ " 抓取任务出现问题！");
			Frame.text.append("\r\n"+DateUtil.getLocalDate("yyyy-MM-dd HH:mm:ss")
					+ e);
		}
	}
}
