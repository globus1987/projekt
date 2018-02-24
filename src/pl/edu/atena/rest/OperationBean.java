package pl.edu.atena.rest;

import javax.ejb.Stateless;

import lombok.Data;

@Stateless
@Data
public class OperationBean {
private String refId;
private String method;

public OperationBean() {
	super();
	this.refId="1";
	this.method="default";
}

}
