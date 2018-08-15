package pl.edu.atena.observers;

import lombok.Data;
import lombok.extern.java.Log;
import pl.edu.atena.entities.TestSuite;
import pl.edu.atena.observers.TestSuiteEvent.Typ;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;

@Data
@Stateless
@Log
public class TestCaseObserver {
	
	public void jestTestCasePrzedZmiana(@Observes @TestSuiteEvent(Typ.PRZED) TestSuite testsuite) {
		log.info("test przed zmiana:"+testsuite );
	}
	public void jestTestCasePoZmianie(@Observes @TestSuiteEvent(Typ.PO) TestSuite testsuite) {
		log.info("test po zmianie:"+testsuite );
	}
	
	public void aftersuccess(@Observes(during=TransactionPhase.AFTER_SUCCESS) @TestSuiteEvent(Typ.PRZED) TestSuite testsuite) {
		log.info("after success");
	}
	
	public void afterfail(@Observes(during=TransactionPhase.AFTER_FAILURE) @TestSuiteEvent(Typ.PRZED) TestSuite testsuite) {
		log.info("after fail");
	}
	
	public void beforecompletion(@Observes(during=TransactionPhase.BEFORE_COMPLETION) @TestSuiteEvent(Typ.PRZED) TestSuite testsuite) {
		log.info("before completion");
	}
	
	public void aftercompletion(@Observes(during=TransactionPhase.AFTER_COMPLETION) @TestSuiteEvent(Typ.PRZED) TestSuite testsuite) {
		log.info("after completion");
	}
}
