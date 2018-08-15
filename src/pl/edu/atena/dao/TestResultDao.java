package pl.edu.atena.dao;

import pl.edu.atena.entities.TestResult;
import pl.edu.atena.jms.MessageProducerMulti;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class TestResultDao {
	@PersistenceContext(unitName = "MultiSpi")
	private EntityManager em;
	@EJB
	private MessageProducerMulti<TestResult> producer;
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void save(TestResult testresult) {
		if (testresult.getId() == null || testresult.getId() == 0) {
			em.persist(testresult);
		} else {
			em.merge(testresult);
		}
		;
	}

	public TestResult find(Long id) {
		return em.find(TestResult.class, id);
	}

	public void delete(Long id) {
		TestResult testresult = find(id);
		if (testresult != null) {
			em.remove(testresult);
		}
	}
	public List<TestResult> selectAll(){
		Query query = em.createQuery("select p from TestResult p");
		return query.getResultList();
	}
}
