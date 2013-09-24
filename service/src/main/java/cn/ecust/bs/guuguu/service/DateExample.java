package cn.ecust.bs.guuguu.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateExample {

	public static void main(String[] args) throws ParseException
	{
		//Mon Sep 23 2013 08:00:00 GMT+0800
		SimpleDateFormat sdf=new SimpleDateFormat("EEE MMM dd yyyy hh:mm:ss",Locale.ENGLISH);
		Date d =sdf.parse("Mon Sep 23 2013 08:00:00");
		System.out.println(d.toString());
	}
}
