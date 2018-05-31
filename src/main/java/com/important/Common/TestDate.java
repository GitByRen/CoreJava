package com.important.Common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class TestDate {

	// java.util.Date不易于国际化
	@Test
	public void test1() {
		Date d1 = new Date();
		System.out.println(d1.toString());
		System.out.println(d1.getTime());
		Date d2 = new Date(1526974625841L);
		System.out.println(d2);
	}

	// java.text.SimpleDateFormat
	@Test
	public void test2() {
		// 1.格式化1
		SimpleDateFormat sdf = new SimpleDateFormat();
		String format = sdf.format(new Date());
		System.out.println(format);

		// 2.格式化2
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format2 = sdf2.format(new Date());
		System.out.println(format2);
		
		// 3.解析
		try {
			Date parse = sdf.parse("18-5-12 下午3:24");
			System.out.println(parse);
			Date parse2 = sdf2.parse("2018-05-22 15:52:58");
			System.out.println(parse2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test3() {
		// get() add() set() getTime()
		Calendar c = Calendar.getInstance();
		int i = c.get(Calendar.DAY_OF_MONTH);
		System.out.println(i);
		
		c.add(Calendar.DAY_OF_MONTH, 2);
		int j = c.get(Calendar.DAY_OF_MONTH);
		System.out.println(j);
		
		c.set(Calendar.DAY_OF_MONTH, 23);
		Date time = c.getTime();
		System.out.println(time);
	}
}
