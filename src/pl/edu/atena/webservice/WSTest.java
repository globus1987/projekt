package pl.edu.atena.webservice;

import pl.edu.atena.dao.TestSuiteDao;
import pl.edu.atena.dao.UserDao;
import pl.edu.atena.entities.TestCase;
import pl.edu.atena.entities.TestResult;
import pl.edu.atena.entities.TestSuite;
import pl.edu.atena.entities.User;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebParam.Mode;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.mail.MessagingException;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.ws.Holder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@WebService(targetNamespace = "http://pl.edu.atena")
public class WSTest {

	@EJB
	private UserDao userDao;

	@EJB
	private TestSuiteDao testSuiteDao;

	public String testowy(String imie) {
		return "Witaj " + imie;
	}

	public User podajUsera(Long id) {
		return userDao.find(id);
	}

	@WebMethod(operationName = "SzukanieTestSuite")
	@WebResult(name = "Wynik", targetNamespace = "http://pl.edu.atena")
	public TestSuite podajPolise(@XmlElement(required = true) @WebParam(name = "NrTestSuita", mode = Mode.IN) Long id,
			@WebParam(name = "sum", mode = WebParam.Mode.OUT) Holder<Integer> sum) {
		sum.value = 10;
		return testSuiteDao.find(id);
	}

	@WebMethod(operationName = "WczytajTestSuite")
	@WebResult(name = "Wynik", targetNamespace = "http://pl.edu.atena")
	public TestSuite WczytajTestSuite() throws IOException, JAXBException, MessagingException {
		Random generator = new Random();
		TestSuite tsexample = new TestSuite();
		User user = new User();
		TestCase testcase = new TestCase();
		TestCase testcase2 = new TestCase();
		TestResult result = new TestResult();
		TestResult result2 = new TestResult();
		TestResult result3 = new TestResult();
		tsexample.setCreatorUser(user);
		List<TestResult> resultlist = new ArrayList<TestResult>();
		resultlist.add(result);
		resultlist.add(result2);
		resultlist.add(result3);
		testcase.setResultList(resultlist);
		List<TestCase> testcaselist = new ArrayList<TestCase>();
		testcaselist.add(testcase);
		tsexample.setTestcaseList(testcaselist);

		testSuiteDao.save(tsexample);

		try {
			result.setActualValue(result.getExpectedValue());
			result.setValueComparison();
			testSuiteDao.save(tsexample);

		} catch (Exception e) {
			System.out.println("cos sie wywalilo");
			System.out.println(e.getCause().toString());
		}
		return tsexample;
	}

	@WebMethod(operationName = "DodajTestSuite")
	@WebResult(name = "Wynik", targetNamespace = "http://pl.edu.atena")
	public TestSuite dodajTestSuite(@WebParam(name = "TestSuite") @XmlElement(required = true) TestSuite ts) throws IOException, JAXBException, MessagingException {
		testSuiteDao.save(ts);
		return ts;
	}

}
