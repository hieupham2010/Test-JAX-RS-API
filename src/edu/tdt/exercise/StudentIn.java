package edu.tdt.exercise;

public class StudentIn {
	private String id;
	private String name;
	public StudentIn() {}
	public StudentIn(String id , String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "id = " + id + "name = " + name;
	}
}
