package pl.edu.atena.validator;

import javax.ejb.Stateless;

import lombok.extern.java.Log;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import lombok.Data;
import pl.edu.atena.enums.MessageCodes;
import pl.edu.atena.logger.MessageListBean;


@Data
@Stateless
@Log
public class InputFileSheetValidatorBean implements Validator {
	private boolean validationResult;

	public boolean getValidationResult(XSSFSheet sheet, MessageListBean messageListForFile) {
		if (sheet.getPhysicalNumberOfRows() == 0) {
			messageListForFile.addMessage(MessageCodes.ERROR_900013);
		}
		return true;
	}
}
