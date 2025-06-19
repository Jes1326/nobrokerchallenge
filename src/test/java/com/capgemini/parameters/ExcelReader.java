package com.capgemini.parameters;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {
	
	public static String getData(String excelPath, String sheetName, int row, int col) {
	
		String cellData =  "";
	
		try {
			FileInputStream fis = new FileInputStream(excelPath);
			
			Workbook wb = WorkbookFactory.create(fis);
			cellData = wb.getSheet(sheetName).getRow(row).getCell(col).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cellData;
	}
	
	public static int getRowCount(String excelPath, String sheetName) {
		
		int rowCount = 0;
		
		try {
			FileInputStream fis = new FileInputStream(excelPath);
			Workbook wb = WorkbookFactory.create(fis);
			
			rowCount = wb.getSheet(sheetName).getLastRowNum();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rowCount;
	}
}
