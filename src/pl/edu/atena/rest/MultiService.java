package pl.edu.atena.rest;

import java.time.LocalDate;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lombok.extern.jbosslog.JBossLog;

@Path(value = "/multi")
@JBossLog
public class MultiService {

	@EJB
	private StartBean start;

	@GET
	@Path(value = "/test/{param1}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response wynik(@PathParam("param1") int liczba1) {
		start.getRoot().setRequestCapacity(liczba1);
		LocalDate date = LocalDate.now();		
		String data=String.format("%s-%s-%s", date.getYear(),date.getMonthValue(),date.getDayOfMonth());
		start.getRoot().getInstanceList().get(0).setSalesDate(data);
		start.getRoot().getInstanceList().get(0).setValidDate(data);
		return Response.status(200).entity(start).build();
	}
}
