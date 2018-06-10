package pl.edu.atena.utilities;

import java.io.IOException;

import javax.inject.Inject;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import pl.edu.atena.logger.MessageListBean;
import pl.edu.atena.validator.InputFileSetupValidatorBean;

public abstract class TestowaXLS {
	@Inject
	private static MessageListBean messageListForFile = new MessageListBean();
	public static MessageListBean testuj() throws EncryptedDocumentException, InvalidFormatException, IOException {
		InputFileBean input = new InputFileBean();
		XSSFSheet sheet = input.getSheet("Setup");
		boolean validationResult = InputFileSetupValidatorBean.getValidationResult(sheet,messageListForFile);
		System.out.println(messageListForFile);

		System.out.println(validationResult);
		input.closeStreams();
		return messageListForFile;
	
 
	}

}
