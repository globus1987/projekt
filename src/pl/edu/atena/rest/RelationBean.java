package pl.edu.atena.rest;

import javax.ejb.Stateless;

import lombok.Data;

@Stateless
@Data
public class RelationBean {
	private String sourceId;
	private String targetId;
	public RelationBean() {
		super();
		this.sourceId="1";
		this.targetId="1";
	}

}
