package pl.edu.atena.enums;

public enum MessageCodes {
	ERROR_900001("Setup sheet is empty."),
	ERROR_900002("There are missing technical fields in setup sheet."),
	ERROR_900003("There are missing user fields in setup sheet."),
	ERROR_900004("Missing value for field: "),
	ERROR_900005("Wrong structure of setup sheet."),
	ERROR_900006("Too many cells in row:"),
	ERROR_900007("Too many rows."),
	ERROR_900008("Setup data is not starting in first row."),
	ERROR_900009("There is no value in cell:"),
	ERROR_900010("Missing data in row:"),
	ERROR_900011("Attribute value without a name in first row: "),
	ERROR_900012("No attribute header row in object sheet."),
	ERROR_900013("Sheet is empty.");


	
	private final String text;
	private MessageCodes(String s) {
		text = s;
    }
	public String getMessageText() {
	       return this.text;
	    }}
