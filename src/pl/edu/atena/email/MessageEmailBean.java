package pl.edu.atena.email;

import javax.ejb.Stateless;

import lombok.Data;

@Data
@Stateless
public class MessageEmailBean<T> {
	private String subject;
	private T text;
}
