package pl.edu.atena.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RootBean {
	private List<InstanceBean> instanceList = new ArrayList<>();
	private int requestCapacity;
	private String calculationMethod;


	public RootBean(List<InstanceBean> instanceListIn, int requestCapacity, String calculationMethod) {
		super();
		this.instanceList = instanceListIn;
		this.requestCapacity = requestCapacity;
		this.calculationMethod = calculationMethod;
	}
	public RootBean() {
		super();

		this.instanceList.clear();
		this.instanceList.add(new InstanceBean());
		this.requestCapacity = 0;
		this.calculationMethod = "NAME";
	}

}
