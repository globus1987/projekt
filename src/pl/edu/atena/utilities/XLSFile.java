package pl.edu.atena.utilities;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

import javax.ws.rs.FormParam;
 
public class XLSFile {


    private byte[] data;
 
    public byte[] getData() {
        return data;
    }
 
    @FormParam("uploadedFile")
    @PartType("application/octet-stream")
    public void setData(byte[] data) {
        this.data = data;
    }
 
}
