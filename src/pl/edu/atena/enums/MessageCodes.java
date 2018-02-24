package pl.edu.atena.enums;

public enum MessageCodes {
	error_900001 ("Setup sheet is empty."),
	error_900002 ("There are missing technical fields in setup sheet."),
	error_900003 ("There are missing user fields in setup sheet."),
	error_900004 ("Missing value for field: "),
	error_900005("Wrong structure of setup sheet."),
	error_900006("Too many cells in row:"),
	error_900007("Too many rows."),
	error_900008("Setup data is not starting in first row."),
	error_900009("There is no value in cell:"),
	error_900010("Missing data in row:");
	

	
	private final String text;
	private MessageCodes(String s) {
		text = s;
    }
	public String getMessageText() {
	       return this.text;
	    }}
