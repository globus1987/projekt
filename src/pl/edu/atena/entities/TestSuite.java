package pl.edu.atena.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;

/**
 * Entity implementation class for Entity: TestSuite
 *
 */
@Entity
@Data
@Table(name = "MultiSpi_TestSuite")
public class TestSuite extends BaseEntity {

	private String name;
	@OneToOne
	@JoinColumn(name = "CREATORUSER_ID")
	private User creatorUser;
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "TESTSUITE_TESTCASE", joinColumns = @JoinColumn(name = "TS_ID"), inverseJoinColumns = @JoinColumn(name = "TC_ID"))
	private List<TestCase> testcaseList;

	public TestSuite() {
		super();
		this.name = "testowy suite";

	}



}
