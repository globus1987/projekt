package pl.edu.atena.audyt;

import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;

@Stateless
public class GlobalLogger {
	@Produces
	public Logger logger() {
		return Logger.getLogger("CDI");
	}
}
