package pl.edu.atena.services;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import pl.edu.atena.email.EmailBean;
import pl.edu.atena.email.MessageEmailBean;
import pl.edu.atena.rest.StartBean;
import pl.edu.atena.utilities.ObjectConverter;
import pl.edu.atena.xls.dao.ImportDataFromXLS;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.time.LocalDate;
@Path(value = "/RequestBuilder")
public class RequestBuilderService {
	private static final String DATEFORMAT = "%s-%s-%s";

	@Inject
	private ObjectConverter objectConverter;
	@GET
	@Path(value = "/getRequest")
	@Produces(MediaType.APPLICATION_JSON)
	public Response create() throws MessagingException, InvalidFormatException, IOException {
		StartBean start = new StartBean();
		start.getRoot().setRequestCapacity(1);
		LocalDate date = LocalDate.now();
		String data = String.format(DATEFORMAT, date.getYear(), date.getMonthValue(), date.getDayOfMonth());
		start.getRoot().getInstanceList().get(0).setSalesDate(data);
		start.getRoot().getInstanceList().get(0).setValidDate(data);

		ImportDataFromXLS objdata = new ImportDataFromXLS();
		start.getRoot().getInstanceList().get(0).setObjectList(objdata.importObjects());
		start.getRoot().getInstanceList().get(0).setRelationList(objdata.importRelations());
		return Response.status(200).entity(objectConverter.convertRequestToJSON(start)).build();
	}
	
	@POST
	@Path(value = "/getRequest")
	@Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response createNew(MultipartFormDataInput multipartFormDataInput) throws MessagingException, InvalidFormatException, IOException {
		FileServiceImpl fileservice = new FileServiceImpl();
		String filename = fileservice.uploadFile(multipartFormDataInput);
		
		
		StartBean start = new StartBean();
		start.getRoot().setRequestCapacity(1);
		LocalDate date = LocalDate.now();
		String data = String.format(DATEFORMAT, date.getYear(), date.getMonthValue(), date.getDayOfMonth());
		start.getRoot().getInstanceList().get(0).setSalesDate(data);
		start.getRoot().getInstanceList().get(0).setValidDate(data);

		ImportDataFromXLS objdata = new ImportDataFromXLS();
		start.getRoot().getInstanceList().get(0).setObjectList(objdata.importObjects(filename));
		start.getRoot().getInstanceList().get(0).setRelationList(objdata.importRelations(filename));

		return Response.status(200).entity(objectConverter.convertRequestToJSON(start)).build();
	}
	
	public String createRequest() throws MessagingException, InvalidFormatException, IOException {
		StartBean start = new StartBean();
		start.getRoot().setRequestCapacity(1);
		LocalDate date = LocalDate.now();
		String data = String.format(DATEFORMAT, date.getYear(), date.getMonthValue(), date.getDayOfMonth());
		start.getRoot().getInstanceList().get(0).setSalesDate(data);
		start.getRoot().getInstanceList().get(0).setValidDate(data);

		ImportDataFromXLS objdata = new ImportDataFromXLS();
		start.getRoot().getInstanceList().get(0).setObjectList(objdata.importObjects());
		start.getRoot().getInstanceList().get(0).setRelationList(objdata.importRelations());
		return objectConverter.convertRequestToJSON(start);
	}
}
