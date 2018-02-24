package pl.edu.atena.validator;

import javax.ejb.EJB;

public class InputFileValidatorBean {
	
	@EJB
	private InputFileSetupValidatorBean setupValidator;
	
	@EJB
	private InputFileObjectValidatorBean objectValidator;
	
	@EJB
	private InputFileTestcaseValidatorBean testcaseValidator;
	
}
