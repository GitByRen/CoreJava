package com.important.java8.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeUtil {

	public static void main(String[] args) {
		String convertLongToString = timestampToString(1557562070000l);
		System.out.println(convertLongToString);
		
		String localDateTimeToString = localDateTimeToString(LocalDateTime.now());
		System.out.println(localDateTimeToString);
		
		Long localDateTimeToLong = localDateTimeToLong(LocalDateTime.now());
		System.out.println(localDateTimeToLong);
		
		LocalDateTime stringTolocalDateTime = stringTolocalDateTime("2018年10月11日 16:37:28");
		System.out.println(stringTolocalDateTime);
	}
	
	/**
	 * 时间戳转日期字符串
	 * 
	 * @param time 时间戳
	 * @return
	 */
	public static String timestampToString(Long time) {
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()));
    }
	
	/**
	 * LocalDateTime转时间戳
	 * 
	 * @param time
	 * @return
	 */
	public static Long localDateTimeToLong(LocalDateTime time) {
		return time.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }
	
	/**
	 * LocalDateTime转日期字符串
	 * 
	 * @param time
	 * @return
	 */
	public static String localDateTimeToString(LocalDateTime time) {
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return ftf.format(time);
    }
	
	/**
	 * 日期字符串转LocalDateTime
	 * 
	 * @param time
	 * @return
	 */
	public static LocalDateTime stringTolocalDateTime(String time) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
		return LocalDateTime.parse(time, dtf);
    }
	
}
