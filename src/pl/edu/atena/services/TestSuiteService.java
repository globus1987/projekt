package pl.edu.atena.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;


import pl.edu.atena.dao.TestCaseDao;
import pl.edu.atena.dao.TestResultDao;
import pl.edu.atena.dao.TestSuiteDao;
import pl.edu.atena.dao.UserDao;
import pl.edu.atena.email.EmailBean;
import pl.edu.atena.email.MessageEmailBean;
import pl.edu.atena.entities.TestCase;
import pl.edu.atena.entities.TestResult;
import pl.edu.atena.entities.TestSuite;
import pl.edu.atena.entities.User;
import pl.edu.atena.entities.factory.TestElementFactory;
import pl.edu.atena.utilities.InputFileBean;
import pl.edu.atena.utilities.ObjectConverter;
import pl.edu.atena.utilities.TestowaXLS;
import pl.edu.atena.utilities.XLSFile;

@Path(value = "/testsuite")
public class TestSuiteService {
	private Logger log = Logger.getLogger(TestSuiteService.class);
	@Inject
	private UserDao userDao;
	@Inject
	private TestSuiteDao testSuiteDao;
	@Inject
	private TestCaseDao testCaseDao;
	@Inject
	private TestResultDao testResultDao;

	@Inject
	private InputFileBean inputFile;
	@Inject
	private ObjectConverter objectConvert;

	@Inject
	private EmailBean emailBean;

	@Inject
	private TestElementFactory testFactory;
	@Inject
	RequestBuilderService reqbuilder;
	
	@Context
	UriInfo uriInfo;

	@GET
	@Path(value = "/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTS(@PathParam("id") Long id) {

		return Response.status(200).entity(testSuiteDao.find(id)).build();
	}
	
	@GET
	@Path(value = "/newresults/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getnewresults(@PathParam("id") Long id) throws IOException, JAXBException, MessagingException {
		TestSuite ts = testSuiteDao.find(id);
		ts.getTestcaseList().forEach(tc -> tc.checkTestCaseStatus()
		);
		testSuiteDao.save(ts);
		return Response.status(200).entity(testSuiteDao.find(id)).build();
	}

	@GET
	@Path(value = "/testuj")
	@Produces(MediaType.APPLICATION_JSON)
	public Response testuj() throws EncryptedDocumentException, InvalidFormatException, IOException {

		return Response.status(200).entity(TestowaXLS.testuj()).build();
	}

	@GET
	@Path(value = "/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		List<TestSuite> testsuiteList = testSuiteDao.selectAll();
		return Response.status(200).entity(testsuiteList).build();
	}
	@GET
	@Path(value = "/request/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReqById(@PathParam("id") Long id) {
		
		return Response.status(200).entity(testSuiteDao.find(id).getRequest()).build();
	}
	
	@PUT
	@Path(value = "/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response setRequests() {
		List<TestSuite> testsuiteList = testSuiteDao.selectAll();
		testsuiteList.forEach(ts->{
			try {
				ts.setRequest(reqbuilder.createRequest());
				testSuiteDao.save(ts);
			} catch (InvalidFormatException | MessagingException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		return Response.status(200).entity(testsuiteList).build();
	}

	@POST
	@Path(value = "/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createAll()
			throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException,
			HeuristicMixedException, HeuristicRollbackException, IOException, JAXBException, MessagingException {
		Random generator = new Random();

		TestSuite tsexample = testFactory.getNewTestSuite();

		User user = testFactory.getNewUser();

		TestCase testcase = testFactory.getNewTestCase();
		TestCase testcase2 = testFactory.getNewTestCase();
		testcase.setName("testowy");
		testcase2.setName("testowy2");

		TestResult result = testFactory.getNewTestResult();
		TestResult result2 = testFactory.getNewTestResult();
		TestResult result3 = testFactory.getNewTestResult();

		testCaseDao.save(testcase);
		testCaseDao.save(testcase2);

		testResultDao.save(result);
		testResultDao.save(result2);
		testResultDao.save(result3);

		userDao.save(user);
		testSuiteDao.save(tsexample);
		tsexample.setCreatorUser(user);
		testSuiteDao.save(tsexample);
		userDao.save(user);

		List<TestResult> resultlist = new ArrayList<TestResult>();
		result.setActualValue(String.valueOf(generator.nextInt()));
		result2.setActualValue(String.valueOf(generator.nextInt()));
		result3.setActualValue(String.valueOf(generator.nextInt()));

		resultlist.add(result);
		resultlist.add(result2);
		resultlist.add(result3);

		testcase.setName(String.valueOf(generator.nextInt()));
		testcase.setResultList(resultlist);
		List<TestCase> testcaselist = new ArrayList<TestCase>();
		testcaselist.add(testcase);
		tsexample.setTestcaseList(testcaselist);

		testCaseDao.save(testcase);
		testCaseDao.save(testcase2);
		testResultDao.save(result);
		testSuiteDao.save(tsexample);

		return Response.status(200).entity(tsexample).build();
	}

	@POST
	@Path(value = "/allWithJTA")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createAllWithJTA(@Context HttpHeaders headers)
			throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException,
			HeuristicMixedException, HeuristicRollbackException, IOException, JAXBException, MessagingException {

		UriBuilder uri = uriInfo.getAbsolutePathBuilder();

		TestSuite tsexample = testFactory.getNewTestSuite();
		User user = testFactory.getNewUser();
		TestCase testcase = testFactory.getNewTestCase();
		TestResult result = testFactory.getNewTestResult();
		TestResult result2 = testFactory.getNewTestResult();
		TestResult result3 = testFactory.getNewTestResult();
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
			System.out.println("coœ siê wywali³o");
			System.out.println(e.getCause().toString());
		}
		;
		userDao.updateUser(tsexample.getCreatorUser());
		MessageEmailBean<TestSuite> mess = new MessageEmailBean<>();
		mess.setText(testSuiteDao.find(tsexample.getId()));
		EmailBean.sendGMXText(mess);
		Link link = Link.fromUri(uriInfo.getAbsolutePath()).rel("self").type("UPDATE").build();
		Link link2 = Link.fromUri(uriInfo.getAbsolutePath()).rel("self").type("GET").build();
		Link link3 = Link
				.fromUri("http://localhost:8090/MultiSpi-0.0.1-SNAPSHOT/multiApi/user/byId/"
						+ testSuiteDao.find(tsexample.getId()).getCreatorUser().getId())
				.rel("self").type("GET").title("pobieranie usera").build();
		return Response.ok(testSuiteDao.find(tsexample.getId())).links(link, link2, link3).build();
	}

	@POST
	@Path(value = "/jsonorxml")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response createAllWithJTAInput(TestSuite testsuite, @Context HttpHeaders headers)
			throws IOException, JAXBException, MessagingException {
		String header = headers.getRequestHeader("content-type").get(0);
		if (header.equals("application/json")) {
			objectConvert.saveToJSON(testsuite);
		} else if (header.equals("application/xml")) {
			objectConvert.convertToXML(testsuite);

		}
		testSuiteDao.save(testsuite);

		return Response.status(200).entity(testSuiteDao.find(testsuite.getId())).build();
	}
	

}
