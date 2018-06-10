package pl.edu.atena.entities;

import java.util.Random;

import javax.persistence.Entity;
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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
