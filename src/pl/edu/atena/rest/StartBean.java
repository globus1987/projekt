package pl.edu.atena.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import lombok.Data;

@Stateless
@Data
public class StartBean {
@EJB
private RootBean root;

public StartBean(RootBean root) {
	super();
	this.root = root;
}
public StartBean() {
	super();
	this.root = new RootBean();
}

}
