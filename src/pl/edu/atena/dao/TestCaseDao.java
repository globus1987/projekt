package pl.edu.atena.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.edu.atena.entities.TestCase;
import pl.edu.atena.jms.MessageProducerMulti;

@Stateless
public class TestCaseDao {
	@PersistenceContext(unitName = "MultiSpi")
	private EntityManager em;
	@EJB
	private MessageProducerMulti<TestCase> producer;
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void save(TestCase testCase) {
		if (testCase.getId() == null || testCase.getId()==0) {
			em.persist(testCase);
		} else {
			em.merge(testCase);
		};
	}

	public TestCase find(Long id) {
		return em.find(TestCase.class, id);
	}

	public TestCase update(TestCase testCase) {
		System.out.println(em.contains(testCase));
		return em.merge(testCase);
	}

	public void delete(Long id) {
		TestCase testCase = find(id);
		if (testCase != null) {
			em.remove(testCase);
		}
	}
	public List<TestCase> selectAll(){
		Query query = em.createQuery("select p from TestCase p");
		return query.getResultList();
	}
}
