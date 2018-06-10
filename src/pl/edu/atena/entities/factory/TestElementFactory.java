package pl.edu.atena.entities.factory;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;

import pl.edu.atena.entities.TestCase;
import pl.edu.atena.entities.TestResult;
import pl.edu.atena.entities.TestSuite;
import pl.edu.atena.entities.User;

@Stateless
public class TestElementFactory {
	@Produces
	public TestResult getNewTestResult() {
		return new TestResult();
	}

	@Produces
	public TestSuite getNewTestSuite() {
		return new TestSuite();
	}

	@Produces
	public TestCase getNewTestCase() {
		return new TestCase();
	}

	@Produces
	public User getNewUser() {
		return new User();
	}

}
