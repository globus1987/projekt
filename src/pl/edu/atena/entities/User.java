package pl.edu.atena.entities;

import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Data;
import pl.edu.atena.enums.UserType;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
@Data
@Table(name = "MultiSpi_User")
@Stateless
public class User extends BaseEntity{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "First_Name", nullable = false, length = 100)
	private String firstName;
	@Column(name = "Surname", nullable = false, length = 100)
	private String surname;
	@Column(name = "Email_Address", nullable = false, length = 100)
	private String emailAddress;
	@Column(name = "User_Type", nullable = false, length = 100)
	@Enumerated(EnumType.STRING)
	private UserType userType;

	
	public User() {
		
		this.surname = "abc";
		this.firstName = "zxc";
		this.emailAddress = "asda@wp.pl";
		this.userType = UserType.ADMIN;
	}



}
