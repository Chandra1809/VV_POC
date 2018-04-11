 package com.sample.web.utilities;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




/*
 * @author ChandraShekhar.G
 *
 */

public class XlsReader {

	private Workbook xlWorkBook = null;
	private Sheet xlSheet = null;
	private Row xlRow = null;
	private Cell xlCell = null;
	private ArrayList<String> TestCases = new ArrayList<String>();
	private FileInputStream inputStream = null;

	private static Logger logger = LoggerFactory.getLogger(XlsReader.class);

	public XlsReader(String xlFilepath) {
		try {
			inputStream = new FileInputStream(xlFilepath);
		} catch (FileNotFoundException e) {
			logger.error("File(" + xlFilepath + ") not found : ", e);
		}
		try {
			if(xlFilepath.endsWith("xlsx")) {
				xlWorkBook = new XSSFWorkbook(inputStream);
			}
			else {
				xlWorkBook = new HSSFWorkbook(inputStream); 
			}
		} catch (IOException e) {
			logger.error("Exception in excel sheet : ", e);
		} 

	}


	/*
	 * Method to get the list of test cases to execute
	 */
	public ArrayList<String> getExecutableTestCases() throws IOException{

		Sheet xlSheet = xlWorkBook.getSheet("TestCases");
		int colIndex = 0;
		int rCount = xlSheet.getPhysicalNumberOfRows();
		xlRow = xlSheet.getRow(0);
		int cCount = xlRow.getLastCellNum();
		// Creating an array to store excel Data
		String[][] excelData = new String[rCount][cCount];
		//  Reading the column Index of RUNMODE column
		for ( int c=0 ; c<cCount ; c++){
			xlRow = xlSheet.getRow(0);
			xlCell = xlRow.getCell(c);
			String colName = xlCell.getStringCellValue().trim();
			if (colName.equalsIgnoreCase("RunMode")){
				colIndex = c;
			}
		}
		for (int r = 1; r < rCount; r++) {
			for (int c = 0; c < cCount; c++) {
				xlRow = xlSheet.getRow(r);
				xlCell = xlRow.getCell(c);
				//Reading complete excel data into an array -excelData-
				if(xlCell.getCellType() == HSSFCell.CELL_TYPE_STRING){
					excelData[r][c] = xlCell.getStringCellValue();
				}
				else if(xlCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC ){
					excelData[r][c] = String.valueOf(xlCell.getNumericCellValue());
				}							
			}
		}
		// Creating an array to store isExected column
		String[][] isExecuted = new String[rCount][1];

		for (int row = 1; row < rCount; row++) {
			isExecuted[row][0] = excelData[row][colIndex];
			if (isExecuted[row][0].equalsIgnoreCase("y")) {
				// Accessing complete row -which isExecuted=Yes
				TestCases.add(excelData[row][0].trim()); 
			}
		}
		return TestCases;
	}


	/*
	 * Method to get the rows Count in a Sheet
	 */
	private int getRowCount(String sheetName){
		try{
			int index = xlWorkBook.getSheetIndex(sheetName);
			if(index==-1)
				return 0;
			else{
				xlSheet = xlWorkBook.getSheetAt(index);
				int rowCount=xlSheet.getPhysicalNumberOfRows();
				return rowCount;
			}
		}catch(Exception e){
			logger.error("Exception in getting ROW COUNT from excel sheet : ", e);
			return -1;
		}
	}


	/*
	 * Method to get the data from Cell based on Column Name
	 */
	private String getCellData(String sheetName,String colName,int rowNum){
		try{
			int col_Num=-1;
			if(rowNum <=0){
				return "";
			}
			int index = xlWorkBook.getSheetIndex(sheetName);
			if(index==-1){
				return "";
			}
			xlSheet = xlWorkBook.getSheetAt(index);
			xlRow=xlSheet.getRow(0);
			for(int i=0;i<xlRow.getLastCellNum();i++){
				if(xlRow.getCell(i).getStringCellValue().trim().equals(colName.trim()))
					col_Num=i;
			}
			if(col_Num==-1){
				return "";
			}
			xlRow = xlSheet.getRow(rowNum-1);
			if(xlRow==null){
				return "";
			}
			xlCell = xlRow.getCell(col_Num);
			if(xlCell==null){
				return "";
			}
			if(xlCell.getCellType() == HSSFCell.CELL_TYPE_STRING){
				return xlCell.getStringCellValue();
			}
			else if(xlCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC ){
				return String.valueOf(xlCell.getNumericCellValue());
			}
			else if(xlCell.getCellType() == HSSFCell.CELL_TYPE_BLANK){
				return "";
			}
			else{
				return String.valueOf(xlCell.getBooleanCellValue());
			}
		}
		catch(Exception e){
			logger.error("row "+rowNum+" or column "+colName +" does not exist in xls", e);
			return "row "+rowNum+" or column "+colName +" does not exist in xls";
		}
	}



