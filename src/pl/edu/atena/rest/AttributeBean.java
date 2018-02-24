package pl.edu.atena.rest;

import javax.ejb.Stateless;
import lombok.Data;

@Stateless
@Data
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
	// TODO Auto-generated method stub
	this.symbol="";
	this.value="";
}


}
