package com.important.java8.time;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

import org.junit.Test;

public class TestLocalDateTime {

	/**
	 * DateTimeFormatter：格式化时间/日期
	 */
	@Test
	public void test5() {
		DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;
		LocalDateTime ldt = LocalDateTime.now();
		
		String format = ldt.format(dtf);
		System.out.println(format);
		
		System.out.println("********************************");
		
		DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
		String format2 = dtf2.format(ldt);
		System.out.println(format2);
		
		// 日期转时间
		LocalDateTime parse = LocalDateTime.parse(format2, dtf2);
		System.out.println(parse);
	}
	
	
	/**
	 * TemporalAdjuster：时间校正器
	 */
	@Test
	public void test4() {
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);
		
		LocalDateTime withDayOfMonth = ldt.withDayOfMonth(10);
		System.out.println(withDayOfMonth);
		
		LocalDateTime with = ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
		System.out.println(with);
	}
	
	
	/**
	 * Duration：计算两个时间之间的间隔
	 * Period：计算两个日期之间的间隔
	 */
	@Test
	public void test3() {
		Instant ins1 = Instant.now();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Instant ins2 = Instant.now();
		
		// 计算毫秒
		Duration between = Duration.between(ins1, ins2);
		System.out.println(between.toMillis());
		
		System.out.println("******************************");
		
		LocalDate ld1 = LocalDate.of(2015, 1, 2);
		LocalDate ld2 = LocalDate.now();
		Period between2 = Period.between(ld1, ld2);
		System.out.println(between2.getYears());
		System.out.println(between2.getMonths());
		System.out.println(between2.getDays());
		
		// 计算相差天数
		System.out.println(ld2.toEpochDay() - ld1.toEpochDay());
		
	}
	
	
	/**
	 * Instant：时间戳（以Unix元年：1970年1月1日00:00:00到某个时间之间的毫秒值）
	 */
	@Test
	public void test2() {
		// 默认获取UTC时区
		Instant now = Instant.now();
		System.out.println(now);
	}
	
	
	/**
	 * LocalDate、LocalTime、LocalDateTime
	 */
	@Test
	public void test1() {
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);
		
		LocalDateTime ldt2 = LocalDateTime.of(2020, 1, 1, 12, 21, 30);
		System.out.println(ldt2);
		
		// 加
		LocalDateTime plusDays = ldt.plusDays(2);
		System.out.println(plusDays);
		
		// 减
		LocalDateTime minusHours = ldt.minusHours(2);
		System.out.println(minusHours);
		
		// 比较：返回boolean
		System.out.println(ldt2.isBefore(ldt));
		
		// 星期
		System.out.println(ldt.getMonth());
		// 数字
		System.out.println(ldt.getMonthValue());
		// 一个月中的第几天
		System.out.println(ldt.getDayOfMonth());
	}
	
}
