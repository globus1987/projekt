package pl.edu.atena.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.log4j.Logger;
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/topic/ProjektT"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })

public class TopicReader<T> implements MessageListener {
	Logger log = Logger.getLogger("messageconsumer");

	public TopicReader() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message message) {
		try {
			ObjectMessage objMessage = (ObjectMessage) message;
			T value = (T) objMessage.getObject();
			log.info("wpad³ topic g³ówny "+value.getClass().getSimpleName()+": " + value);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
