package com.xf.grasp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ExcelWriter {
	static void write(JSONArray jsonArray, String savePath) throws BiffException, IOException, RowsExceededException, WriteException {
		WritableWorkbook wwb = null;
		File file = new File(savePath);
		if (file.exists()){
			FileInputStream in = new FileInputStream(file);
			Workbook wb = Workbook.getWorkbook(in);
			wwb = Workbook.createWorkbook(file,wb); 
		}else {
			wwb = Workbook.createWorkbook(file);
		}
		
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject object = jsonArray.getJSONObject(i);
			String positionName = object.getString("position_name");
			if (positionName == null || "null".equals(positionName)) {
				positionName = "平均";
			}
			WritableSheet sheet = wwb.getSheet(positionName);
			if (sheet == null) {
				sheet = wwb.createSheet(positionName, i);
			}
			int rows = sheet.getRows();
			if (rows <= 0) {
				sheet.addCell(new Label(0, 0, "更新时间"));
				sheet.addCell(new Label(1, 0, "监测点"));
				sheet.addCell(new Label(2, 0, "AQR"));
				sheet.addCell(new Label(3, 0, "空气质量"));
				sheet.addCell(new Label(4, 0, "首要污染物"));
				sheet.addCell(new Label(5, 0, "PM2.5"));
				sheet.addCell(new Label(6, 0, "PM10"));
				sheet.addCell(new Label(7, 0, "CO"));
				sheet.addCell(new Label(8, 0, "NO2"));
				sheet.addCell(new Label(9, 0, "O3一小时"));
				sheet.addCell(new Label(10, 0, "O3八小时"));
				sheet.addCell(new Label(11, 0, "SO2"));
				rows++;
			}
			
			String timePoint = parseNullToEmpty(object.getString("time_point"));
			if (!timePoint.equals(sheet.getCell(0, rows - 1).getContents())) {
				sheet.addCell(new Label(0, rows, timePoint));
				sheet.addCell(new Label(1, rows, parseNullToEmpty(positionName)));
				sheet.addCell(new Label(2, rows, parseNullToEmpty(object.getString("aqi"))));
				sheet.addCell(new Label(3, rows, parseNullToEmpty(object.getString("quality"))));
				sheet.addCell(new Label(4, rows, parseNullToEmpty(object.getString("primary_pollutant"))));
				sheet.addCell(new Label(5, rows, parseNullToEmpty(object.getString("pm2_5"))));
				sheet.addCell(new Label(6, rows, parseNullToEmpty(object.getString("pm10"))));
				sheet.addCell(new Label(7, rows, parseNullToEmpty(object.getString("co"))));
				sheet.addCell(new Label(8, rows, parseNullToEmpty(object.getString("no2"))));
				sheet.addCell(new Label(9, rows, parseNullToEmpty(object.getString("o3"))));
				sheet.addCell(new Label(10, rows, parseNullToEmpty(object.getString("o3_8h"))));
				sheet.addCell(new Label(11, rows, parseNullToEmpty(object.getString("so2"))));
			}
		}
		
		wwb.write();
		
		wwb.close();
	}
	
	private static String parseNullToEmpty(String s) {
		return s == null || "null".equals(s) ? "" : s;
	}
}
