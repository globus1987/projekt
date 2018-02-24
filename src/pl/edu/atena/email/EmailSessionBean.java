package pl.edu.atena.email;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;


@Stateless
@LocalBean
public class EmailSessionBean {
	private int port = 465;
	private String host = "smtp.gmail.com";
	private String from = "globus1987@gmail.com";
	private boolean auth = true;
	private String username = "globus1987@gmail.com";
	private String password = "Arek109770";
//	private Protocol protocol = Protocol.SMTPS;
	private boolean debug = true;
	public void sendEmail(String to, String subject, String body) {
		
	}
}
