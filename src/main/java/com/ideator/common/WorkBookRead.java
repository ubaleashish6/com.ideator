package com.ideator.common;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WorkBookRead {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		private final String base_pathMicro = ConfigProperties.getFilePath("excel_file_path");
		private final String pathMicro =ConfigProperties.BASEFOLDER+base_pathMicro;
		private final String base_path = ConfigProperties.getFilePath("crendential_file_path");
		private final String path = ConfigProperties.BASEFOLDER+base_path;
	
		//Write the testcase result in Excel sheet
		public void writeSheetTDD(int resultStatus, String methodName) throws IOException {
			fis = new FileInputStream(pathMicro);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet2 = wb.getSheetAt(2);
			ArrayList<Integer> rowNumList = findRow(methodName);
			if (!(rowNumList.isEmpty())) {
				for (Integer rowNum : rowNumList) {
					if (resultStatus == 1) {
						String result = "Pass";
						sheet2.getRow(rowNum).createCell(5).setCellValue(result);
					}
					if (resultStatus == 2) {
						String result = "Fail";
						sheet2.getRow(rowNum).createCell(5).setCellValue(result);
					}
					if (resultStatus == 3) {
						String result = "Skip";
						sheet2.getRow(rowNum).createCell(5).setCellValue(result);
					}
				}
				fos = new FileOutputStream(pathMicro);
				wb.write(fos);
				wb.close();
			} else {
				System.out.println("No " + methodName + " Found to write Result please,check Test Excel File");
			}
		}
	
		public String[] ReadsheetLogin() throws IOException {
			fis = new FileInputStream(path);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet1 = wb.getSheetAt(0);
			String userName = sheet1.getRow(0).getCell(0).getStringCellValue();
			String passWord = sheet1.getRow(0).getCell(1).getStringCellValue();
			wb.close();
			return new String[] { userName, passWord };
		}
		
	//Read sheet number 2 and return all rows and coloumn
		public String[][] ReadsheetTDD() throws IOException {
			fis = new FileInputStream(pathMicro);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet2 = wb.getSheetAt(2);
			int rowCount = sheet2.getPhysicalNumberOfRows();
			int ColumnCount = sheet2.getRow(0).getPhysicalNumberOfCells();
			int row = 0;
			int column = 0;
			wb.close();
			String data[][] = new String[rowCount][ColumnCount];
			for (row = 1; row < rowCount; row++) {
				for (column = 1; column < ColumnCount; column++) {
	
					try {
						data[row][column] = sheet2.getRow(row).getCell(column).getStringCellValue();
					} catch (Exception e) {
					}
				}
			}
			return data;
		}
	
		//find row range of perticular classname in given excel sheet
		public ArrayList<Integer> findRow(String cellContent) throws IOException {
			fis = new FileInputStream(pathMicro);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet2 = wb.getSheetAt(2);
			ArrayList<Integer> classRowNums = new ArrayList<Integer>();
			for (Row row : sheet2) {
				for (Cell cell : row) {
					if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
						if (cell.getRichStringCellValue().getString().trim().equals(cellContent)) {
							int num = row.getRowNum();
							classRowNums.add(num);
						}
					}
					wb.close();
				}
			}
			return classRowNums;
		}
	
		//return row coloumn count from the excel sheet
		public int[] getRow_Column_Count(int sheetNumber) throws IOException {
			fis = new FileInputStream(pathMicro);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(sheetNumber);
			int rowCount = sheet.getPhysicalNumberOfRows();
			int ColumnCount = sheet.getRow(0).getPhysicalNumberOfCells();
			wb.close();
			return new int[] { rowCount, ColumnCount };
		}
	
	}
