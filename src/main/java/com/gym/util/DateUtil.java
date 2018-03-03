package com.gym.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	/**
	 * 功能描述：返回月
	 *
	 * @param date
	 *            Date 日期
	 * @return 返回月份
	 */
	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    return calendar.get(Calendar.MONTH) + 1;
	}
	 
	/**
	 * 功能描述：返回日
	 *
	 * @param date
	 *            Date 日期
	 * @return 返回日份
	 */
	public static int getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    return calendar.get(Calendar.DAY_OF_MONTH);
	}
	 
	/**
	 * 功能描述：返回小
	 *
	 * @param date
	 *            日期
	 * @return 返回小时
	 */
	public static int getHour(Date date) {
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    return calendar.get(Calendar.HOUR_OF_DAY);
	}
	 
	/**
	 * 功能描述：返回分
	 *
	 * @param date
	 *            日期
	 * @return 返回分钟
	 */
	public static int getMinute(Date date) {
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    return calendar.get(Calendar.MINUTE);
	}
	 
	/**
	 * 返回秒钟
	 *
	 * @param date
	 *            Date 日期
	 * @return 返回秒钟
	 */
	public static int getSecond(Date date) {
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    return calendar.get(Calendar.SECOND);
	}
	 
	/**
	 * 功能描述：返回毫
	 *
	 * @param date
	 *            日期
	 * @return 返回毫
	 */
	public static long getMillis(Date date) {
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    return calendar.getTimeInMillis();
	}

}
