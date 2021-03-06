package pl.edu.atena.entities;

import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * Entity implementation class for Entity: TestSuite
 *
 */
@Entity
/**
 * @return
 */

@Table(name = "MultiSpi_TestSuite")
@XmlRootElement(name="Zestaw")
@Data
public class TestSuite extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Date getImportDate() {
		return importDate;
	}


	public void setImportDate(Date importDate) {
		this.importDate = importDate;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setCreatorUser(User creatorUser) {
		this.creatorUser = creatorUser;
	}


	public void setTestcaseList(List<TestCase> testcaseList) {
		this.testcaseList = testcaseList;
	}


	public String getName() {
		return name;
	}


	public User getCreatorUser() {
		return creatorUser;
	}

	
	public List<TestCase> getTestcaseList() {
		return testcaseList;
	}

	
	@Column(name = "MultiSpi_TestSuiteName", nullable = false, length = 100)
	private String name;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CREATORUSER_ID")
	private User creatorUser;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "TESTSUITE_TESTCASE", joinColumns = @JoinColumn(name = "TS_ID"), inverseJoinColumns = @JoinColumn(name = "TC_ID"))
	private List<TestCase> testcaseList;
	@Temporal(TemporalType.DATE)
	private Date importDate;
	@Lob
	private String request;
	
	
	public TestSuite() {
		super();
		this.creatorUser = new User();
		this.name = "testowy suite";
		this.importDate = Date.from(Instant.now());
	}

	@Override
	public String toString() {
		return String.format("TestSuite [name=%s, creatorUser=%s, testcaseList=%s]", name, creatorUser, testcaseList);
	}

}
