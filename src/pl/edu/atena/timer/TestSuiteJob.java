package pl.edu.atena.timer;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import pl.edu.atena.dao.UserDao;


@Startup
@Singleton
public class TestSuiteJob {

    /**
     * Default constructor. 
     */
    public TestSuiteJob() {
        // TODO Auto-generated constructor stub
    }
	@EJB
	private UserDao userDao;


}