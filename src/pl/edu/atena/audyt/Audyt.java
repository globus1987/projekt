package pl.edu.atena.audyt;

import java.time.Instant;
import java.util.Date;

import javax.interceptor.ExcludeDefaultInterceptors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@ExcludeDefaultInterceptors 
@Data
public class Audyt {

	@Id
	@GeneratedValue
	private Long id;

	private String klasa;
	private String metoda;
	private long mils;
	@Column(length=10485760)
	private String parametry;
	@Temporal(TemporalType.TIMESTAMP)
	private Date timeStamp;
	public Audyt() {
		
	}

	public Audyt(String klasa,String metoda,long mils,String parametry) {
		this.timeStamp=Date.from(Instant.now());
		this.klasa=klasa;
		this.metoda=metoda;
		this.mils=mils;
		this.parametry=parametry;
	
	}
}
