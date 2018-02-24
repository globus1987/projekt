package pl.edu.atena.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import lombok.Data;
import pl.edu.atena.entities.User;
import pl.edu.atena.enums.UserType;

@Data
@Stateless
public class UserDao {
	@PersistenceContext(unitName = "MultiSpi")
	private EntityManager em;

	@EJB
	private User newUser;

	public void save(User user) {
		if (user.getId() ==null || user.getId() ==0) {
			em.persist(user);
		} else {
			em.merge(user);
		}
		;
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

	public List<User> findByUser(UserType usertype) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> query = cb.createQuery(User.class);

		Root<User> p = query.from(User.class);
		query.select(p);
		query.where(cb.equal(p.get("userType"), usertype));

		List<User> list = this.em.createQuery(query).getResultList();

		// set the root class
		return list;
	}

	public User retrieve(Long id) {
		return em.getReference(User.class, id);
	}

	public void addUser(String inputFirstName, String inputSurname, String inputEmail, UserType inputUserType) {

		newUser.setFirstName(inputFirstName);
		newUser.setSurname(inputSurname);
		newUser.setEmailAddress(inputEmail);
		newUser.setUserType(inputUserType);
		em.merge(newUser);
	}

	
}
