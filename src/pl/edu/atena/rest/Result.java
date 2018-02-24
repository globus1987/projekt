package pl.edu.atena.rest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Result {
	private int WynikLocal;
	private int WynikRemote;
	private int WynikBean;
	private LocalDate date = LocalDate.now();
	private LocalTime time = LocalTime.now();
	private List<String> studenci = new ArrayList<>();

	public Result(int wynikLocal, int wynikRemote, int wynikBean, List<String> studenciIn) {
		super();
		WynikLocal = wynikLocal;
		WynikRemote = wynikRemote;
		WynikBean = wynikBean;
		date = LocalDate.now();
		studenci = studenciIn;
	}

	public Result() {
		// TODO Auto-generated constructor stub
	}

}
