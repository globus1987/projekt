package pl.edu.atena.validator;

import java.util.ArrayList;

import javax.inject.Inject;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import lombok.Data;
import pl.edu.atena.enums.MessageCodes;
import pl.edu.atena.logger.MessageListBean;
import pl.edu.atena.utilities.InputFileBean;

@Data
public class InputFileObjectValidatorBean extends InputFileSheetValidatorBean {
	private static int FirstDataRow = 4;
	@Inject
	private static InputFileBean inputFile;

	public static boolean getValidationResult(XSSFSheet sheet, MessageListBean messageListForFile) {
		boolean result = true;
		if (sheet.getPhysicalNumberOfRows() == 0) {
			messageListForFile.addMessage(MessageCodes.error_900001);
			result = false;
		}
		if (!InputFileObjectValidatorBean.checkSheetStructure(sheet, messageListForFile)) {

			result = false;
		}
		;
		if (!InputFileObjectValidatorBean.checkUserData(sheet, messageListForFile)) {

			result = false;
		}
		;

		return result;
	}

	private static boolean checkUserData(XSSFSheet sheet, MessageListBean messageListForFile) {
		// TODO Auto-generated method stub
		return false;
	}

	private static boolean checkSheetStructure(XSSFSheet sheet, MessageListBean messageListForFile) {
		boolean result = true;
		if (sheet.getFirstRowNum() != 0) {
			messageListForFile.addMessage(MessageCodes.error_900008);
			return false;
		}
		if (sheet.getLastRowNum() < 4) {
			messageListForFile.addMessage(MessageCodes.error_900007);
			result = false;
		}
		if (!result) {
			return false;
		}
		short LastAttributeColumn = 0;
		if (sheet.getRow(0) != null) {
			LastAttributeColumn = sheet.getRow(0).getLastCellNum();
		}
		else
		{
			messageListForFile.addMessage(MessageCodes.error_900012);
			return false;
		}
		for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
			XSSFRow actualRow = sheet.getRow(i);
			if (actualRow == null) {
				messageListForFile.addMessage(MessageCodes.error_900010, String.valueOf(i + 1));
				continue;
			}
			// header rows validation
			if (actualRow.getRowNum() < 3) {
				for (int y=0;y<LastAttributeColumn;y++) {
					
				}
			}
			//data rows validation
			if (actualRow.getRowNum() > 3) {
				if (actualRow.getLastCellNum() > LastAttributeColumn) {
					messageListForFile.addMessage(MessageCodes.error_900011,
							"Column " + actualRow.getLastCellNum() + " Row: " + String.valueOf(i + 1));
					result = false;
				}

				XSSFCell iDCell = actualRow.getCell(0, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				XSSFCell NameCell = actualRow.getCell(1, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				XSSFCell validFromCell = actualRow.getCell(1, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				XSSFCell validToCell = actualRow.getCell(1, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				ArrayList<XSSFCell> techColumnList = new ArrayList<XSSFCell>();
				techColumnList.add(iDCell);
				techColumnList.add(NameCell);
				techColumnList.add(validFromCell);
				techColumnList.add(validToCell);

				for (int z = 0; z < techColumnList.size(); z++) {
					if (techColumnList.get(z).getCellTypeEnum() == CellType.BLANK) {
						messageListForFile.addMessage(MessageCodes.error_900009, techColumnList.get(z).getReference());
						result = false;
					}
				}
			}
		}
		return result;
	}

}
