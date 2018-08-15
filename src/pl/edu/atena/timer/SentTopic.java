package pl.edu.atena.timer;

import org.apache.log4j.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
@Stateless
public class SentTopic {
	Logger log = Logger.getLogger("log_senttopic");
	private Timer timer;
    /**
     * Default constructor. 
     */
	@Resource
	private TimerService ts;
	
    public SentTopic() {
    	LocalDateTime ldt = LocalDateTime.now().plusSeconds(10);
		Instant instant = ldt.atZone(ZoneId.systemDefault()).toInstant();
		Date date = Date.from(instant);
	//	timer = ts.createTimer(date, "Timer ts");
    }
	
	 @Timeout
	    public void programmaticTimeout(Timer timer) {
		 log.info("wysłanie topicu - timeout");
	    }
}