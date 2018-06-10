package pl.edu.atena.rest;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OperationBean {
private String refId;
private String method;

public OperationBean() {
	super();
	this.refId="1";
	this.method="default";
}

}
