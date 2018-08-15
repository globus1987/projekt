package pl.edu.atena.services;

import pl.edu.atena.dao.TestCaseDao;
import pl.edu.atena.dao.TestResultDao;
import pl.edu.atena.dao.TestSuiteDao;
import pl.edu.atena.dao.UserDao;
import pl.edu.atena.entities.User;
import pl.edu.atena.entities.factory.TestElementFactory;
import pl.edu.atena.enums.UserType;
import pl.edu.atena.utilities.InputFileBean;
import pl.edu.atena.utilities.ObjectConverter;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path(value = "/user")
public class UserService {
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
	private TestElementFactory testFactory;

	@Context
	UriInfo uriInfo;

	@POST
	@Path(value = "/jsonUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(User newUser) {
		userDao.save(newUser);
		return Response.status(200).entity(newUser).build();
	}

	@GET
    @Path(value = "/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getU1(@PathParam("id") Long id) {
		User user = userDao.find(id);
		return Response.status(200).entity(user).build();
	}

	@GET
	@Path(value = "/usertype/{usertype}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByUserType(@PathParam("usertype") UserType usertype) {
		List<User> userList = userDao.findByUserType(usertype);
		return Response.status(200).entity(userList).build();
	}
	
	@GET
	@Path(value = "/emailaddress/{emailaddress}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByUserType(@PathParam("emailaddress") String emailaddress) {
		List<User> userList = userDao.findByEmail(emailaddress);
		return Response.status(200).entity(userList).build();
	}
	
	@GET
	@Path(value = "/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByUserType() {
		List<User> userList = userDao.selectAll();
		return Response.status(200).entity(userList).build();
	}

}
