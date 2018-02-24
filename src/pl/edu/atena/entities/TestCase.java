package pl.edu.atena.entities;

import java.time.LocalDate;
import java.util.List;

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
	// private Clob request;
	// private Clob response;
	@OneToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "TC_RESULTLIST", joinColumns = @JoinColumn(name = "TC_ID"), inverseJoinColumns = @JoinColumn(name = "TCRESULT_ID"))
	private List<TestResult> resultList;
	private LocalDate lastExecutionDate;
	private String inputFileId;
	
	public TestCase() {
		super();
		this.name="nieustalona";
		this.status=TestCaseStatus.PREPARED;
		this.lastExecutionDate=LocalDate.now();
		this.inputFileId="testowyplik";
	}
	public void buildRequest() {
		// TODO
	}

}
