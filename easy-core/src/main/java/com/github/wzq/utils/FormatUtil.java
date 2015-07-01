package com.github.wzq.utils;

import android.annotation.SuppressLint;
import android.util.Log;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by wzq on 15/4/14.
 */
public class FormatUtil {
	
	private static final String TAG = "FormatUtil";
	
	public static final String PATTERN_ZH_TIME = "MM月dd日 HH:mm";
	public static final String PATTERN_ZH_DATE = "yyyy年MM月dd日";
	public static final String PATTERN_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String PATTERN_DATE = "yyyy-MM-dd";
	
	@SuppressLint("SimpleDateFormat")
	public static String formatDate(String date,String pattern) {
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		try {
			Date d = s.parse(date);
			s.applyPattern(pattern);
			date = s.format(d);
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG, e.getClass().getSimpleName());
		}
		return date;
	}
	
	public static String formatNumber(String value){
		NumberFormat nf = NumberFormat.getNumberInstance(Locale.CHINA);
		String num = "0";
		try {
			num = nf.format(nf.parse(value));
		} catch (ParseException e) {
			e.printStackTrace();
			Log.e(TAG, e.getClass().getSimpleName());
		}
		return num;
	}
}
