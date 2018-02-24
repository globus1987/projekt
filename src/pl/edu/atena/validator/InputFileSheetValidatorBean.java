package pl.edu.atena.validator;

import javax.ejb.Stateless;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import lombok.Data;

@Data
@Stateless
public abstract class InputFileSheetValidatorBean implements Validator{
	private boolean validationResult;

	public static boolean getValidationResult(XSSFSheet sheet) {
		// TODO Auto-generated method stub
		return true;
	}
}
