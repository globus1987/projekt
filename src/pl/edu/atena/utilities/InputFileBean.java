package pl.edu.atena.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ejb.Stateful;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import lombok.Data;

@Stateful
@Data
public class InputFileBean {
	private String endpoint;
	private String userFirstname;
	private String userSurname;
	private String email;
	private int requestedCapacity;
	private String language;
	private FileInputStream fileIn;
	private FileOutputStream fileOut;

	public void ReadPropertiesFromFile() {

	}

	public InputFileBean() throws IOException, EncryptedDocumentException, InvalidFormatException {
		this.fileIn = new FileInputStream(new File("D:/TestTool.xlsx"));
		this.fileOut = new FileOutputStream((new File("D:/TestTool2.xlsx")));
		XSSFWorkbook wb = null;
		try {
			XSSFWorkbook wb2 = new XSSFWorkbook(this.fileIn);
			// wb = new XSSFWorkbook("D:\\TestTool.xlsx");
			XSSFSheet sheet = wb2.getSheetAt(0);
			XSSFRow row0 = sheet.getRow(0);
			this.endpoint = row0.getCell(1).getStringCellValue();
			XSSFRow row1 = sheet.getRow(1);
			this.userFirstname = row1.getCell(1).getStringCellValue();
			XSSFRow row2 = sheet.getRow(2);
			this.userSurname = row2.getCell(1).getStringCellValue();
			XSSFRow row3 = sheet.getRow(3);
			this.email = row3.getCell(1).getStringCellValue();
			XSSFRow row4 = sheet.getRow(4);
			this.language = row4.getCell(1).getStringCellValue();
			XSSFRow row5 = sheet.getRow(5);
			this.requestedCapacity = (int) row5.getCell(1).getNumericCellValue();
		} finally {
			if (wb != null)
				wb.close();
			if (fileOut != null)
				fileOut.close();
			if (fileIn != null)
				fileIn.close();
		}
	}

	public InputFileBean(InputStream inputStream)
			throws IOException, EncryptedDocumentException, InvalidFormatException {
		FileInputStream fileIn = null;
		FileOutputStream fileOut = null;
		XSSFWorkbook wb = null;
		try {
			// FileInputStream nowyplik = new FileInputStream("D:/Akademia JAVA/TestTool
			// input.xlsx");
			XSSFWorkbook wb2 = new XSSFWorkbook(inputStream);
			// wb = new XSSFWorkbook("D:\\TestTool.xlsx");
			XSSFSheet sheet = wb2.getSheetAt(0);
			XSSFRow row0 = sheet.getRow(0);
			this.endpoint = row0.getCell(1).getStringCellValue();
			XSSFRow row1 = sheet.getRow(1);
			this.userFirstname = row1.getCell(1).getStringCellValue();
			XSSFRow row2 = sheet.getRow(2);
			this.userSurname = row2.getCell(1).getStringCellValue();
			XSSFRow row3 = sheet.getRow(3);
			this.email = row3.getCell(1).getStringCellValue();
			XSSFRow row4 = sheet.getRow(4);
			this.language = row4.getCell(1).getStringCellValue();
			XSSFRow row5 = sheet.getRow(5);
			this.requestedCapacity = (int) row5.getCell(1).getNumericCellValue();
		} finally {
			if (wb != null)
				wb.close();
			if (fileOut != null)
				fileOut.close();
			if (fileIn != null)
				fileIn.close();
		}
	}

	public XSSFSheet getSheet(String name) throws InvalidFormatException, IOException {

		XSSFWorkbook wb2 = new XSSFWorkbook(new File("D:/TestTool.xlsx"));
		return wb2.getSheet(name);

	}

	public XSSFWorkbook getWorkbook() throws InvalidFormatException, IOException {

		XSSFWorkbook wb2 = new XSSFWorkbook(new File("D:/TestTool.xlsx"));
		return wb2;

	}

	public void closeStreams() throws InvalidFormatException, IOException {

		this.fileOut.close();
		this.fileIn.close();
	}
	
}
