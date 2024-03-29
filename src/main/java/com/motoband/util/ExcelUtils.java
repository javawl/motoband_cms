package com.motoband.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class ExcelUtils {
	  //总行数
    private int totalRows = 0;  
    //总条数
    private int totalCells = 0; 
    //错误信息接收器
    private String errorMsg;
    //构造方法
    public ExcelUtils(){}
    //获取总行数
    public int getTotalRows()  { return totalRows;} 
    //获取总列数
    public int getTotalCells() {  return totalCells;} 
    //获取错误信息
    public String getErrorInfo() { return errorMsg; }  
    
  /**
   * 验证EXCEL文件
   * @param filePath
   * @return
   */
  public boolean validateExcel(String filePath){
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))){  
            errorMsg = "文件名不是excel格式";  
            return false;  
        }  
        return true;
  }
    
  /**
   * 传入的文件写入本地文件中
   * @param fielName
   * @return
   */
  public boolean writeExcelToLocal(String fileName,MultipartFile Mfile,String contextpath,String taskid){
	  boolean b=false;
	  //验证文件名是否合格
      if(!validateExcel(fileName)){
          return false;
      }
    
      //把spring文件上传的MultipartFile转换成CommonsMultipartFile类型
       CommonsMultipartFile cf= (CommonsMultipartFile)Mfile; 
       //获取本地存储路径
       File file = new  File(contextpath);
       //创建一个目录 （它的路径名由当前 File 对象指定，包括任一必须的父路径。）
       if (!file.exists()) file.mkdirs();
       //新建一个文件
       File file1 = new File(contextpath + "/"+taskid+ ".xlsx"); 
   //    System.out.println(contextpath + "/"+taskid+ ".xlsx");
       //将上传的文件写入新建的文件中
       try {
           cf.getFileItem().write(file1); 
           b=true;
       } catch (Exception e) {
           e.printStackTrace();
       }
       
    
      return b;
  }
  
  public  List<String> getExcelInfo(String path,String fileName){
	  File file = new File(path+"/"+fileName + ".xls"); 
	  File file1 = new File(path+"/"+fileName + ".xlsx"); 
	   //初始化客户信息的集合    
      List<String> customerList=new ArrayList<String>();
      //初始化输入流
      InputStream is = null;  
      try{
    	  //根据文件名判断文件是2003版本还是2007版本
          boolean isExcel2003 = true; 
         
          if(file.exists()){
        	  //根据新建的文件实例化输入流
              is = new FileInputStream(file);
          }else if(file1.exists()){
        	  //根据新建的文件实例化输入流
              is = new FileInputStream(file1); 
              isExcel2003 = false; 
          }
          
          
        
         //根据excel里面的内容读取客户信息
         customerList = getExcelInfo(is, isExcel2003); 
         is.close();
     }catch(Exception e){
         e.printStackTrace();
     } finally{
         if(is !=null)
         {
             try{
                 is.close();
             }catch(IOException e){
                 is = null;    
                 e.printStackTrace();  
             }
         }
     }
      
      return customerList;
  }
  
  
  
  
  /**
   * 根据excel里面的内容读取客户信息
   * @param is 输入流
   * @param isExcel2003 excel是2003还是2007版本
   * @return
   * @throws IOException
   */
  public  List<String> getExcelInfo(InputStream is,boolean isExcel2003){
       List<String> customerList=null;
       try{
           /** 根据版本选择创建Workbook的方式 */
           Workbook wb = null;
           //当excel是2003时
           if(isExcel2003){
               wb = new HSSFWorkbook(is); 
           }
           else{//当excel是2007时
               wb = new XSSFWorkbook(is); 
           }
           //读取Excel里面客户的信息
           customerList=readExcelValue(wb);
       }
       catch (IOException e)  {  
           e.printStackTrace();  
       }  
       return customerList;
  }
  /**
   * 读取Excel里面客户的信息
   * @param wb
   * @return
   */
  private List<String> readExcelValue(Workbook wb){ 
      //得到第一个shell  
       Sheet sheet=wb.getSheetAt(0);
       
      //得到Excel的行数
       this.totalRows=sheet.getPhysicalNumberOfRows();
       
      //得到Excel的列数(前提是有行数)
       if(totalRows>=1 && sheet.getRow(0) != null){
            this.totalCells=sheet.getRow(0).getPhysicalNumberOfCells();
       }
       
       List<String> customerList=new ArrayList<String>();
       String customer;            
      //循环Excel行数,从第二行开始。标题不入库
       for(int r=1;r<totalRows;r++){
           Row row = sheet.getRow(r);
           if (row == null) continue;
           customer = new String();
           
           //循环Excel的列
           for(int c = 0; c <this.totalCells; c++){    
               Cell cell = row.getCell(c);
               if (null != cell){
                   if(c==0){//第一列
                	   customer=cell.getStringCellValue();
                   }
               }
           }
           //添加客户
           customerList.add(customer);
       }
       return customerList;
  }
  
  
  
  // @描述：是否是2003的excel，返回true是2003 
  public static boolean isExcel2003(String filePath)  {  
       return filePath.matches("^.+\\.(?i)(xls)$");  
   }  
 
  //@描述：是否是2007的excel，返回true是2007 
  public static boolean isExcel2007(String filePath)  {  
       return filePath.matches("^.+\\.(?i)(xlsx)$");  
   }  


}
