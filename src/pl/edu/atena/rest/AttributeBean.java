package pl.edu.atena.rest;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;


/**
 * Bean odpowiedzialny za węzeł atrybutu
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AttributeBean implements IModelBean {
private String symbol;
private String value;
public AttributeBean() {
	super();
	this.symbol="defaultSymbol";
	this.value="defaultValue";
}
@Override
public void setDefaultValues() {
	this.symbol="";
	this.value="";
}


}
