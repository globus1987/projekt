package pl.edu.atena.rest;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RootBean {
	private List<InstanceBean> InstanceList = new ArrayList<>();
	private int requestCapacity;
	private String calculationMethod;
	
	
	public RootBean(List<InstanceBean> InstanceListIn, int requestCapacity, String calculationMethod) {
		super();
		this.InstanceList = InstanceListIn;
		this.requestCapacity = requestCapacity;
		this.calculationMethod = calculationMethod;
	}
	public RootBean() {
		super();

		this.InstanceList.clear();
		this.InstanceList.add(new InstanceBean());
		this.requestCapacity = 0;
		this.calculationMethod = "NAME";
	}

}
