package pl.edu.atena.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.log4j.Logger;

/**
 * Message-Driven Bean implementation class for: MessageConsumer
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/ProjektQ"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class MessageConsumer<T> implements MessageListener {

	/**
	 * Default constructor.
	 */
	Logger log = Logger.getLogger("messageconsumer");

	public MessageConsumer() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message message) {
		try {
			ObjectMessage objMessage = (ObjectMessage) message;
			T value = (T) objMessage.getObject();
			log.info("wpad³ "+value.getClass().getSimpleName()+": " + value);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
