package pl.edu.atena.rest;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class Result {
	private int wynikLocal;
	private int wynikRemote;
	private int wynikBean;
	private LocalDate date = LocalDate.now();
	private LocalTime time = LocalTime.now();
	private List<String> studenci = new ArrayList<>();

	public Result(int wynikLocalIn, int wynikRemoteIn, int wynikBeanIn, List<String> studenciIn) {
		super();
		wynikLocal = wynikLocalIn;
		wynikRemote = wynikRemoteIn;
		wynikBean = wynikBeanIn;
		date = LocalDate.now();
		studenci = studenciIn;
	}

	public Result() {
	}

}
