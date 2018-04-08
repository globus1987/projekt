package pl.edu.atena.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.Setter;

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
@Setter
public class TestSuite extends BaseEntity {
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

	public TestSuite() {
		super();
		this.name = "testowy suite";

	}

	@Override
	public String toString() {
		return String.format("TestSuite [name=%s, creatorUser=%s, testcaseList=%s]", name, creatorUser, testcaseList);
	}

}
