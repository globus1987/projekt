package pl.edu.atena.jms;

import org.apache.log4j.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/topic/ProjektT"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })

public class ReaderSecond<T> implements MessageListener {
	Logger log = Logger.getLogger("messageconsumer");

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message message) {
		try {
			ObjectMessage objMessage = (ObjectMessage) message;
			T value = (T) objMessage.getObject();
			log.info("wpadl topic " + value.getClass().getSimpleName() + ": " + value);
		} catch (JMSException e) {
			log.error("problem z odebraniem topica");
		}
	}
}
