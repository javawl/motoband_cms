package com.motoband.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;

public class DateFormat {
	
	public static String dateToString(Date date)
	{
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:MM:ss"); 
		java.sql.Date sDate=new java.sql.Date(date.getTime());
		return df.format(sDate);
	}
	
	public static Date getNow(Date date) throws ParseException
	{
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:MM:ss"); 
		java.sql.Date sDate=new java.sql.Date(date.getTime());
		return df.parse(df.format(sDate));
	}
	
	public  static String getYesterday(){
		Calendar   cal   =   Calendar.getInstance();
	    cal.add(Calendar.DATE,   -1);
	    String yesterday = new SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
	    return yesterday;
	}

	/**
	 * 将日期格式的数据按照预定的格式进行转换
	 * 
	 * @param date
	 * @param formatGeshi
	 * @return
	 */
	public static String formatDate(Date date, String formatGeshi) {

		SimpleDateFormat format = new SimpleDateFormat(formatGeshi);

		return format.format(date);

	}
	
	/**
	 * 判断是否是日期格式数据
	 * 
	 * @param cell
	 * @return
	 */
	public static boolean isCellDateFormatted(Cell cell) {

		if (HSSFDateUtil.isCellDateFormatted(cell)) {
			return true;
		}
		return false;

	}
	
	/**
	 * 获取某天00:00:00的时间戳
	 * @param SqlTime
	 * @return
	 */
	public static long getDayStart(long time) {
		int t = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String today =  sdf.format(new Date(time*1000l));		
			Date d;
			d = sdf.parse(today);
			t = (int) (d.getTime()/1000);
		} catch (ParseException e) {
			
		}
		return t;		
	}
	
	/**
	 * 获取某天天23:59:59的时间戳
	 * @param SqlTime
	 * @return
	 */
	public static long getDayEnd(long time) {
		int t = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String today =  sdf.format(new Date(time*1000l));			
			Date d;
			d = sdf.parse(today);
			t = (int) (d.getTime()/1000+60*60*24-1);
		} catch (ParseException e) {
			
		}
		return t;		
	}
	/**
	 *  获得日期字符串是一年中的第几周
	* <p>Method:getWeekInYear </p>
	* <p>Description: </p>
	* <p>Return Type: int</p>
	* @author fanghebin
	* @date 2017年2月18日 下午4:01:16
	 */
	public static int getWeekInYear(String time){
		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		  Date date = null;
		try {
			date = format.parse(time);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		  Calendar calendar = Calendar.getInstance();
		//  calendar.setFirstDayOfWeek(Calendar.MONDAY);
		  calendar.setTime(date);
		 
		return  calendar.get(Calendar.WEEK_OF_YEAR);
	}
	/**
	 *  获得日期字符串d的年份
	* <p>Method:getWeekInYear </p>
	* <p>Description: </p>
	* <p>Return Type: int</p>
	* @author fanghebin
	* @date 2017年2月18日 下午4:01:16
	 */
	public static int getYearInTime(String time){
		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		  Date date = null;
		try {
			date = format.parse(time);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		  Calendar calendar = Calendar.getInstance();
		
		  calendar.setTime(date);
		 
		return  calendar.get(Calendar.YEAR);
	}
	/**
	 *  获得日期字符串是一年中的第几月
	* <p>Method:getWeekInYear </p>
	* <p>Description: </p>
	* <p>Return Type: int</p>
	* @author fanghebin
	* @date 2017年2月18日 下午4:01:16
	 */
	public static int getMonthInYear(String time){
		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		  Date date = null;
		try {
			date = format.parse(time);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		  Calendar calendar = Calendar.getInstance();
	
		  calendar.setTime(date);
		 
		return  calendar.get(Calendar.MONTH);
	}
	 /**  
     * 得到某年某周的第一天  
     *  
     * @param year  
     * @param week  
     * @return  
     */  
    public static Date getFirstDayOfWeek(int year, int week) {  
        Calendar c = Calendar.getInstance();  
        c.set(Calendar.YEAR, year);  
        c.set(Calendar.WEEK_OF_YEAR, week);  
      //  c.setFirstDayOfWeek(Calendar.MONDAY);  
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Sunday   
          
        return c.getTime();  
    }  
	  /**  
     * 得到某年某周的最后一天  
     *  
     * @param year  
     * @param week  
     * @return  
     */  
    public static Date getLastDayOfWeek(int year, int week) {  
        Calendar c = Calendar.getInstance();  
        c.set(Calendar.YEAR, year);  
        c.set(Calendar.WEEK_OF_YEAR, week);  
      //  c.setFirstDayOfWeek(Calendar.MONDAY);  
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Saturday  
          
        return c.getTime();  
    }  
    /**  
     * 得到某年某月的第一天  
     *  
     * @param year  
     * @param month  
     * @return  
     */  
    public static Date getFirstDayOfMonth(int year, int month) {     
        Calendar cal = Calendar.getInstance();     
        cal.set(Calendar.YEAR, year);     
        cal.set(Calendar.MONTH, month);  
        cal.set(Calendar.DAY_OF_MONTH,cal.getMinimum(Calendar.DATE));  
       return cal.getTime(); 
    }   
    /**  
     * 得到某年某月的最后一天  
     *  
     * @param year  
     * @param month  
     * @return  
     */  
    public static Date getLastDayOfMonth(int year, int month) {  
        Calendar c = Calendar.getInstance();  
        c.set(Calendar.YEAR, year);  
        c.set(Calendar.MONTH, month);  
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));  
  
        return c.getTime();  
    }  
}
