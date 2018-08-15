package pl.edu.atena.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
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
		this.objectList = new ArrayList<>();
		this.objectList.add(new ObjectBean());
		this.relationList = new ArrayList<>();
		this.relationList.add(new RelationBean());
		this.operationList = new ArrayList<>();
		this.operationList.add(new OperationBean());
	}
	@Override
	public void setDefaultValues() {
		this.id = "";
		this.state = "";
		this.salesDate = "";
		this.validDate = "";
		this.objectList = new ArrayList<>();
		this.objectList.add(new ObjectBean());
		this.relationList = new ArrayList<>();
		this.relationList.add(new RelationBean());
		this.operationList = new ArrayList<>();
		this.operationList.add(new OperationBean());		
	}

}
