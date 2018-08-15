package pl.edu.atena.interceptor;

import pl.edu.atena.audyt.AudytDao;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

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
			audytdao.loguj(clazz, method, mils, parametry);
		}
	}
}
