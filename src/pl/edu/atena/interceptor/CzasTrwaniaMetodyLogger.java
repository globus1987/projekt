package pl.edu.atena.interceptor;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.apache.log4j.Logger;

import pl.edu.atena.audyt.AudytDao;
import pl.edu.atena.utilities.ObjectConverter;

public class CzasTrwaniaMetodyLogger {

	@Inject
	private AudytDao audytdao;
	

	
	@AroundInvoke
	public Object liczCzas(InvocationContext invocation) throws Exception {
		Instant start = Instant.now();
		try {
			return invocation.proceed();
		} finally {
			long mils = Duration.between(start, Instant.now()).toMillis();
			String clazz = invocation.getTarget().getClass().getCanonicalName();
			String method = invocation.getMethod().getName();
			String parametry=Arrays.asList(invocation.getParameters()).toString();
			Logger log = Logger.getLogger(clazz);
			
			audytdao.loguj(clazz, method, mils, parametry);
		}
	}
}
