package pl.edu.atena.logger;

import lombok.Data;
import pl.edu.atena.enums.MessageCodes;

@Data
public class MessageBean {
	private MessageCodes code;
	private String text;
	
	public MessageBean(MessageCodes code) {
		this.code=code;
		this.text=code.getMessageText();
	}
	public MessageBean(MessageCodes code,String additionalText) {
		this.code=code;
		this.text=code.getMessageText()+additionalText;
	}
}
