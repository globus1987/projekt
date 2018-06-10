package pl.edu.atena.timer;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

@Stateless
public class SentTopic {
	private Timer timer;
    /**
     * Default constructor. 
     */
	@Resource
	private TimerService ts;
	
    public SentTopic() {
        // TODO Auto-generated constructor stub
    	LocalDateTime ldt = LocalDateTime.now().plusSeconds(10);
		Instant instant = ldt.atZone(ZoneId.systemDefault()).toInstant();
		Date date = Date.from(instant);
	//	timer = ts.createTimer(date, "Timer ts");
    }
	
	 @Timeout
	    public void programmaticTimeout(Timer timer) {
	        System.out.println("timeout");
	    }
}