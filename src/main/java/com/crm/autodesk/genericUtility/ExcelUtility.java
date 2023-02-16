package com.crm.autodesk.genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * It contains Excel Specific Reusable methods/actions
 * @author Vineeth
 *
 */
public class ExcelUtility {
	private DataFormatter df;
	private Workbook wb;
	/**
	 * in this constructor is used for intializing
	 */
	public ExcelUtility() {
		df=new DataFormatter();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(IPathConstant.EXCEL_FILE_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			wb = WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

/**
 * this method is used to fetch the data from excel sheet from one sheet
 * @param sheetName
 * @param exptestCaseName
 * @return
 */
	public Map<String, String> getData(String sheetName,String exptestCaseName)
	{
		Sheet sheet = wb.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();    
		Map<String, String>map=new HashMap<>();
		for(int i=0;i<=rowCount;i++)      
		{
			String testScriptName = df.formatCellValue(sheet.getRow(i).getCell(0));
			if(testScriptName.equals(exptestCaseName))
			{
				for(int j=1;j<sheet.getRow(i).getLastCellNum();j++)  
				{
					String key=df.formatCellValue(sheet.getRow(i).getCell(j));
					String value=df.formatCellValue(sheet.getRow(i+1).getCell(j));
					map.put(key,value);
				}
				break;
			}
		}
		return map;
	}

/**
 * this method is used to fetch the data from excel another sheet
 * @param sheetName
 * @return
 */
	public Map<String, String> getData(String sheetName)
	{
		Sheet sheet = wb.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();     
		Map<String, String>map=new HashMap<>();
		for(int i=0;i<=rowCount;i++)       
		{	 
			String key=df.formatCellValue(sheet.getRow(i).getCell(0));
			String value=df.formatCellValue(sheet.getRow(i).getCell(1));
			map.put(key,value);
		}
		closeExcel();
		return map;
	}
/**
 * this method is used to close the excel connection
 */
	public void closeExcel() {
		try {
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
