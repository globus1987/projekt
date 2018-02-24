package pl.edu.atena.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import lombok.Data;

@Stateless
@Data
public class InstanceBean implements IModelBean {
	private String id;
	private String state;
	private String salesDate;
	private String validDate;
	private List<ObjectBean> objectList;
	private List<RelationBean> relationList;
	private List<OperationBean> operationList;
	public InstanceBean() {
		super();
		this.id = "0";
		this.state = "DEV";
		this.salesDate = "2000-01-01";
		this.validDate = "2000-01-01";
		this.objectList = new ArrayList<ObjectBean>();
		this.objectList.add(new ObjectBean());
		this.relationList = new ArrayList<RelationBean>();
		this.relationList.add(new RelationBean());
		this.operationList = new ArrayList<OperationBean>();
		this.operationList.add(new OperationBean());
	}
	@Override
	public void setDefaultValues() {
		this.id = "";
		this.state = "";
		this.salesDate = "";
		this.validDate = "";
		this.objectList = new ArrayList<ObjectBean>();
		this.objectList.add(new ObjectBean());
		this.relationList = new ArrayList<RelationBean>();
		this.relationList.add(new RelationBean());
		this.operationList = new ArrayList<OperationBean>();
		this.operationList.add(new OperationBean());		
	}

}
