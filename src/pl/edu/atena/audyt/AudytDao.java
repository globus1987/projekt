package pl.edu.atena.audyt;

import java.sql.Clob;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.ExcludeDefaultInterceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@ExcludeDefaultInterceptors   
public class AudytDao {

	@PersistenceContext(unitName = "MultiSpi")
	private EntityManager em;

	public void loguj(String klasa,String metoda,long mils,String parametry) {
		Audyt audyt = new Audyt(klasa,metoda,mils,parametry);
		em.persist(audyt);
	}

}
