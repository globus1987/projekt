package pl.edu.atena.rest;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RelationBean {
	private String sourceId;
	private String targetId;
	public RelationBean() {
		super();
		this.sourceId="1";
		this.targetId="1";
	}

}
