package pl.edu.atena.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
