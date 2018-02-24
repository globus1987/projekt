package pl.edu.atena.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.jboss.logging.Logger;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import pl.edu.atena.dao.TestCaseDao;
import pl.edu.atena.dao.TestResultDao;
import pl.edu.atena.dao.TestSuiteDao;
import pl.edu.atena.dao.UserDao;
import pl.edu.atena.entities.TestCase;
import pl.edu.atena.entities.TestResult;
import pl.edu.atena.entities.TestSuite;
import pl.edu.atena.entities.User;
import pl.edu.atena.enums.UserType;
import pl.edu.atena.utilities.InputFileBean;
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
	public Response createAll() {
		Random generator = new Random();

		TestSuite tsexample = new TestSuite();
		User user = new User();
		TestCase testcase = new TestCase();
		testcase.setName("testowy");
		TestResult result = new TestResult();

		testCaseDao.save(testcase);
		testResultDao.save(result);
		userDao.save(user);
		testSuiteDao.save(tsexample);
		tsexample.setCreatorUser(user);
		testSuiteDao.save(tsexample);
		userDao.save(user);

		List<TestResult> resultlist = new ArrayList<TestResult>();
		resultlist.add(result);
		testcase.setName(String.valueOf(generator.nextInt()));
		testcase.setResultList(resultlist);
		List<TestCase> testcaselist = new ArrayList<TestCase>();
		testcaselist.add(testcase);
		tsexample.setTestcaseList(testcaselist);

		testCaseDao.save(testcase);
		testResultDao.save(result);
		testSuiteDao.save(tsexample);

		System.out.println(result);
		System.out.println(testcase);
		System.out.println(user);
		System.out.println(tsexample);

		return Response.status(200).entity(tsexample).build();
	}
}
