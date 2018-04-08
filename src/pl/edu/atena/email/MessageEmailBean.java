package pl.edu.atena.email;

import javax.ejb.Stateless;

import lombok.Data;

@Data
@Stateless
public class MessageEmailBean {
private String subject;
private String text;
}
