package pl.edu.atena.services;

import org.apache.log4j.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import pl.edu.atena.utilities.XLSFile;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
@Path(value = "/upload")
public class UploadFileService {
	Logger log = Logger.getLogger("log_upload");
	@POST
	@Path("/upload")
	@Consumes("multipart/form-data")
	public Response uploadFile(@MultipartForm XLSFile form) {

		String fileName = "d:\\anything.jpg";
		
		try {
			writeFile(form.getData(), fileName);
		} catch (IOException e) {

			log.error("problem z zapisem pliku");
		}
		return Response.status(200)
		    .entity("uploadFile is called, Uploaded file name : " + fileName).build();

	}

	// save to somewhere
	private void writeFile(byte[] content, String filename) throws IOException {

		File file = new File(filename);

		if (!file.exists()) {
			file.createNewFile();
		}

		try (FileOutputStream fop = new FileOutputStream(file)) {

			fop.write(content);
			fop.flush();
		}

	}
}
