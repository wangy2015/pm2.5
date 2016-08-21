package com.xf.grasp;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: 胜利软件公司</p>
 * <p>DepartmentBean: 油田应用开发部</p>
 * Date: 2007-8-20
 *
 * @version 0.1
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.Format;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Calendar;

/**
 * 日期操作工具类，提供常见的日期操作需要的方法。
 */

public class DateUtil {
    /**
     * 此类中封装一些常用的字符串操作。
     * 所有方法都是静态方法，不需要生成此类的实例，
     * 为避免生成此类的实例，构造方法被申明为private类型的。
     */
    private DateUtil() {
    }

    /**
     * 获取当前系统时间，格式'yyyy-mm-dd'
     *
     * @return 当前系统时间
     */
    public static String getLocalDate() {
        return format(new Date());
    }

    public static Date getDateTime() {
		return new Date(System.currentTimeMillis());
	}
    
    /**
     * 获取指定格式的系统时间字符串
     *
     * @param dateFormat 日期格式
     * @return 指定格式的系统时间
     */
    public static String getLocalDate(String dateFormat) {
        return format(getDate(), dateFormat);
    }

    /**
     * 返回当前系统时间
     *
     * @return 系统时间
     */
    public static Date getDate() {
        return new Date();
    }

    /**
     * 返回当前系统日期、时间
     *
     * @param format 返回日期的格式
     * @return 系统日期、时间
     */
    public static Date getDate(String format) {
        return parse(getLocalDate(format));
    }

    /**
     * 格式化日期 'yyyy-mm-dd'
     *
     * @param date 待格式化的日期
     * @return 日期字符串格式 'yyyy-mm-dd',date为空时返回""
     */
    public static String format(Date date) {
        return format(date, "yyyy-MM-dd");
    }


    /**
     * 格式：dateFormat 指定的格式
     *
     * @param date       待格式化的日期
     * @param dateFormat 日期格式
     * @return 日期的字符串格式，date为空时返回""
     */
    public static String format(Date date, String dateFormat) {
        if (date == null) return "";
        Format formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(date);
    }

    /**
     * 格式：dateFormat 指定的格式
     *
     * @param date       待格式化的日期
     * @param dateFormat 日期格式
     * @param defaultRtn date为空时的默认返回值
     * @return 日期的字符串格式
     */
    public static String format(Date date, String dateFormat, String defaultRtn) {
        if (defaultRtn == null) defaultRtn = "";
        if (date == null) return defaultRtn;
        Format formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(date);
    }

    /**
     * 将"yyyy-MM-dd"格式的字符串格式化为Date类型
     *
     * @param str 待处理的字符串
     * @return 日期，若出现异常ParseException则返回 null
     */
    public static Date parse(String str) {
        return parse(str, "yyyy-MM-dd");
    }

