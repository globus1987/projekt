package pl.edu.atena.utilities;

import lombok.Data;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.ejb.Stateful;
import java.io.*;
import java.util.Objects;

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

    public InputFileBean() throws IOException, InvalidFormatException {
		this.fileIn = new FileInputStream(new File("D:/TestTool.xlsx"));
		this.fileOut = new FileOutputStream((new File("D:/TestTool2.xlsx")));
		XSSFWorkbook wb = null;
		try {
            try (XSSFWorkbook wb2 = new XSSFWorkbook(this.fileIn)) {
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
            }
		} finally {
            Objects.requireNonNull(wb).close();
				fileOut.close();
				fileIn.close();
		}
	}

    public InputFileBean(String filename) throws IOException {
		this.fileIn = new FileInputStream(new File(filename));
		this.fileOut = new FileOutputStream((new File(filename)));
		XSSFWorkbook wb = null;
		try {
            try (XSSFWorkbook wb2 = new XSSFWorkbook(this.fileIn)) {
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
            }
		} finally {
            Objects.requireNonNull(wb).close();
				fileOut.close();
				fileIn.close();
		}
	}

	public InputFileBean(InputStream inputStream)
            throws IOException {

        try (XSSFWorkbook wb2 = new XSSFWorkbook(inputStream)) {
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
		}

	}


	public XSSFSheet getSheet(String name) throws InvalidFormatException, IOException {

        try (XSSFWorkbook wb2 = new XSSFWorkbook(new File("D:/TestTool.xlsx"))) {
            return wb2.getSheet(name);
        }

	}

	public XSSFWorkbook getWorkbook() throws InvalidFormatException, IOException {

        try (XSSFWorkbook wb2 = new XSSFWorkbook(new File("D:/TestTool.xlsx"))) {
            return wb2;
        }

	}

    public void closeStreams() throws IOException {

		this.fileOut.close();
		this.fileIn.close();
	}
	
}
