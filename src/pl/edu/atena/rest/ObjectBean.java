package pl.edu.atena.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import lombok.Data;

@Stateless
@Data
public class ObjectBean implements IModelBean{
private String id;
private String name;
private List<AttributeBean> attributeList;
public ObjectBean() {
	super();
	this.id="1";
	this.name="defaultName";
	this.attributeList=new ArrayList<AttributeBean>();
	this.attributeList.add(new AttributeBean());
	
}
@Override
public void setDefaultValues() {
	this.id="";
	this.name="";
	this.attributeList=new ArrayList<AttributeBean>();
	this.attributeList.add(new AttributeBean());
}

}
