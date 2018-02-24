package pl.edu.atena.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.edu.atena.entities.TestResult;

@Stateless
public class TestResultDao {
	@PersistenceContext(unitName = "MultiSpi")
	private EntityManager em;

	public void save(TestResult testresult) {
		if (testresult.getId() == null ||testresult.getId() ==0 ) {
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
}
