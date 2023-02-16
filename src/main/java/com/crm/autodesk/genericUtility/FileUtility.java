package com.crm.autodesk.genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
/**
 * It contains File Specific Reusable methods/actions
 * @author Vineeth
 *
 */
public class FileUtility {

	private Properties pro_Obj;
	/**
	 * This constructor intiallize properties class and load all the keys into java memory
	 */
	public FileUtility() {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(IPathConstant.PROPERTY_FILE_PATH);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		pro_Obj=new Properties();
		try {
			pro_Obj.load(fis);
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
	 * It is used to read the value from properties file based on Argument passed
	 * @param key
	 * @return
	 * @throws Throwable
	 */
	public String getPropertyKeyValue(String key){
		return pro_Obj.getProperty(key);		
	}
}
