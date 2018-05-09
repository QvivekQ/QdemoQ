package dataprovider;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.testng.annotations.DataProvider;

public class CutomDataProvider {
	private HSSFWorkbook workbook;

	@DataProvider(name = "empLogin")
	public Object[][] readExcel() throws IOException {
		workbook = new HSSFWorkbook(new POIFSFileSystem(new FileInputStream("/home/qolsys/Desktop/sample.xls")));
		HSSFSheet sheet = workbook.getSheet("sample");
		int totalRows = sheet.getLastRowNum();
		int totalColums = sheet.getRow(0).getPhysicalNumberOfCells();

		// Read data from excel and store the same in the Object Array.
		Object obj[][] = new Object[totalRows][totalColums];
		for (int i = 0; i < totalRows; i++) {
			obj[i][0] = sheet.getRow(i ).getCell(0).toString();
			obj[i][1] = sheet.getRow(i ).getCell(1).toString();
		}

		return obj;
	}

}