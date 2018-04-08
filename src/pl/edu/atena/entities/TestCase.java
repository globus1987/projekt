package pl.edu.atena.entities;

import java.sql.Clob;
import java.util.Date;
import java.time.Instant;
import java.util.List;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import pl.edu.atena.enums.TestCaseStatus;

/**
 * Entity implementation class for Entity: ReqRespLog
 *
 */
@Entity
@Data
@Table(name = "MultiSpi_TestCase")
public class TestCase extends BaseEntity {
	/**
	 * 
	 */
	@Column(name = "TestCaseName", nullable = false, length = 100)
	private String name;
	private TestCaseStatus status;
	//private Clob request;
	//private Clob response;
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinTable(name = "TC_RESULTLIST", joinColumns = @JoinColumn(name = "TC_ID"), inverseJoinColumns = @JoinColumn(name = "TCRESULT_ID"))
	private List<TestResult> resultList;
	private Date lastExecutionDate;
	private String inputFileId;
	
	public TestCase() {
		super();
		Random generator = new Random();

		this.name="TC_Number_"+String.valueOf(generator.nextInt(100000));
		
		this.status=TestCaseStatus.PREPARED;
		this.lastExecutionDate=Date.from(Instant.now());
		this.inputFileId="testowyplik";
	}
	public void buildRequest() {
		// TODO
	}

}
