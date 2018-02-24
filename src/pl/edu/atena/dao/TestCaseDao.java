package pl.edu.atena.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.edu.atena.entities.TestCase;
import pl.edu.atena.entities.TestResult;

@Stateless
public class TestCaseDao {
	@PersistenceContext(unitName = "MultiSpi")
	private EntityManager em;

	public void save(TestCase testCase) {
		if (testCase.getId() == null || testCase.getId()==0) {
			em.persist(testCase);
		} else {
			em.merge(testCase);
		}
		;
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
}
