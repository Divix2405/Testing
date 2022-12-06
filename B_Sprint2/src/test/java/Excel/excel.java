package Excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;


public class excel{
	
		static XSSFWorkbook workbook;
		static XSSFSheet sheet;
		static XSSFRow row;
		static XSSFCell cell;
		
		public static Object[][] readData() throws IOException {
			
			String excelFilePath = System.getProperty("user.dir")+"\\Files\\data.xlsx";
			File excelFile = new File(excelFilePath);
			FileInputStream fis = new FileInputStream(excelFile);
			
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet("one");
			
			int rows = sheet.getLastRowNum(); 
			
			int cols = sheet.getRow(1).getLastCellNum(); 
			
			
			Object[][] obj = new Object[rows][cols];
			
			for(int r=1;r<=rows;r++) {
				
				for(int c=0;c<cols;c++) {
					
					obj[r-1][c] = getCellData(c,r);		
					
				}
				
			}
			
			return obj;
			
		}
	
	public static String getCellData(int colNum,int rowNum) {
		
		row = sheet.getRow(rowNum);
		cell = row.getCell(colNum);
		
		CellType cellType = cell.getCellType();
		
		switch(cellType) {
		
		case STRING: 
			 return cell.getStringCellValue();
			 
		case NUMERIC:
			return Integer.toString((int)cell.getNumericCellValue());
			
				
		}
		
		return null;
		

	}
}
	
	/*	
	String path=System.getProperty("user.dir")+"\\Files\\data.xlsx";
	
	File ef=new File(path);
	
	FileInputStream fis=new FileInputStream(ef);
	
	XSSFWorkbook wb=new XSSFWorkbook(fis);
	
	XSSFSheet sheet=wb.getSheet("one");
	
	Iterator<Row> itrRow=sheet.iterator();
	while(itrRow.hasNext()) {
		Row row=itrRow.next();
		Iterator<Cell> itrCell = row.iterator();
		while(itrCell.hasNext()) {
			Cell cell = itrCell.next();
			CellType celltype = cell.getCellType();
			switch(celltype) {
			case STRING:
				System.out.println(cell.getStringCellValue());
				break;
			case NUMERIC:
				System.out.println(cell.getNumericCellValue());
				break;
			
			}
			System.out.println(" ");
			
		}
		System.out.println(" ");
	}
	wb.close();

   }*/

