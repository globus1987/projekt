package pl.edu.atena.logger;
import lombok.Data;
import pl.edu.atena.enums.MessageCodes;

import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.List;

@Data
@Stateful
public class MessageListBean {
	private List<MessageBean> messageList = new ArrayList<>();
	
	
	public void addMessage(MessageCodes code) {
		messageList.add(new MessageBean(code));
	}
	public void addMessage(MessageCodes code,String additionalText) {
		messageList.add(new MessageBean(code,additionalText));
	}
}
