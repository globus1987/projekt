package pl.edu.atena.services;

import org.apache.log4j.Logger;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import java.io.*;
import java.util.List;
import java.util.Map;

public class FileServiceImpl implements IFileService {
    Logger log = Logger.getLogger("log_fileservice");
    public static final String UPLOAD_FILE_SERVER = System.getProperty("UploadFolder");

    public Response downloadExcelFile() {
        String path = "D:/Demo/download/Sample.xlsx";
        // set file (and path) to be download
        File file = new File(path);

        ResponseBuilder responseBuilder = Response.ok((Object) file);
        responseBuilder.header("Content-Disposition", "attachment; filename=\"MyExcelFile.xlsx\"");
        return responseBuilder.build();
    }

    public Response uploadExcelFile(MultipartFormDataInput multipartFormDataInput) {

        MultivaluedMap<String, String> multivaluedMap = null;

        String fileName = null;
        InputStream inputStream = null;
        String uploadFilePath = null;

        try {
            Map<String, List<InputPart>> map = multipartFormDataInput.getFormDataMap();
            List<InputPart> lstInputPart = map.get("uploadedFile");

            for (InputPart inputPart : lstInputPart) {

                // get filename to be uploaded
                multivaluedMap = inputPart.getHeaders();
                fileName = getFileName(multivaluedMap);

                if (null != fileName && !"".equalsIgnoreCase(fileName)) {

                    // write & upload file to UPLOAD_FILE_SERVER
                    inputStream = inputPart.getBody(InputStream.class, null);
                    uploadFilePath = writeToFileServer(inputStream, fileName);

                    // close the stream
                    inputStream.close();
                }
            }
        } catch (IOException ioe) {
            log.error("problem z plikiem xls importowanym");
        } finally {
            // release resources, if any
        }

        return Response.ok("File uploaded successfully at " + uploadFilePath).build();
    }


    public String uploadFile(MultipartFormDataInput multipartFormDataInput) {

        MultivaluedMap<String, String> multivaluedMap = null;

        String fileName = null;
        InputStream inputStream = null;
        String uploadFilePath = null;

        try {
            Map<String, List<InputPart>> map = multipartFormDataInput.getFormDataMap();
            List<InputPart> lstInputPart = map.get("uploadedFile");

            for (InputPart inputPart : lstInputPart) {

                // get filename to be uploaded
                multivaluedMap = inputPart.getHeaders();
                fileName = getFileName(multivaluedMap);

                if (null != fileName && !"".equalsIgnoreCase(fileName)) {

                    // write & upload file to UPLOAD_FILE_SERVER
                    inputStream = inputPart.getBody(InputStream.class, null);
                    uploadFilePath = writeToFileServer(inputStream, fileName);

                    // close the stream
                    inputStream.close();
                }
            }
        } catch (IOException ioe) {
            log.error("problem z plikiem");
        } finally {
            // release resources, if any
        }

        return uploadFilePath;
    }

    /**
     * @param inputStream
     * @param fileName
     * @throws IOException
     */
    private String writeToFileServer(InputStream inputStream, String fileName) throws IOException {

        String qualifiedUploadFilePath = UPLOAD_FILE_SERVER + fileName;
        try (OutputStream outputStream = new FileOutputStream(new File(qualifiedUploadFilePath))) {
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.flush();
        } catch (IOException ioe) {
            log.error("problem z zapisem pliku na serwer");
        }
        //release resource, if any
        return qualifiedUploadFilePath;
    }

    /**
     * @param multivaluedMap
     * @return
     */
    private String getFileName(MultivaluedMap<String, String> multivaluedMap) {

        String[] contentDisposition = multivaluedMap.getFirst("Content-Disposition").split(";");

        for (String filename : contentDisposition) {

            if ((filename.trim().startsWith("filename"))) {
                String[] name = filename.split("=");
                return name[1].trim().replaceAll("\"", "");
            }
        }
        return "UnknownFile";
    }

}
