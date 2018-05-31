package com.important.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	/**
	 * 比较两个时间相差多长时间
	 */
	public static String getDatePoor(Date endDate, Date nowDate) {
		long nDay = 1000 * 60 * 60 * 24;
		long nHour = 1000 * 60 * 60;
		long nMinute = 1000 * 60;
		// long ns = 1000;
		// 获得两个时间的毫秒时间差异
		long diff = endDate.getTime() - nowDate.getTime();
		// 计算差多少天
		long day = diff / nDay;
		// 计算差多少小时
		long hour = diff % nDay / nHour;
		// 计算差多少分钟
		long min = diff % nDay % nHour / nMinute;
		// 计算差多少秒//输出结果
		// long sec = diff % nd % nh % nm / ns;
		return day + "天" + hour + "小时" + min + "分钟";
	}

	/**
	 * 指定日期的第一天
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
		return parseDefaultDate(formatDefaultDate(calendar.getTime()));
	}

	/**
	 * 指定日期的最后一天
	 */
	public static Date getLastDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
		// 不会智能计算
		calendar.roll(Calendar.DATE, -1);
		return parseDefaultDate(formatDefaultDate(calendar.getTime()));
	}

	/**
	 * 获取月份的天数
	 */
	public static int getDaysOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// 传入的是一个int类型，这个方法的具体意思就是说根据你传入的参数代表的意思（年、月、周等）查询当前（年、月、周）拥有的最大值。
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * yyyy-MM-dd
	 */
	public static Date parseDefaultDate(String dateTime) {
		return parseDate(dateTime, "yyyy-MM-dd");
	}

	/**
	 * yyyy-MM-dd
	 */
	public static String formatDefaultDate(Date date) {
		return formatDateByFormat(date, "yyyy-MM-dd");
	}

	/**
	 * formatDate
	 */
	public static String formatDateByFormat(Date date, String format) {
		String result = StringUtils.EMPTY_STRING;
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			result = sdf.format(date);
		}
		return result;
	}

	/**
	 * parseDate
	 */
	public static Date parseDate(String time, String format) {
		if (StringUtils.isNullOrEmpty(time)) {
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		try {
			return dateFormat.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
