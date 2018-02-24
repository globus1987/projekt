package pl.edu.atena.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.edu.atena.entities.TestSuite;

@Stateless
public class TestSuiteDao {
	@PersistenceContext(unitName = "MultiSpi")
	private EntityManager em;

	public void save(TestSuite testSuite) {
		if (testSuite.getId() == null || testSuite.getId() == 0) {
			em.persist(testSuite);
		} else {
			em.merge(testSuite);
		}
		;
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
}
