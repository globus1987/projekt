package pl.edu.atena.timer;

import pl.edu.atena.dao.UserDao;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;


@Startup
@Singleton
public class TestSuiteJob {

    /**
     * Default constructor. 
     */

    @EJB
	private UserDao userDao;


}