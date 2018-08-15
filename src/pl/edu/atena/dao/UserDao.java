package pl.edu.atena.dao;

import lombok.Data;
import pl.edu.atena.entities.User;
import pl.edu.atena.enums.UserType;
import pl.edu.atena.jms.MessageProducerMulti;
import pl.edu.atena.utilities.InputFileBean;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Data
@Stateless
public class UserDao {
	@PersistenceContext(unitName = "MultiSpi")
	private EntityManager em;

	@EJB
	private User newUser;
	@EJB
	private MessageProducerMulti<User> producer;
	
	@Inject
	private InputFileBean inputFile;
	
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void save(User user) {
		if (user.getId() == null || user.getId() == 0) {
			em.persist(user);
		} else {
			em.merge(user);
		}
	}

	public void delete(Long id) {
		User user = find(id);
		if (user != null) {
			em.remove(user);
		}
	}

	public void createUserList(List<User> inputUserList) {
		inputUserList.forEach(e -> em.persist(e));
	}

	public User find(Long id) {
		return em.find(User.class, id);
	}

	public User find(UserType usertype) {
		return em.find(User.class, usertype);
	}

	public List<User> findByUserType(UserType usertype) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> query = cb.createQuery(User.class);

		Root<User> p = query.from(User.class);
		query.select(p);
		query.where(cb.equal(p.get("userType"), usertype));
		return this.em.createQuery(query).getResultList();
	}
	public List<User> findByEmail(String emailAddress) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> query = cb.createQuery(User.class);

		Root<User> p = query.from(User.class);
		query.select(p);
		query.where(cb.equal(p.get("emailAddress"), emailAddress));
		return this.em.createQuery(query).getResultList();
	}

	public User retrieve(Long id) {
		return em.getReference(User.class, id);
	}

	public void updateUser(User updatedUser) {
		User updated=this.find(updatedUser.getId());
		updated.setFirstName(inputFile.getUserFirstname());
		updated.setSurname(inputFile.getUserSurname());
		updated.setEmailAddress(inputFile.getEmail());
		
		em.merge(updated);
	}
	public List<User> selectAll(){
		Query query = em.createQuery("select p from User p");
		return query.getResultList();
	}

}
