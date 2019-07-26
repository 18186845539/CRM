package org.jsoft.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.jsoft.model.Customer;

public class XlsUtil {

	public static void main(String[] args) {
		xls("D:/ws/1.xls");
	}
	
	@SuppressWarnings("resource")
	public static List<Customer> xls(String path){
		List<Customer> result = new ArrayList<Customer>();
		try {
			Workbook wb = new HSSFWorkbook(new FileInputStream(path));
			HSSFSheet sheet = (HSSFSheet) wb.getSheetAt(0);
			int rowLength = sheet.getLastRowNum();
			for(int rIndex = 1; rIndex <= rowLength; rIndex++){
				Row row = sheet.getRow(rIndex);
				String name = getValue(row.getCell(0));
				String source = getValue(row.getCell(1));
				String status = getValue(row.getCell(2));
				String sysUserId = getValue(row.getCell(3));
				String telTime = getValue(row.getCell(4));
				String estimateTime = getValue(row.getCell(5));
				String moneyStr = getValue(row.getCell(6));
				String address = getValue(row.getCell(7));
				String phone = getValue(row.getCell(8));
				String remark = getValue(row.getCell(9));
				String file = getValue(row.getCell(10));
				Customer customer = new Customer();
				customer.setId(UUIDUtil.getUUID());
				customer.setName(name);
				customer.setSource(source);
				customer.setStatus(status);
				customer.setSysUserId(sysUserId);
				customer.setTelTime(telTime);
				customer.setEstimateTime(estimateTime);
				customer.setMoney(new Double(moneyStr));
				customer.setAddress(address);
				customer.setPhone(phone);
				customer.setRemark(remark);
				customer.setFile(file);
				result.add(customer);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@SuppressWarnings("all")
	private static String getValue(Cell hssfCell) {
    	if(hssfCell==null){
    		return "---";
    	}
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
        	double cur=hssfCell.getNumericCellValue();
        	long longVal = Math.round(cur);
        	Object inputValue = null;
        	if(Double.parseDouble(longVal + ".0") == cur)  
        	        inputValue = longVal;  
        	else  
        	        inputValue = cur; 
            return String.valueOf(inputValue);
        } else if(hssfCell.getCellType() == hssfCell.CELL_TYPE_BLANK || hssfCell.getCellType() == hssfCell.CELL_TYPE_ERROR){
        	return "---";
        }
        else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
	
}
