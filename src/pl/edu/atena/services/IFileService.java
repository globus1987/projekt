package pl.edu.atena.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
@Path("/fileservice")
public interface IFileService {
	 @GET
	    @Path("/download/xls")
	    @Produces("application/vnd.ms-excel")
	    public Response downloadExcelFile();
	 @POST
	    @Path("/upload/xls")
	    @Consumes(MediaType.MULTIPART_FORM_DATA)
	    public Response uploadExcelFile(MultipartFormDataInput multipartFormDataInput);
}
