package pl.edu.atena.dao;

import lombok.extern.java.Log;
import org.apache.log4j.Logger;
import pl.edu.atena.entities.TestSuite;
import pl.edu.atena.interceptor.CzasTrwaniaMetodyLogger;
import pl.edu.atena.jms.MessageProducerMulti;
import pl.edu.atena.observers.TestSuiteEvent;
import pl.edu.atena.observers.TestSuiteEvent.Typ;
import pl.edu.atena.utilities.InputFileBean;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Stateless
@Log
public class TestSuiteDao {
	@PersistenceContext(unitName = "MultiSpi")
	private EntityManager em;

	@EJB
	private UserDao userDao;

	@EJB
	private TestCaseDao testCaseDao;
	@EJB
	private TestResultDao testResultDao;
	@EJB
	private MessageProducerMulti<TestSuite> producer;

	@Inject
	@TestSuiteEvent(Typ.PRZED)
	private Event<TestSuite> eventPrzed;

	@Inject
	@TestSuiteEvent(Typ.PO)
	private Event<TestSuite> eventPo;

	@Inject
	private Logger loger;

	@Inject
	private InputFileBean inputFile;

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Interceptors(CzasTrwaniaMetodyLogger.class)
	public void save(TestSuite testSuite) throws IOException, JAXBException, MessagingException {

		if (testSuite.getId() == null || testSuite.getId() == 0) {
			eventPrzed.fire(testSuite);
			em.persist(testSuite);
			loger.info("persist");

		} else {
			eventPo.fire(testSuite);
			em.merge(testSuite);
			loger.info("merge");

		}
		;
		loger.info(testSuite);
		// producer.sentMessage(testSuite);
		// producer.sentTopic(testSuite);

	}

	public TestSuite find(Long id) {

		return em.find(TestSuite.class, id);
	}

	public void delete(Long id) {
		TestSuite testSuite = find(id);
		if (testSuite != null) {
			em.remove(testSuite);
		}
	}

	public void delete() {
		List<TestSuite> testSuiteList = em.createQuery("select p from TestSuite p").getResultList();
		testSuiteList.forEach(e -> em.remove(e));

	}

	@SuppressWarnings("unchecked")
	public List<TestSuite> selectAll() {
		Query query = em.createQuery("select p from TestSuite p");
		return query.getResultList();
	}

}
