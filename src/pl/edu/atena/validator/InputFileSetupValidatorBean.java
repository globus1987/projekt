package pl.edu.atena.validator;

import lombok.Data;
import lombok.extern.java.Log;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import pl.edu.atena.enums.MessageCodes;
import pl.edu.atena.logger.MessageListBean;
import pl.edu.atena.utilities.InputFileBean;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
@Log
public abstract class InputFileSetupValidatorBean extends InputFileSheetValidatorBean {
	
	@Inject
	private static InputFileBean inputFile;

	public boolean getValidationResult(XSSFSheet sheet, MessageListBean messageListForFile) {
		boolean result = true;
		if (sheet.getPhysicalNumberOfRows() == 0) {
			messageListForFile.addMessage(MessageCodes.ERROR_900001);
			result = false;
		}
		if (!InputFileSetupValidatorBean.checkSheetStructure(sheet, messageListForFile)) {

			result = false;
		}
		if (!InputFileSetupValidatorBean.checkUserData(sheet, messageListForFile)) {

			result = false;
		}
		if (!InputFileSetupValidatorBean.checkTechnicalFields(sheet, messageListForFile)) {

			result = false;
		}
		return result;
	}

	private static boolean checkSheetStructure(XSSFSheet sheet, MessageListBean messageListForFile) {

		boolean result = true;
		if (sheet.getFirstRowNum() != 0) {
			messageListForFile.addMessage(MessageCodes.ERROR_900008);
			result = false;
		}

		if (sheet.getLastRowNum() != 5) {
			messageListForFile.addMessage(MessageCodes.ERROR_900007);
			result = false;
		}

		for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
			XSSFRow actualRow = sheet.getRow(i);
			if (actualRow == null) {
				messageListForFile.addMessage(MessageCodes.ERROR_900010, String.valueOf(i + 1));
				continue;
			}
			if (actualRow.getPhysicalNumberOfCells() > 2) {
				messageListForFile.addMessage(MessageCodes.ERROR_900006, String.valueOf(actualRow.getRowNum()));
				result = false;
			}
			XSSFCell firstCell = actualRow.getCell(0, MissingCellPolicy.CREATE_NULL_AS_BLANK);
			XSSFCell secondCell = actualRow.getCell(1, MissingCellPolicy.CREATE_NULL_AS_BLANK);

			if (firstCell.getCellTypeEnum() == CellType.BLANK) {
				messageListForFile.addMessage(MessageCodes.ERROR_900009, firstCell.getReference());
				result = false;
			}
			if (secondCell.getCellTypeEnum() == CellType.BLANK) {
				messageListForFile.addMessage(MessageCodes.ERROR_900009, secondCell.getReference());
				result = false;
			}
		}
		return result;
	}

	private static List<String> getRowHeaderList(XSSFSheet sheet) {
		List<String> headerList = new ArrayList<>();
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {

			XSSFRow actualRow = (XSSFRow) rowIterator.next();
			headerList.add(actualRow.getCell(0).toString());
		}
		return headerList;
	}

	private static boolean checkUserData(XSSFSheet sheet, MessageListBean messageListForFile) {
		List<String> rowList = InputFileSetupValidatorBean.getRowHeaderList(sheet);
		List<String> userDataFields = new ArrayList<>();
		userDataFields.add("User Firstname:");
		userDataFields.add("User Surname:");
		userDataFields.add("User email:");
		if (!rowList.containsAll(userDataFields)) {
			messageListForFile.addMessage(MessageCodes.ERROR_900003);
			return false;
		}
		int index = rowList.indexOf("User Firstname:");
		if (sheet.getRow(index).getCell(1).getStringCellValue().isEmpty()) {
			messageListForFile.addMessage(MessageCodes.ERROR_900004, "User Firstname");
			return false;
		}
		index = rowList.indexOf("User Surname:");
		if (sheet.getRow(index).getCell(1).getStringCellValue().isEmpty()) {
			messageListForFile.addMessage(MessageCodes.ERROR_900004, "User Surname");
			return false;
		}
		index = rowList.indexOf("User email:");
		if (sheet.getRow(index).getCell(1).getStringCellValue().isEmpty()) {
			messageListForFile.addMessage(MessageCodes.ERROR_900004, "User email");
			return false;
		}
		return true;
	}

	private static boolean checkTechnicalFields(XSSFSheet sheet, MessageListBean messageListForFile) {
		List<String> rowList = InputFileSetupValidatorBean.getRowHeaderList(sheet);
		List<String> technicalFields = new ArrayList<>();
		technicalFields.add("Language:");
		technicalFields.add("Request Capacity:");
		technicalFields.add("Endpoint:");

		if (!rowList.containsAll(technicalFields)) {
			messageListForFile.addMessage(MessageCodes.ERROR_900002);
			return false;
		}
		int index = rowList.indexOf("Language:");
		if (sheet.getRow(index).getCell(1, MissingCellPolicy.CREATE_NULL_AS_BLANK)
				.getCellTypeEnum() == CellType.BLANK) {
			messageListForFile.addMessage(MessageCodes.ERROR_900004, "Language");
			return false;
		}
		index = rowList.indexOf("Request Capacity:");
		if (sheet.getRow(index).getCell(1, MissingCellPolicy.CREATE_NULL_AS_BLANK)
				.getCellTypeEnum() == CellType.BLANK) {
			messageListForFile.addMessage(MessageCodes.ERROR_900004, "Request Capacity");
			return false;
		}
		index = rowList.indexOf("Endpoint:");
		if (sheet.getRow(index).getCell(1, MissingCellPolicy.CREATE_NULL_AS_BLANK)
				.getCellTypeEnum() == CellType.BLANK) {
			messageListForFile.addMessage(MessageCodes.ERROR_900004, "Endpoint");
			return false;
		}
		return true;
	}

}
