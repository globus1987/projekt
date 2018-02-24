package pl.edu.atena.logger;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;

import lombok.Data;
import pl.edu.atena.enums.MessageCodes;

@Data
@Stateful
public class MessageListBean {
	private List<MessageBean> messageList = new ArrayList<MessageBean>();
	
	
	public void addMessage(MessageCodes code) {
		messageList.add(new MessageBean(code));
	}
	public void addMessage(MessageCodes code,String additionalText) {
		messageList.add(new MessageBean(code,additionalText));
	}
}
