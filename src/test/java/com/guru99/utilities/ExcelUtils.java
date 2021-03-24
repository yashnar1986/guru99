package com.guru99.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 *
 * Utility class to do READ/WRITE operations on Excel files. It can only work
 * with .xlsx (MS Excel 97-2003) files.
 */
public class ExcelUtils {

	private XSSFWorkbook workBook;
	private XSSFSheet sheet;
	private String path;

	public ExcelUtils(String path, String sheetName) {
		this.path = path;
		try {

			FileInputStream excelFile = new FileInputStream(path);
			workBook = new XSSFWorkbook(excelFile);
			sheet = workBook.getSheet(sheetName);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public int rowCount() {
		return sheet.getPhysicalNumberOfRows();
	}

	public List<String> getColumnNames() {
		List<String> columns = new ArrayList<>();
		for (Cell cell : sheet.getRow(0)) {
			columns.add(cell.toString());
		}
		return columns;
	}

	public List<Map<String, String>> getDataAsList() {

		List<String> columnNames = getColumnNames(); // gets the names of columns

		List<Map<String, String>> data = new ArrayList<>();

		for (int i = 1; i < rowCount(); i++) {
			XSSFRow row = sheet.getRow(i); // Gets each row

			Map<String, String> rowMap = new HashMap<String, String>();
			// We are mapping each column name with each cell in a row
			for (Cell cell : row) {
				int columnIndex = cell.getColumnIndex();
				rowMap.put(columnNames.get(columnIndex), cell.toString());
			}
			data.add(rowMap);
		}
		return data;
	}

	public void setCellData(String value, int rowNum, int colNum) {
		XSSFCell cell;
		XSSFRow row;
		try {
			row = sheet.getRow(rowNum);
			cell = row.getCell(colNum);
			if (cell == null) {
				cell = row.createCell(colNum);
				cell.setCellValue(value);
			} else {
				cell.setCellValue(value);
			}
			FileOutputStream outputFile = new FileOutputStream(path);
			workBook.write(outputFile);
			outputFile.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setCellData(String value, String columnName, int row) {
		int column = getColumnNames().indexOf(columnName);
		setCellData(value, row, column);
	}

}