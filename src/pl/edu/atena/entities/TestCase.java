package pl.edu.atena.entities;

import lombok.Data;
import pl.edu.atena.enums.TestCaseStatus;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Column(name = "TestCaseName", nullable = false, length = 100)
	private String name;
	private TestCaseStatus status;
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinTable(name = "TC_RESULTLIST", joinColumns = @JoinColumn(name = "TC_ID"), inverseJoinColumns = @JoinColumn(name = "TCRESULT_ID"))
	private List<TestResult> resultList;
	private Date lastExecutionDate;
	private String inputFileId;
	
	public TestCase() {
		super();
		int generator = new Random().nextInt(100000);

		this.name = String.format("TC_Number_%s", String.valueOf(generator));
		
		this.status=TestCaseStatus.PREPARED;
		this.lastExecutionDate=Date.from(Instant.now());
		this.inputFileId="testowyplik";
	}

	public void checkTestCaseStatus() {
		TestCaseStatus tempStatus = TestCaseStatus.PREPARED;
		for (int i=0;i<resultList.size();i++) {
			tempStatus=resultList.get(i).getActualValue().equals(resultList.get(i).getExpectedValue())?TestCaseStatus.SUCCESS:TestCaseStatus.FAILED;
			if (tempStatus==TestCaseStatus.FAILED) {
				this.status = tempStatus;
				return;
			}
		}
		this.status = tempStatus;
	}

}
