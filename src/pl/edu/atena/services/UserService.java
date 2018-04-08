package pl.edu.atena.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
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
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

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
import pl.edu.atena.enums.UserType;
import pl.edu.atena.utilities.InputFileBean;
import pl.edu.atena.utilities.ObjectConverter;
import pl.edu.atena.utilities.TestowaXLS;

@Path(value = "/user")
public class UserService {
	private Logger log = Logger.getLogger(UserService.class);
	@EJB
	private UserDao userDao;
	@EJB
	private TestSuiteDao testSuiteDao;
	@EJB
	private TestCaseDao testCaseDao;
	@EJB
	private TestResultDao testResultDao;

	@EJB
	private InputFileBean inputFile;
	@Inject
	@Dependent
	private ObjectConverter objectConvert;
	
	@Inject
	private EmailBean emailBean;
	
	@POST
	@Path(value = "/SendEmail")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response sendEmail(MessageEmailBean message) throws MessagingException {
		emailBean.sendGMX(message);
		return Response.status(200).build();
	}
	
	@POST
	@Path(value = "/addUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(User newUser) {
		userDao.save(newUser);
		return Response.status(200).entity(newUser).build();
	}

	@POST
	@Path(value = "/addUserByXLSX")
	@Produces(MediaType.APPLICATION_JSON)
	public Response create() {
		User newUser = new User();
		newUser.setEmailAddress(inputFile.getEmail());
		newUser.setFirstName(inputFile.getUserFirstname());
		newUser.setSurname(inputFile.getUserSurname());
		newUser.setUserType(UserType.USER);

		userDao.save(newUser);
		return Response.status(200).entity(newUser).build();
	}

	@POST
	@Path(value = "/addUserByXLSX2")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("multipart/form-data")
	public Response create2(MultipartFormDataInput input)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		User newUser = new User();
		String fileName = "";

		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		List<InputPart> inputParts = uploadForm.get("uploadedFile");
		for (InputPart inputPart : inputParts) {
			InputStream inputStream = inputPart.getBody(InputStream.class, null);
			InputFileBean inputFile = new InputFileBean(inputStream);
			newUser.setEmailAddress(inputFile.getEmail());
			newUser.setFirstName(inputFile.getUserFirstname());
			newUser.setSurname(inputFile.getUserSurname());
			newUser.setUserType(UserType.USER);
			userDao.save(newUser);
		}
		return Response.status(200).entity(newUser).build();
	}

	@GET
	@Path(value = "/User")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser() {

		return Response.status(200).entity("Dupa").build();
	}

	@GET
	@Path(value = "/byId/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getU1(@PathParam("id") Long id) {
		User user = userDao.find(id);
		return Response.status(200).entity(user).build();
	}

	@GET
	@Path(value = "/byUserType/{usertype}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByUserType(@PathParam("usertype") UserType usertype) {
		List<User> userList = userDao.findByUser(usertype);

		return Response.status(200).entity(userList).build();
	}

	@GET
	@Path(value = "/testuj")
	@Produces(MediaType.APPLICATION_JSON)
	public Response testuj() throws EncryptedDocumentException, InvalidFormatException, IOException {

		return Response.status(200).entity(TestowaXLS.testuj()).build();
	}

	@POST
	@Path(value = "/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createAll() throws NotSupportedException, SystemException, SecurityException, IllegalStateException,
			RollbackException, HeuristicMixedException, HeuristicRollbackException, IOException, JAXBException {
		Random generator = new Random();

		TestSuite tsexample = new TestSuite();

		User user = new User();

		TestCase testcase = new TestCase();
		TestCase testcase2 = new TestCase();
		testcase.setName("testowy");
		testcase2.setName("testowy2");

		TestResult result = new TestResult();
		TestResult result2 = new TestResult();
		TestResult result3 = new TestResult();

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
	@Context
	UriInfo uriInfo;
	@POST
	@Path(value = "/allWithJTA")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createAllWithJTA(@Context HttpHeaders headers) throws NotSupportedException, SystemException, SecurityException,
			IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, IOException, JAXBException {
		
		
		UriBuilder uri = uriInfo.getAbsolutePathBuilder();
	   
		TestSuite tsexample = new TestSuite();
		User user = new User();
		TestCase testcase = new TestCase();
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
			System.out.println("coœ siê wywali³o");
			System.out.println(e.getCause().toString());
		}
		;
		 Link link = Link.fromUri(uriInfo.getAbsolutePath()).rel("self").type("UPDATE").build();
		    Link link2 = Link.fromUri(uriInfo.getAbsolutePath()).rel("self").type("GET").build();
		    Link link3 = Link.fromUri("http://localhost:8090/MultiSpi-0.0.1-SNAPSHOT/multiApi/user/byId/"+testSuiteDao.find(tsexample.getId()).getCreatorUser().getId()).rel("self").type("GET").title("pobieranie usera").build();

		return Response.ok(testSuiteDao.find(tsexample.getId())).links(link,link2,link3).build();
	}
	@POST
	@Path(value = "/allWithJTA/input")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response createAllWithJTAInput(TestSuite testsuite,@Context HttpHeaders headers) throws IOException, JAXBException  {
	String header = headers.getRequestHeader("content-type").get(0);
		if (header.equals("application/json"))
		{
			objectConvert.saveToJSON(testsuite);
		}
		else if (header.equals("application/xml")) {
			objectConvert.convertToXML(testsuite);

		}
		testSuiteDao.save(testsuite);
		
		
		return Response.status(200).entity(testSuiteDao.find(testsuite.getId())).build();
	}
}
