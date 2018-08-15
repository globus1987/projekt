package pl.edu.atena.rest;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ObjectBean implements IModelBean{
private String id;
private String name;
private List<AttributeBean> attributeList;
public ObjectBean() {
	super();
	this.id="1";
	this.name="defaultName";
	this.attributeList = new ArrayList<>();
	this.attributeList.add(new AttributeBean());
	
}
@Override
public void setDefaultValues() {
	this.id="1";
	this.name="";
	this.attributeList = new ArrayList<>();
	this.attributeList.add(new AttributeBean());
}

}
