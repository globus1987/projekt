package pl.edu.atena.jms;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.log4j.Logger;
@Stateless
public class MessageProducerMulti<T> {
	Logger log = Logger.getLogger("log_producer");
	
	@Resource(mappedName = "java:/JmsXA")
	private ConnectionFactory connectionFactory;
	@Resource(lookup = "java:/jms/queue/ProjektQ")
	private Queue queue;
	
	@Resource(lookup = "java:/jms/topic/ProjektT")
	private Topic topic;
	public void sentMessage(T value) {
		try {
			Connection connection = null;
			MessageProducer publisher = null;
			Session session = null;
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			publisher=session.createProducer(queue);
			connection.start();
			ObjectMessage message = session.createObjectMessage();
			message.setObject((Serializable) value);

			publisher.send(message);
			log.info("wys³ano message");
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	public void sentTopic(T value) {
		try {
			Connection connection = null;
			MessageProducer publisher = null;
			Session session = null;
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			publisher=session.createProducer(topic);
			connection.start();
			ObjectMessage message = session.createObjectMessage();
			message.setObject((Serializable) value);

			publisher.send(message);
			log.info("wys³ano topic");
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
