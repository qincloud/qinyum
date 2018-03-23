package com.qinyum.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat format_yyyymm = new SimpleDateFormat("yyyyMM");

	/**
	 * 获得今天的时间
	 */
	public static String getToday() {
		Calendar today = Calendar.getInstance();
		today.add(Calendar.DAY_OF_WEEK, 0);
		return format.format(today.getTime());
	}

	public static Date getTodayDate() {
		Calendar today = Calendar.getInstance();
		today.add(Calendar.DAY_OF_WEEK, 0);
		try {
			return format.parse(format.format(today.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
			return today.getTime();
		}
	}

	/**
	 * 时间转换字符串
	 */
	public static String converDateToString(Date date) {
		return format.format(date);
	}

	/**
	 * 字符串转换时间
	 */
	public  static Date converStringToDate(String date) {
		try {
			return format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 本月第一天获取
	 */
	public static String getMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();
		final int firstDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
		Date oneDayFirst = calendar.getTime();
		oneDayFirst.setDate(firstDay);
		return format.format(oneDayFirst);
	}

	/**
	 * 本月最后一天获取
	 */
	public static String getMonthLastDay() {
		Calendar calendar = Calendar.getInstance();
		final int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		Date oneDayLast = calendar.getTime();
		oneDayLast.setDate(lastDay);
		return format.format(oneDayLast);
	}

	/**
	 * 获取月份第一天
	 */
	public static String monthFirstDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		date.setDate(calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return format.format(date);
	}

	/**
	 * 获取月份最后一天
	 */
	public static String monthLastDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		date.setDate(calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return format.format(date);
	}

	/**
	 * 获取指定月份第一天
	 */
	public static String monthZdFirstDay(int fix) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(calendar.MONTH, fix); // 0代表本月时间,1代表下个月时间,-1代表上个月时间
		Date date = calendar.getTime();
		date.setDate(calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return format.format(date);
	}

	/**
	 * 获取指定月份最后一天
	 */
	public static String monthZdLastDay(int fix) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(calendar.MONTH, fix);// 0代表本月时间,1代表下个月时间,-1代表上个月时间
		Date date = calendar.getTime();
		date.setDate(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return format.format(date);
	}

	/**
	 * 获取本年第一天
	 */
	public static String getCurrYearFirst() {
		Calendar calendar = Calendar.getInstance();
		int currentYear = calendar.get(Calendar.YEAR);
		return getYearFirst(currentYear);
	}

	/**
	 * 获取本年最后一天
	 */
	public static String getCurrYearLast() {
		Calendar calendar = Calendar.getInstance();
		int currentYear = calendar.get(Calendar.YEAR);
		return getYearLast(currentYear);
	}

	/**
	 * 获取某年第一天
	 */
	public static String getYearFirst(int year) {   //输入年份数字即可(要得到2016年的时间输入2016即可)
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		return format.format(currYearFirst);
	}

	/**
	 * 获取某年最后一天
	 */
	public static String getYearLast(int year) {     //输入年份数字即可(要得到2016年的时间输入2016即可)
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();
		return format.format(currYearLast);
	}

}
