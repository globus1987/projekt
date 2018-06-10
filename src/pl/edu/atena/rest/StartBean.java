package pl.edu.atena.rest;

import javax.ejb.EJB;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
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
