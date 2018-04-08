package pl.edu.atena.timer;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timer;

import pl.edu.atena.dao.UserDao;
import pl.edu.atena.enums.UserType;


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