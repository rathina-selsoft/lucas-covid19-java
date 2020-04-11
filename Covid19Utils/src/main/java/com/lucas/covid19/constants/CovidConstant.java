package com.lucas.covid19.constants;

import java.text.SimpleDateFormat;

public class CovidConstant {
	
	public static final String SUCCESS = "success";
	public static final String IRRECOVERABLE_ERROR = "Irrecoverable error occurred, please contact helpdesk for assistance";
	public static final int TIMEOUT = 20;
	public static final String UNUSED = "unused";
	public static final SimpleDateFormat isoDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat userReadableDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
	public static final SimpleDateFormat utcDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.sss");

}