    /**
     * 将指定格式的字符串格式化为Date类型
     *
     * @param str        待处理的字符串
     * @param dateFormat 日期的格式
     * @return 日期，若出现异常ParseException则返回 null
     */
    public static Date parse(String str, String dateFormat) {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        try {
            if (str == null) {
                return null;
            }
            return format.parse(str);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 根据日历的规则，为给定的日历字段添加或减去指定的时间量。
     *
     * @param date   日期
     * @param field  日历字段。
     * @param amount 为字段添加的日期或时间量。
     * @return 修改后的日期
     */
    public static Date add(Date date, int field, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    /**
     * 此方法将两个日期之间的时间以dateFormat的格式转换为字符串数组
     *
     * @param dateStart  起始日期
     * @param dateEnd    终止日期
     * @param dateFormat 日期格式
     * @return 日期的字符串数组
     */
    public static String[] splitDay(String dateStart, String dateEnd, String dateFormat) {
        String[] wordLists;

        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        Calendar grcStart = new GregorianCalendar();
        Calendar grcEnd = new GregorianCalendar();

        if (dateStart == null || dateEnd == null) {
            wordLists = new String[1];
            wordLists[0] = dateStart;
            return wordLists;
        }
        try {
            grcStart.setTime(format.parse(dateStart));
        } catch (ParseException e) {
            grcStart = new GregorianCalendar();
        }
        try {
            grcEnd.setTime(format.parse(dateEnd));
        } catch (ParseException ex) {
            grcEnd = new GregorianCalendar();
        }
        return splitDay(grcStart, grcEnd, dateFormat);
    }

    /**
     * 此方法将两个日期之间的时间以dateFormat的格式转换为字符串数组
     *
     * @param grcStart   起始日期
     * @param grcEnd     终止日期
     * @param dateFormat 日期格式
     * @return 日期的字符串数组
     */
    public static String[] splitDay(Calendar grcStart, Calendar grcEnd, String dateFormat) {
        String[] wordLists;
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        GregorianCalendar grcTmp = new GregorianCalendar();
        grcTmp.setTime(grcStart.getTime());
        int total = 0;
        while (grcTmp.get(Calendar.YEAR) < grcEnd.get(Calendar.YEAR)) {
            if (grcTmp.isLeapYear(grcTmp.get(Calendar.YEAR)))
                total += 365;
            else
                total += 366;
            grcTmp.add(Calendar.YEAR, 1);
        }
        if (grcTmp.get(Calendar.YEAR) == grcEnd.get(Calendar.YEAR))
            total += grcEnd.get(Calendar.DAY_OF_YEAR) - grcTmp.get(Calendar.DAY_OF_YEAR);
        if (total <= 0)
            return null;
        wordLists = new String[total];
        for (int i = 0; i < total; i++) {
            wordLists[i] = format.format(grcStart.getTime());
            grcStart.add(Calendar.DATE, 1);
        }
        return wordLists;
    }

    /**
     * 此方法将两个日期之间的时间以monthFormat的格式转换为字符串数组
     *
     * @param startMonth  起始日期
     * @param endMonth    终止日期
     * @param monthFormat 日期格式
     * @return 日期的字符串数组
     */
    public static String[] splitMonth(String startMonth, String endMonth, String monthFormat) {
        String[] wordLists;

        SimpleDateFormat format = new SimpleDateFormat(monthFormat);
        Calendar grcStart = new GregorianCalendar();
        Calendar grcEnd = new GregorianCalendar();

        if (startMonth == null || endMonth == null) {
            wordLists = new String[1];
            wordLists[0] = startMonth;
            return wordLists;
        }
        try {
            grcStart.setTime(format.parse(startMonth));
        } catch (ParseException e) {
            grcStart = new GregorianCalendar();
        }
        try {
            grcEnd.setTime(format.parse(endMonth));
        } catch (ParseException ex) {
            grcEnd = new GregorianCalendar();
        }
        return splitMonth(grcStart, grcEnd, monthFormat);
    }

    /**
     * 此方法将两个日期之间的时间以dateFormat的格式转换为字符串数组
     *
     * @param grcStart   起始日期
     * @param grcEnd     终止日期
     * @param dateFormat 日期格式
     * @return 日期的字符串数组
     */
    public static String[] splitMonth(Calendar grcStart, Calendar grcEnd, String dateFormat) {
        String[] wordLists;
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        GregorianCalendar grcTmp = new GregorianCalendar();
        grcTmp.setTime(grcStart.getTime());
        int total = 0;
        while (grcTmp.get(Calendar.YEAR) < grcEnd.get(Calendar.YEAR)) {
            total += 12;
            grcTmp.add(Calendar.YEAR, 1);
        }
        if (grcTmp.get(Calendar.YEAR) == grcEnd.get(Calendar.YEAR))
            total += grcEnd.get(Calendar.MONTH) - grcTmp.get(Calendar.MONTH);
        if (total <= 0)
            return null;
        total++;
        wordLists = new String[total];
        for (int i = 0; i < total; i++) {
            wordLists[i] = format.format(grcStart.getTime());
            grcStart.add(Calendar.MONTH, 1);
        }
        return wordLists;
    }

    /**
     * 计算两个日期之间的日期差值
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 日期1-日期2 的值
     * @since 1.1
     */
    public static int differenceOfDay(Date date1, Date date2) {
        int sign = date1.compareTo(date2);
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        if (sign > 0) {
            cal1.setTime(date1);
            cal2.setTime(date2);
        } else {
            cal1.setTime(date2);
            cal2.setTime(date1);
        }
        int total = 0;
        while (cal2.get(Calendar.YEAR) < cal1.get(Calendar.YEAR)) {
            total += cal2.getActualMaximum(Calendar.DAY_OF_YEAR);
            cal2.add(Calendar.YEAR, 1);
        }
        if (cal2.get(Calendar.YEAR) == cal1.get(Calendar.YEAR)) {
            total += cal1.get(Calendar.DAY_OF_YEAR) - cal2.get(Calendar.DAY_OF_YEAR);
        }
        return sign * total;
    }

    /**
     * 根据日历的规则，获取给定的日期所在旬差值为amount的旬度的第一日。
     *
     * @param date   日期
     * @param amount 与日期所在旬的差值量
     * @return 根据日历的规则，获取给定的日期所在旬差值为amount的旬度的第一日
     * @since 1.1
     */
    public static Date getFirstDayPeriodOfTenDays(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int xunCount = (calendar.get(Calendar.DAY_OF_MONTH) - 1) / 10;
        if (xunCount == 3) xunCount = 2;
        int monthDiff;
        if ((xunCount + amount) < 0) {
            if ((xunCount + amount) / 3 == 0) monthDiff = ((xunCount + amount) / 3 - 1);
            else monthDiff = (xunCount + amount) / 3;
        } else {
            monthDiff = (xunCount + amount) / 3;
        }
        calendar.setTime(DateUtil.add(calendar.getTime(), Calendar.MONTH, monthDiff));
        if ((xunCount + amount) % 3 == 0) {
            xunCount = 0;
        } else if ((xunCount + amount) % 3 < 0) {
            xunCount = (xunCount + amount) % 3 + 3;
        } else {
            xunCount = (xunCount + amount) % 3;
        }
        switch (xunCount) {
            case 0:
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                break;
            case 1:
                calendar.set(Calendar.DAY_OF_MONTH, 11);
                break;
            default:
                calendar.set(Calendar.DAY_OF_MONTH, 21);
        }
        return calendar.getTime();
    }

    /**
     * 根据日历的规则，获取给定的日期所在旬差值为amount的旬度的最后一日。
     *
     * @param date   日期
     * @param amount 与日期所在旬的差值量
     * @return 根据日历的规则，获取给定的日期所在旬差值为amount的旬度的最后一日
     * @since 1.1
     */
    public static Date getEndDayPeriodOfTenDays(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int xunCount = (calendar.get(Calendar.DAY_OF_MONTH) - 1) / 10;
        if (xunCount == 3) xunCount = 2;
        int monthDiff;
        if ((xunCount + amount) < 0) {
            if ((xunCount + amount) / 3 == 0) monthDiff = ((xunCount + amount) / 3 - 1);
            else monthDiff = (xunCount + amount) / 3;
        } else {
            monthDiff = (xunCount + amount) / 3;
        }
        calendar.setTime(DateUtil.add(calendar.getTime(), Calendar.MONTH, monthDiff));
        if ((xunCount + amount) % 3 == 0) {
            xunCount = 0;
        } else if ((xunCount + amount) % 3 < 0) {
            xunCount = (xunCount + amount) % 3 + 3;
        } else {
            xunCount = (xunCount + amount) % 3;
        }
        switch (xunCount) {
            case 0:
                calendar.set(Calendar.DAY_OF_MONTH, 10);
                break;
            case 1:
                calendar.set(Calendar.DAY_OF_MONTH, 20);
                break;
            default:
                calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        }
        return calendar.getTime();
    }

    /**
     * 计算两个日期之间的差,单位是天
     * Chivas Ju. zhuy#slof.com
     *
     * @param date1
     * @param date2
     * @return long
     * todo 待验证
     */
    private static long getDays(Date date1, Date date2) {
        long quot = 0;
        try {
            quot = date1.getTime() - date2.getTime();
            quot = quot / 1000 / 60 / 60 / 24;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Math.abs(quot);

    }

    //第N周开始日期
    //todo 待验证
    private static Date getStartTime(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        int k = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DAY_OF_YEAR, 1 - k);
        calendar.add(Calendar.WEEK_OF_YEAR, i - 1);
        return calendar.getTime();
    }

    //第N周结束日期
    //todo 待验证
    private static Date getEndTime(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        int k = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DAY_OF_YEAR, 1 - k);
        calendar.add(Calendar.WEEK_OF_YEAR, i - 1);
        calendar.add(Calendar.DAY_OF_YEAR, 6);
        return calendar.getTime();
    }
    /**
     *  返回年
     * @param q
     * @return
     */
    public static String getYear(Date q){
    	return format(q,"yyyy");
    }
    /**
     * 返回月
     * @param q
     * @return
     */
    public static String getMonth(Date q){
    	return format(q,"MM");
    }
    /**
     * 返回日子
     * @param q
     * @return
     */
    public static String getDay(Date q){
    	return format(q,"dd");
    }
    /**
     * 增加日期
     *
     * @param date  日期
     * @param field the calendar field
     * @param i     天数
     * @return 添加i天后的日期
     */
    public static Date addDate(Date date, int field, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, i);
        return calendar.getTime();
    }    
    /**
     * 增加日期
     *
     * @param date 日期
     * @param i    天数
     * @return 添加i天后的日期
     */
    public static Date addDate(Date date, int i) {
        return addDate(date, Calendar.DATE, i);
    }

    /**
     * 增加日期
     *
     * @param date 日期
     * @param i    月数
     * @return 添加i月后的日期
     */
    public static Date addMonth(Date date, int i) {
        return addDate(date, Calendar.MONTH, i);
    }

    /**
     * 增加日期
     *
     * @param date 日期
     * @param i    年数
     * @return 添加i年后的日期
     */
    public static Date addYear(Date date, int i) {
        return addDate(date, Calendar.YEAR, i);
    }  
    
    /**
     * 传入年月，获取旬度的开始结束时间
     * @param year
     * @param month
     * @param param 1-上旬；2-中旬；3-下旬
     * @return 数组第一个为开始时间，第二个为结束时间
     */
    public static Date[] getTenDay(String year, String month, int param){
    	Date[] date = new Date[2];
    	
    	if(param==1){
    		date[0] = parse(year+"-"+month+"-01");
    		date[1] = parse(year+"-"+month+"-10");
    	}else if(param==2){
    		date[0] = parse(year+"-"+month+"-11");
    		date[1] = parse(year+"-"+month+"-20");
    	}else if(param==3){
    		date[0] = parse(year+"-"+month+"-21");
    		Calendar calendar = Calendar.getInstance();
    		calendar.setTime(date[0]);
    		date[1] = parse(year+"-"+month+"-" +(calendar.getActualMaximum(Calendar.DAY_OF_MONTH)));
    	}
    	
    	return date;
    }
    
    public static int getLastDayOfMonth(Date date){
    	
    	Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    
    public static String getXd(Date date){
    	String rtn = "";
    	System.out.println(Integer.parseInt(DateUtil.getDay(date)));
    	if(Integer.parseInt(DateUtil.getDay(date))>=1 && Integer.parseInt(DateUtil.getDay(date))<11){
    		rtn = "1";
    	}else if(Integer.parseInt(DateUtil.getDay(date))>10 && Integer.parseInt(DateUtil.getDay(date))<21){
    		rtn = "2";
    	}else{
    		rtn = "3";
    	}
    	return rtn;
    }
    
    public static String getTomorrow(String rq, long num) {
		try {
			java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(
					"yyyy-MM-dd");
			long timeLong = (format.parse(rq)).getTime();
			return format.format(new java.util.Date(timeLong + 3600 * 24 * 1000
					* num));
		} catch (Exception ex) {

		}
		return "";

	}
    public static long getInterval(String rq_start, String rq_end) {
		long resultLong = 0;
		long startLong = 0;
		long endLong = 0;
		try {
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					"yyyy-MM-dd");
			startLong = (sdf.parse(rq_start)).getTime();
			endLong = (sdf.parse(rq_end)).getTime();
			resultLong = (endLong - startLong) / (3600 * 24 * 1000) + 1;
		} catch (Exception e) {
            return 0;
		}
		return resultLong;
	}
    
    public static void main(String[] args){
    	System.out.println(getXd(DateUtil.getDate()));
//       System.out.println(getTenDay("2007", "02", 3)[0]);
//       System.out.println(getTenDay("2007", "02", 3)[1]);
//       System.out.println(DateUtil.getEndDayPeriodOfTenDays(parse("2007-02-21"),0));
//       System.out.println(DateUtil.getFirstDayPeriodOfTenDays(parse("2007-02-21"),0));
    }
}
