package com.example.demo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Staff {

	int id;
	List<Map<String, LocalDate>> rows;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Map<String, LocalDate>> getRows() {
		return rows;
	}
	public void setRows(List<Map<String, LocalDate>> rows) {
		this.rows = rows;
	}
	public Staff(int id, List<Map<String, LocalDate>> rows) {
		super();
		this.id = id;
		this.rows = rows;
	}
	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	
	

	
	
	
	
}