	/*
	 * Method to get the data from Cell based on Column Index
	 */
	private String getCellData(String sheetName,int colNum,int rowNum){
		try{
			if(rowNum <=0)
				return "";
			int index = xlWorkBook.getSheetIndex(sheetName);
			if(index==-1)
				return "";
			xlSheet = xlWorkBook.getSheetAt(index);
			xlRow = xlSheet.getRow(rowNum-1);
			if(xlRow==null)
				return "";
			xlCell = xlRow.getCell(colNum);
			if(xlCell==null)
				return "";
			if(xlCell.getCellType()==HSSFCell.CELL_TYPE_STRING){
				return xlCell.getStringCellValue();
			}
			else if(xlCell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
				return String.valueOf(xlCell.getNumericCellValue());
			}
			else if(xlCell.getCellType()==HSSFCell.CELL_TYPE_BLANK){
				return "";
			}
			else{ 
				return String.valueOf(xlCell.getBooleanCellValue());
			}
		}
		catch(Exception e){
			logger.error("row " + rowNum + " or column " + colNum + " does not exist in xls", e);
			return "row "+rowNum+" or column "+colNum +" does not exist in xls";
		}
	}



	/*
	 * Method to get the testdata for specified TestCase
	 */
	public Object[][] getTestData(String sheetName,String testCase){
		try{
			int testCaseStartRowNum=0;
			int testCaseEndRowNum=0;
			int totalRows = 0;
			Object[][] testdata = null;
			for(int rNum=1; rNum<=getRowCount("TestCases"); rNum++){
				if(testCase.trim().equals(getCellData("TestCases", 0, rNum).trim())){
					if(getCellData("TestCases", "RunMode", rNum).trim().equalsIgnoreCase("y")){
						String startRowNumber = getCellData("TestCases", "StartRowNumber", rNum).trim();
						if(!isNullOrEmpty(startRowNumber)){
							testCaseStartRowNum = (int) Double.parseDouble(startRowNumber);
						}
						String endRowNumber = getCellData("TestCases", "EndRowNumber", rNum).trim();
						if(!isNullOrEmpty(endRowNumber)){
							testCaseEndRowNum = (int) Double.parseDouble(endRowNumber);
						}
						break;
					}
					break;
				}
			}

			if(testCaseStartRowNum != 0 && testCaseEndRowNum != 0){
				totalRows = testCaseEndRowNum-testCaseStartRowNum+1;
			}

			if(totalRows <= 0) {
				testdata = new Object[totalRows][1];
				logger.info("Skipping the test...Either the test is set to: NO or Invalid Test Data Range Defined......");
				return testdata;
			}
			else {
				testdata = new Object[totalRows][1];
			}
			
			xlSheet = xlWorkBook.getSheet(sheetName);
			xlRow = xlSheet.getRow(0);
			int cols = xlRow.getLastCellNum();
			HashMap<String,String> map=null;

			for(int rNum=0;rNum<totalRows;rNum++,testCaseStartRowNum++){
				map=new HashMap<String,String>();
				for(int cNum=0;cNum<cols;cNum++){
					if(!getCellData(sheetName, cNum, testCaseStartRowNum).equals("")){
						map.put(getCellData(sheetName, cNum, 1).trim(),getCellData(sheetName, cNum, testCaseStartRowNum).trim());
					}
				}
				testdata[rNum][0]=map;
			}
			return testdata;
		}catch(Exception e){
			logger.error("Exception in getting the test data from excel sheet : ", e);
			Object[][] testdata =  new Object[-1][-1];;
			return testdata;
		}
	}



	/*
	 * Method to verify cell value is null or empty
	 */
	private boolean isNullOrEmpty(String cellValue) {
		if (cellValue == null) {
			return true;
		} else if (cellValue.isEmpty() || cellValue.trim().equals("")) {
			return true;
		}
		return false;
	}

}

