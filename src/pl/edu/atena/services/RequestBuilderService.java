package pl.edu.atena.services;

import java.io.IOException;
import java.time.LocalDate;

import javax.mail.MessagingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import pl.edu.atena.email.EmailBean;
import pl.edu.atena.email.MessageEmailBean;
import pl.edu.atena.rest.StartBean;
import pl.edu.atena.utilities.ObjectConverter;
import pl.edu.atena.xls.dao.ImportDataFromXLS;
@Path(value = "/RequestBuilder")
public class RequestBuilderService {
	
	@GET
	@Path(value = "/getRequest")
	@Produces(MediaType.APPLICATION_JSON)
	public Response create() throws MessagingException, InvalidFormatException, IOException {
		StartBean start = new StartBean();
		start.getRoot().setRequestCapacity(1);
		LocalDate date = LocalDate.now();		
		String data=String.format("%s-%s-%s", date.getYear(),date.getMonthValue(),date.getDayOfMonth());
		start.getRoot().getInstanceList().get(0).setSalesDate(data);
		start.getRoot().getInstanceList().get(0).setValidDate(data);
		MessageEmailBean<String> mess = new MessageEmailBean<>();
		mess.setText(ObjectConverter.convertRequestToJSON(start));
		EmailBean.sendGMXText(mess);
		ImportDataFromXLS objdata = new ImportDataFromXLS();
		start.getRoot().getInstanceList().get(0).setObjectList(objdata.importObjects());
		start.getRoot().getInstanceList().get(0).setRelationList(objdata.importRelations());
		return Response.status(200).entity(ObjectConverter.convertRequestToJSON(start)).build();
	}
	
	public String createRequest() throws MessagingException, InvalidFormatException, IOException {
		StartBean start = new StartBean();
		start.getRoot().setRequestCapacity(1);
		LocalDate date = LocalDate.now();		
		String data=String.format("%s-%s-%s", date.getYear(),date.getMonthValue(),date.getDayOfMonth());
		start.getRoot().getInstanceList().get(0).setSalesDate(data);
		start.getRoot().getInstanceList().get(0).setValidDate(data);
		MessageEmailBean<String> mess = new MessageEmailBean<>();
		mess.setText(ObjectConverter.convertRequestToJSON(start));
		EmailBean.sendGMXText(mess);
		ImportDataFromXLS objdata = new ImportDataFromXLS();
		start.getRoot().getInstanceList().get(0).setObjectList(objdata.importObjects());
		start.getRoot().getInstanceList().get(0).setRelationList(objdata.importRelations());
		return ObjectConverter.convertRequestToJSON(start);
	}
}
