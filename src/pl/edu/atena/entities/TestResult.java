package pl.edu.atena.entities;

import java.io.Serializable;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * Entity implementation class for Entity: TestResult
 *
 */
@Entity
@Data
@Table(name = "MultiSpi_TestResult")
public class TestResult extends BaseEntity {

	private String expectedValue;
	private String actualValue;
	private boolean valueComparison;

	public TestResult() {
		super();
		Random generator = new Random();

		this.actualValue = String.valueOf(generator.nextInt());
		this.expectedValue = String.valueOf(generator.nextInt());
		
		this.valueComparison=this.actualValue==this.expectedValue?true:false;
	}
	public void setValueComparison() {
		this.valueComparison=this.actualValue==this.expectedValue?true:false;
	}

}
