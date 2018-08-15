package pl.edu.atena.utilities;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import pl.edu.atena.logger.MessageListBean;
import pl.edu.atena.validator.InputFileSetupValidatorBean;

import javax.inject.Inject;
import java.io.IOException;

public abstract class TestowaXLS {
	@Inject
	private static MessageListBean messageListForFile = new MessageListBean();
	@Inject
	private InputFileSetupValidatorBean inputFileSetupValidatorBean;

	public MessageListBean testuj() throws InvalidFormatException, IOException {

		InputFileBean input = new InputFileBean();
		XSSFSheet sheet = input.getSheet("Setup");
		boolean validationResult = inputFileSetupValidatorBean.getValidationResult(sheet, messageListForFile);
		System.out.println(messageListForFile);

		System.out.println(validationResult);
		input.closeStreams();
		return messageListForFile;


	}

}
