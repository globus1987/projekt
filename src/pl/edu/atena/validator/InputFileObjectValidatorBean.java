package pl.edu.atena.validator;

import lombok.Data;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import pl.edu.atena.enums.MessageCodes;
import pl.edu.atena.logger.MessageListBean;
import pl.edu.atena.utilities.InputFileBean;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Data
@Stateless
public class InputFileObjectValidatorBean extends InputFileSheetValidatorBean {
	private static int firstDataRow = 4;
	@Inject
	private InputFileBean inputFile;

	public boolean getValidationResult(XSSFSheet sheet, MessageListBean messageListForFile) {
		boolean result = true;
		if (sheet.getPhysicalNumberOfRows() == 0) {
			messageListForFile.addMessage(MessageCodes.ERROR_900001);
			result = false;
		}
		if (!InputFileObjectValidatorBean.checkSheetStructure(sheet, messageListForFile)) {

			result = false;
		}
		if (!InputFileObjectValidatorBean.checkUserData(sheet, messageListForFile)) {

			result = false;
		}
		return result;
	}

	private static boolean checkUserData(XSSFSheet sheet, MessageListBean messageListForFile) {
		return false;
	}

	private static boolean checkSheetStructure(XSSFSheet sheet, MessageListBean messageListForFile) {
		boolean result = true;
		if (sheet.getFirstRowNum() != 0) {
			messageListForFile.addMessage(MessageCodes.ERROR_900008);
			return false;
		}
		if (sheet.getLastRowNum() < 4) {
			messageListForFile.addMessage(MessageCodes.ERROR_900007);
			result = false;
		}
		if (!result) {
			return false;
		}
		short lastAttributeColumn = 0;
		if (sheet.getRow(0) != null) {
			lastAttributeColumn = sheet.getRow(0).getLastCellNum();
		}
		else
		{
			messageListForFile.addMessage(MessageCodes.ERROR_900012);
			return false;
		}
		for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
			XSSFRow actualRow = sheet.getRow(i);
			if (actualRow == null) {
				messageListForFile.addMessage(MessageCodes.ERROR_900010, String.valueOf(i + 1));
				continue;
			}
			// header rows validation
			if (actualRow.getRowNum() < 3) {
				for (int y = 0; y < lastAttributeColumn; y++) {
					
				}
			}
			//data rows validation
			if (actualRow.getRowNum() > 3) {
				if (actualRow.getLastCellNum() > lastAttributeColumn) {
					messageListForFile.addMessage(MessageCodes.ERROR_900011,
							String.format("Column %s Row: %s", actualRow.getLastCellNum(), String.valueOf(i + 1)));
					result = false;
				}

				XSSFCell iDCell = actualRow.getCell(0, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				XSSFCell nameCell = actualRow.getCell(1, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				XSSFCell validFromCell = actualRow.getCell(1, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				XSSFCell validToCell = actualRow.getCell(1, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				List<XSSFCell> techColumnList = new ArrayList<>();
				techColumnList.add(iDCell);
				techColumnList.add(nameCell);
				techColumnList.add(validFromCell);
				techColumnList.add(validToCell);

				for (int z = 0; z < techColumnList.size(); z++) {
					if (techColumnList.get(z).getCellTypeEnum() == CellType.BLANK) {
						messageListForFile.addMessage(MessageCodes.ERROR_900009, techColumnList.get(z).getReference());
						result = false;
					}
				}
			}
		}
		return result;
	}

}
